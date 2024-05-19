CREATE TABLE `marketplace`.`products`
(
    `id`              INT         NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(45) NOT NULL,
    `price`           VARCHAR(45) NOT NULL,
    `description`     VARCHAR(45) NOT NULL,
    `submittion_time` VARCHAR(45) NOT NULL,
    `photo_url`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
