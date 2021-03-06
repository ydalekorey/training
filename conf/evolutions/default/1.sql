# --- !Ups

CREATE TABLE `doctor` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(64) NOT NULL,
	`password` CHAR(64) NOT NULL,
	`name` VARCHAR(64) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `email_uq` (`email`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

# --- !Downs

DROP TABLE `doctor`;