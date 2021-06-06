DROP DATABASE IF EXISTS `call_station_db`;

CREATE DATABASE `call_station_db` DEFAULT CHARACTER SET utf8;

USE `call_station_db`;

CREATE TABLE `users_table` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `accounts_table` (
    `client_id` BIGINT NOT NULL,
    `balance` DOUBLE NOT NULL,
    `status` BOOLEAN NOT NULL,
    FOREIGN KEY (`client_id`) REFERENCES `users_table` (`id`) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `addons_table`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `cost` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `accounts_addons_table` (
    `account_id` BIGINT NOT NULL,
    `service_id` BIGINT NOT NULL,
    `payment` BOOLEAN NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `accounts_table` (`client_id`) ON DELETE CASCADE,
    FOREIGN KEY (`service_id`) REFERENCES `addons_table` (`id`) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;
/*
ALTER TABLE `service`
ADD CONSTRAINT `FK_transfer_RT_source_account`
FOREIGN KEY (`source_id`)
REFERENCES `account`(`id`)
ON UPDATE RESTRICT
ON DELETE RESTRICT;
f;*/

INSERT INTO `users_table`
(`id`, `login`,   `password`, `role`)
VALUES
(1,    "Dmitry",   "12345",    0),
(2,    "fog",   "12345",     0),
(3,    "Masha",   "12345",     1),
(4,    "Sasha",   "12345",     1),
(5,    "Tanya",   "12345",     1);

INSERT INTO `accounts_table`
( `client_id`, `balance`, `status`)
VALUES
(     4,         1100,    true),
(     3,         2200,    false),
(     5,         2200,    true);

INSERT INTO `addons_table`
(`id`, `name`, `cost`)
VALUES
(1,    "mms",          65.32),
(2,    "4G",             223),
(3,    "3G",            54.8),
(4,    "BEZLIMIT",      15.5);

INSERT INTO `accounts_addons_table`
(`account_id`, `service_id`, `payment`)
VALUES
(3,               2,       false),
(5,               3,       true),
(5,               4,       false),
(4,               2,       true),
(4,               4,       false),
(4,               1,       true);