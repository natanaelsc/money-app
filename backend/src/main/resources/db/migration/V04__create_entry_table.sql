CREATE TABLE IF NOT EXISTS `entry` 
(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(50) NOT NULL,
	`due_date` DATE NOT NULL,
	`payment_date` DATE,
	`amount` DECIMAL(10,2) NOT NULL,
	`note` VARCHAR(100),
	`type` VARCHAR(20) NOT NULL,
	`category_id` BIGINT,
	`person_id` BIGINT,
    CONSTRAINT `entry_pkey` PRIMARY KEY (`id`),
	FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
	FOREIGN KEY (`person_id`) REFERENCES `person`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;