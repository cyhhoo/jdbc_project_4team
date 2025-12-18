-- 현재 존재하는 데이터 베이스 확인
show databases;

-- 1) 데이터 베이스 생성 후,
create database bookdb;

-- bookdb 데이터베이스(스키마) 생성 확인
show databases;

-- swcamp 계정에 bookdb 데이터 베이스 모든 권한을 부여
grant all privileges on bookdb.* to 'swcamp'@'%';

-- swcamp 계정에 권한 추가 확인
show grants for 'swcamp'@'%';

use menudb;