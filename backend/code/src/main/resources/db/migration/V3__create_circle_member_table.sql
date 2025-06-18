-- 创建圈子成员表
CREATE TABLE circle_member (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    circle_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    create_time DATETIME NOT NULL,
    FOREIGN KEY (circle_id) REFERENCES circle(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    UNIQUE KEY uk_circle_user (circle_id, user_id)
); 