-- 用户表添加字段
ALTER TABLE user 
ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'ROLE_STUDENT',
ADD COLUMN mentor_id INTEGER,
ADD COLUMN avatar VARCHAR(255),
ADD COLUMN introduction TEXT;

-- 书籍表添加字段
ALTER TABLE book
ADD COLUMN author VARCHAR(100),
ADD COLUMN isbn VARCHAR(20),
ADD COLUMN cover VARCHAR(255),
ADD COLUMN category VARCHAR(50),
ADD COLUMN mentor_id INTEGER;

-- 圈子表重命名并添加字段
RENAME TABLE `group` TO circle;
ALTER TABLE circle
ADD COLUMN mentor_id INTEGER NOT NULL;

-- 创建读书笔记表
CREATE TABLE reading_note (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    content TEXT NOT NULL,
    likes INTEGER DEFAULT 0,
    create_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

-- 创建读书笔记点赞表
CREATE TABLE reading_note_likes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    note_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (note_id) REFERENCES reading_note(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    UNIQUE KEY unique_note_user (note_id, user_id)
);

-- 创建评论表
CREATE TABLE comment (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    target_id INTEGER NOT NULL,
    type VARCHAR(20) NOT NULL,
    content TEXT NOT NULL,
    create_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
); 