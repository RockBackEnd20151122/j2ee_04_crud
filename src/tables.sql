
#in MAC
brew install mysql

mysql.server start

mysql -u root -p

create database userTest;

use userTest;

CREATE TABLE `student` (
  `id` int(10) ,
  `name` varchar(100) NOT NULL,
  `age` varchar(100) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table student;

INSERT INTO `student` VALUES ('01', 'Rock01', '33', 'JinzhongRoad' );
delete from student where id='01';
UPDATE student t set age=7 where t.id in ('01','2') and t.name='1';