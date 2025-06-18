const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
const session = require('express-session');
const multer = require('multer');
const path = require('path');

const app = express();

// 中间件
app.use(cors({
    origin: 'http://localhost:8080',
    credentials: true
}));
app.use(express.json());
app.use(session({
    secret: 'moyun-secret-key',
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false }
}));

// 数据库连接
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',  // 你的 MySQL 用户名
    password: '123456',  // 你的 MySQL 密码
    database: 'moyun'
});

// 测试数据库连接
db.connect((err) => {
    if (err) {
        console.error('数据库连接失败:', err);
        return;
    }
    console.log('数据库连接成功');
});

// 文件上传配置
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/')
    },
    filename: function (req, file, cb) {
        cb(null, Date.now() + path.extname(file.originalname))
    }
});
const upload = multer({ storage: storage });

// Add global error handlers for unhandled exceptions and unhandled promise rejections
process.on('uncaughtException', (err) => {
  console.error('Unhandled Exception:', err);
  // It's good practice to exit for uncaught exceptions after logging
  process.exit(1);
});

process.on('unhandledRejection', (reason, promise) => {
  console.error('Unhandled Rejection at:', promise, 'reason:', reason);
  // You might choose to exit or just log, depending on application needs
});

// 用户相关路由
app.post('/api/register', (req, res) => {
    const { username, password, email } = req.body;
    const sql = 'INSERT INTO users (username, password, email) VALUES (?, ?, ?)';
    db.query(sql, [username, password, email], (err, result) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json({ message: '注册成功', userId: result.insertId });
    });
});

app.post('/api/login', (req, res) => {
    const { username, password } = req.body;
    const sql = 'SELECT * FROM users WHERE username = ? AND password = ?';
    db.query(sql, [username, password], (err, results) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        if (results.length > 0) {
            req.session.userId = results[0].id;
            res.json({ message: '登录成功', user: results[0] });
        } else {
            res.status(401).json({ message: '用户名或密码错误' });
        }
    });
});

// 图书相关路由
app.get('/api/books', (req, res) => {
    const sql = 'SELECT * FROM books';
    db.query(sql, (err, results) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json(results);
    });
});

app.post('/api/books', upload.single('cover'), (req, res) => {
    const { title, author, description, category } = req.body;
    const cover = req.file ? req.file.path : null;
    const sql = 'INSERT INTO books (title, author, cover, description, category) VALUES (?, ?, ?, ?, ?)';
    db.query(sql, [title, author, cover, description, category], (err, result) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json({ message: '添加成功', bookId: result.insertId });
    });
});

// Get a single book by ID
app.get('/api/books/:id', (req, res) => {
    const bookId = req.params.id;
    const sql = 'SELECT * FROM books WHERE id = ?';
    db.query(sql, [bookId], (err, results) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        if (results.length > 0) {
            res.json(results[0]);
        } else {
            res.status(404).json({ message: 'Book not found' });
        }
    });
});

// 书评相关路由
app.post('/api/reviews', (req, res) => {
    const { bookId, content, rating } = req.body;
    const userId = req.session.userId;
    if (!userId) {
        res.status(401).json({ message: '请先登录' });
        return;
    }
    const sql = 'INSERT INTO reviews (book_id, user_id, content, rating) VALUES (?, ?, ?, ?)';
    db.query(sql, [bookId, userId, content, rating], (err, result) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json({ message: '评论成功', reviewId: result.insertId });
    });
});

// Get all reviews
app.get('/api/reviews', (req, res) => {
    const sql = 'SELECT * FROM reviews';
    db.query(sql, (err, results) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json(results);
    });
});

// Get reviews for a specific book
app.get('/api/books/:bookId/reviews', (req, res) => {
    const bookId = req.params.bookId;
    const sql = 'SELECT * FROM reviews WHERE book_id = ?';
    db.query(sql, [bookId], (err, results) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json(results);
    });
});

// 消息相关路由
app.post('/api/messages', (req, res) => {
    const { toUserId, content } = req.body;
    const fromUserId = req.session.userId;
    if (!fromUserId) {
        res.status(401).json({ message: '请先登录' });
        return;
    }
    const sql = 'INSERT INTO messages (from_user_id, to_user_id, content) VALUES (?, ?, ?)';
    db.query(sql, [fromUserId, toUserId, content], (err, result) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json({ message: '发送成功', messageId: result.insertId });
    });
});

// 日志相关路由
app.post('/api/blogs', (req, res) => {
    const { title, content } = req.body;
    const userId = req.session.userId;
    if (!userId) {
        res.status(401).json({ message: '请先登录' });
        return;
    }
    const sql = 'INSERT INTO blogs (user_id, title, content) VALUES (?, ?, ?)';
    db.query(sql, [userId, title, content], (err, result) => {
        if (err) {
            res.status(500).json({ error: err.message });
            return;
        }
        res.json({ message: '发表成功', blogId: result.insertId });
    });
});

const PORT = 5000;
const server = app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});

server.on('error', (err) => {
    console.error('Server failed to start or encountered an error:', err);
}); 