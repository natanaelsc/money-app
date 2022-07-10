CREATE TABLE `user` (
	`id` BIGINT PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `permission` (
	`id` BIGINT PRIMARY KEY,
	`description` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `user_permission` (
	`user_id` BIGINT NOT NULL,
	`permission_id` BIGINT NOT NULL,
	PRIMARY KEY (`user_id`, `permission_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
	FOREIGN KEY (`permission_id`) REFERENCES `permission`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO `user` (`id`, `name`, `email`, `password`) values (1, 'Administrador', 'admin@moneyapp.com', '$2a$10$yaK9QLjuiOGi9yoNP.5jm.sVzLq..u1WuGD2Eheac4pC8y1qgcG8u');
INSERT INTO `user` (`id`, `name`, `email`, `password`) values (2, 'Maria Silva', 'maria@moneyapp.com', '$2a$10$gg.0mwJVaY9CopcuBj1Ky.Jc95HV13zzrv8FxzSRBDlIV1CcCCl/y');
INSERT INTO `permission` (`id`, `description`) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO `permission` (`id`, `description`) values (2, 'ROLE_FIND_CATEGORY');
INSERT INTO `permission` (`id`, `description`) values (3, 'ROLE_REGISTER_PERSON');
INSERT INTO `permission` (`id`, `description`) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO `permission` (`id`, `description`) values (5, 'ROLE_FIND_PERSON');
INSERT INTO `permission` (`id`, `description`) values (6, 'ROLE_REGISTER_ENTRY');
INSERT INTO `permission` (`id`, `description`) values (7, 'ROLE_REMOVE_ENTRY');
INSERT INTO `permission` (`id`, `description`) values (8, 'ROLE_FIND_ENTRY');
-- admin
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 1);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 2);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 3);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 4);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 5);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 6);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 7);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (1, 8);
-- maria
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (2, 2);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (2, 5);
INSERT INTO `user_permission` (`user_id`, `permission_id`) values (2, 8); 