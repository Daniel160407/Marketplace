ALTER TABLE `marketplace`.`products`
    ADD COLUMN `uploader` VARCHAR(45) NOT NULL AFTER `photo_url`;
