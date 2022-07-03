CREATE TABLE IF NOT EXISTS `person` 
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`street` VARCHAR(30),
	`number` VARCHAR(30),
	`complement` VARCHAR(30),
	`district` VARCHAR(30),
	`zip` VARCHAR(30),
	`city` VARCHAR(30),
	`state` VARCHAR(30),
	`active` BOOLEAN NOT NULL,
    CONSTRAINT `person_pkey` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;