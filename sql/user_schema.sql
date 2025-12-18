CREATE DATABASE IF NOT EXISTS bookdb;
USE bookdb;

CREATE TABLE IF NOT EXISTS tbl_user (
    user_no INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 번호',
    user_id VARCHAR(50) NOT NULL UNIQUE COMMENT '아이디',
    user_pwd VARCHAR(255) NOT NULL COMMENT '비밀번호',
    user_name VARCHAR(50) NOT NULL COMMENT '이름',
    user_role VARCHAR(20) DEFAULT 'USER' COMMENT '권한 (USER, ADMIN)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자 목록';

-- 초기 관리자 계정 생성 (이미 존재하면 무시)
INSERT INTO tbl_user (user_id, user_pwd, user_name, user_role)
SELECT * FROM (
    SELECT 'admin' AS new_id, 'admin' AS new_pwd, '관리자' AS new_name, 'ADMIN' AS new_role
) AS tmp
WHERE NOT EXISTS (
    SELECT user_id FROM tbl_user WHERE user_id = 'admin'
) LIMIT 1;

-- 초기 일반 사용자 계정 생성 (이미 존재하면 무시)
INSERT INTO tbl_user (user_id, user_pwd, user_name, user_role)
SELECT * FROM (
    SELECT 'user01' AS new_id, 'pass01' AS new_pwd, '홍길동' AS new_name, 'USER' AS new_role
) AS tmp
WHERE NOT EXISTS (
    SELECT user_id FROM tbl_user WHERE user_id = 'user01'
) LIMIT 1;

GRANT CREATE ON *.* TO 'swcamp'@'%';

GRANT ALL PRIVILEGES ON bookdb.* TO 'swcamp'@'%';

FLUSH PRIVILEGES;

