# --- !Ups

CREATE TABLE `appointment` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`doctor_id` INT(10) UNSIGNED NOT NULL,
	`start_time` DATETIME NOT NULL,
	`duration` TIME NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `appointment_doctor_fk` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

# --- !Downs

DROP TABLE `appointment`;
