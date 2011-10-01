CREATE TABLE `users`
	(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`email` char(254) COLLATE utf8_unicode_ci NOT NULL,
	`first_name` char(64) COLLATE utf8_unicode_ci NOT NULL,
	`last_name` char(128) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
CREATE TABLE `games`
	(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`playdate` date not null,
	`winner` char(1) not null,
	`opening` char(80),
	`pgn_file` char(80),
	PRIMARY KEY ( `id` )
	) ENGINE=InnoDB;
CREATE TABLE `games_teams_xref`
	(
	`game_id` int(11) not null,
	`user` char(128) not null,
	`side` char(1) not null,
	PRIMARY KEY ( game_id, user )
	) ENGINE=InnoDB;

