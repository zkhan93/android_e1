CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
)

 CREATE TABLE `mail` (
  `id` int(11) NOT NULL auto_increment,
  `subject` varchar(100) default NULL,
  `body` varchar(500) default NULL,
  `sender` int(11) default NULL,
  `priority` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `sender` (`sender`)
)

CREATE TABLE `receiver` (
  `id` int(11) NOT NULL auto_increment,
  `mail_id` int(11) default NULL,
  `to_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `mail_id` (`mail_id`),
  KEY `to_id` (`to_id`)
)

CREATE TABLE `cc` (
  `id` int(11) NOT NULL auto_increment,
  `mail_id` int(11) default NULL,
  `receiver` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `mail_id` (`mail_id`),
  KEY `receiver` (`receiver`)
)
CREATE TABLE `bcc` (
  `id` int(11) NOT NULL auto_increment,
  `mail_id` int(11) default NULL,
  `receiver` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `mail_id` (`mail_id`),
  KEY `receiver` (`receiver`)
)