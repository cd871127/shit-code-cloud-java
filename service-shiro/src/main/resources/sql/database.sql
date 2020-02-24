CREATE DATABASE IF NOT EXISTS shiroDb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
grant all privileges on shiroDb.* to 'appdata'@'%';
grant insert, select, delete, update on shiroDb.* to 'appopr'@'%';
flush privileges;
