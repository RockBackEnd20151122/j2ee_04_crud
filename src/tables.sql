
#in MAC
brew install mysql

mysql.server start

mysql -u root -p

create database userTest;

use userTest;

CREATE TABLE `users` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table users;

INSERT INTO `users` VALUES ('01', 'Rock01', '33', 'JinzhongRoad' );
delete from users where id='01';
UPDATE users t set user_name='Rock', password='fuck' where t.user_id in ('01','2') and t.user_name='Rock01';