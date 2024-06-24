DROP DATABASE IF EXISTS iot_device_data;
CREATE DATABASE iot_device_data CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
SET FOREIGN_KEY_CHECKS = 0;


CREATE TABLE iot_device_data.devices
(
    deviceID BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    deviceName VARCHAR(100) NOT NULL,
    connectionURL TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE iot_device_data.device_payloads
(
    devicePayloadID BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    topic VARCHAR(1024) NOT NULL,
    payload VARCHAR(2048) NOT NULL,
    timestampOfRecord DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;