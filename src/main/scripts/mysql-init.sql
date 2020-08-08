DROP DATABASE IF EXISTS carservice;

DROP USER IF EXISTS `car_service`@`%`;

CREATE DATABASE IF NOT EXISTS carservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `car_service`@`%` IDENTIFIED WITH mysql_native_password BY '12345678';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,

CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `carservice`.* TO `car_service`@`%`;

FLUSH PRIVILEGES;