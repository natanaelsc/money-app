CREATE TABLE IF NOT EXISTS `category` 
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `category_pkey` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
INSERT INTO `category` (`name`) VALUES ('Lazer');
INSERT INTO `category` (`name`) VALUES ('Alimentação');
INSERT INTO `category` (`name`) VALUES ('Supermercado');
INSERT INTO `category` (`name`) VALUES ('Farmácia');
INSERT INTO `category` (`name`) VALUES ('Outros');