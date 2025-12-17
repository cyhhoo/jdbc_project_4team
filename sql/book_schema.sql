CREATE DATABASE IF NOT EXISTS bookdb;
USE bookdb;

CREATE TABLE IF NOT EXISTS tbl_book (
    book_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '도서 번호',
    title VARCHAR(255) NOT NULL COMMENT '도서 제목',
    author VARCHAR(255) NOT NULL COMMENT '저자',
    price INT NOT NULL COMMENT '가격',
    image_url VARCHAR(255) NULL COMMENT '이미지 경로'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='도서 목록';

-- 샘플 데이터 삽입 (이미지 경로 포함)
INSERT INTO tbl_book (title, author, price, image_url) VALUES 
('자바의 정석', '남궁성', 30000, '/resources/images/java_jungsuk.jpg'),
('Do it! 자바 프로그래밍', '박은종', 25000, '/resources/images/doit_java.jpg'),
('이펙티브 자바', '조슈아 블로크', 36000, NULL),
('헤드 퍼스트 디자인 패턴', '에릭 프리먼', 38000, NULL),
('클린 코드', '로버트 C. 마틴', 32000, NULL);
