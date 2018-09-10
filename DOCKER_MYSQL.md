##DOCKER 사용해서 MySQL 설치하기.
- $ docker pull mysql
- $ docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql_local mysql
##MySQL에 사용자 생성하기.
- $ CREATE DATABASE jooq_pilot ;
- $ CREATE USER 'local_user'@'%' IDENTIFIED BY 'password';
- $ GRANT ALL PRIVILEGES ON *.* TO 'local_user'@'%';
- $ FLUSH PRIVILEGES;