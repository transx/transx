-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51a-3ubuntu5.1-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema trp3
--

CREATE DATABASE IF NOT EXISTS trp3;
USE trp3;

--
-- Definition of table `trp3`.`app_user`
--

DROP TABLE IF EXISTS `trp3`.`app_user`;
CREATE TABLE  `trp3`.`app_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `address` varchar(150) default NULL,
  `country` varchar(100) default NULL,
  `city` varchar(50) NOT NULL,
  `province` varchar(100) default NULL,
  `postal_code` varchar(15) NOT NULL,
  `version` int(11) default NULL,
  `account_enabled` bit(1) default NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hint` varchar(255) default NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(255) default NULL,
  `website` varchar(255) default NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `FK459C57292875F47A` (`company_id`),
  CONSTRAINT `FK459C57292875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`app_user`
--

/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
LOCK TABLES `app_user` WRITE;
INSERT INTO `trp3`.`app_user` VALUES  (-7,'','IR','تهران','تهران','1653811111',1,0x01,'owner','12dea96fec20593566ab75692c9949596833adc9','owner@gmail.com','یادآوری رمز عبور','علی','مالکچی','','http://website.com',0x00,0x00,0x00,-1),
 (-6,'','IR','تهران','تهران','1653866846',1,0x01,'test','12dea96fec20593566ab75692c9949596833adc9','test@yahoo.com','یادآوری رمز عبور','علی','همه چی','','http://website.com',0x00,0x00,0x00,-1),
 (-5,'','IR','تهران','تهران','1653866466',1,0x01,'cashier','12dea96fec20593566ab75692c9949596833adc9','cashier@yahoo.com','یادآوری رمز عبور','علی','صندوقچی','','http://website.com',0x00,0x00,0x00,-1),
 (-4,'','IR','تهران','تهران','',1,0x01,'manager','12dea96fec20593566ab75692c9949596833adc9','manager@yahoo.com','یادآوری رمز عبور','علی','مدیرچی','','http://website.com',0x00,0x00,0x00,-1),
 (-3,'','IR','تهران','تهران','80210',1,0x01,'reserver','12dea96fec20593566ab75692c9949596833adc9','reserve@yahoo.com','یادآوری رمز عبور','علی','رزروچی','','http://website.com',0x00,0x00,0x00,-1),
 (-2,'','IR','Tehran','Tehran','80210',2,0x01,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','saeid3@gmail.com','it is still default or yga!','Saeid','Moradi','','http://trp.ir',0x00,0x00,0x00,-1);
INSERT INTO `trp3`.`app_user` VALUES  (-1,'','IR','تهران','تهران','80210',1,0x01,'user','12dea96fec20593566ab75692c9949596833adc9','saeid33mr@yahoo.com','یادآوری رمز عبور','علی','هیچی','','http://apache.org',0x00,0x00,0x00,-1),
 (1,'','IR','تهران','تهران','',2,0x01,'سعید.مرادی','d72f8b66e6224e1f1ded19d90ae086118e0888be','saeed3m@gmail.com','سعید','سعید','مرادی','','http://trp.ir',0x00,0x00,0x00,-2),
 (2,'','IR','تهران','تهران','',6,0x01,'محمد','a8ac20e495c46bcf670d3ec74405052f70d9049e','anisi.1976@gmail.com','انیسی','محمد','انیسی','','',0x00,0x00,0x00,-1),
 (3,'','IR','تهران','تهران','',2,0x01,'عباس','7c4a8d09ca3762af61e59520943dc26494f8941b','abbas@gmail.com','123456','عباس','آخوندزاده','','',0x00,0x00,0x00,-1),
 (5,'','IR','semnan','semnan','',2,0x01,'behnam5420','d4beee1ae03158f64a795e0936b64de5383cca4f','ben_s65amg@yahoo.com','02314441638','behnam','noroozi','','',0x00,0x00,0x00,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;


--
-- Definition of table `trp3`.`car`
--

DROP TABLE IF EXISTS `trp3`.`car`;
CREATE TABLE  `trp3`.`car` (
  `id` bigint(20) NOT NULL auto_increment,
  `code` varchar(20) NOT NULL,
  `plaque_serial` varchar(10) NOT NULL,
  `plaque_number` varchar(20) NOT NULL,
  `plaque_issue` varchar(50) NOT NULL,
  `motor` varchar(50) NOT NULL,
  `chassis` varchar(50) NOT NULL,
  `transit` varchar(50) NOT NULL,
  `company_build` varchar(50) NOT NULL,
  `build_year` varchar(10) NOT NULL,
  `statistic_card` varchar(20) default NULL,
  `smart_card` varchar(20) default NULL,
  `insurance_exam_deadline` datetime NOT NULL,
  `insurance_third_deadline` datetime NOT NULL,
  `insurance_body_apart_deadline` datetime NOT NULL,
  `insurance_badaneh_jodaganeh` bit(1) default NULL,
  `car_kind_id` bigint(20) default NULL,
  `company_id` bigint(20) NOT NULL,
  `person_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FK17FD42875F47A` (`company_id`),
  KEY `FK17FD4E01389BA` (`person_id`),
  KEY `FK17FD4D04105A3` (`car_kind_id`),
  CONSTRAINT `FK17FD42875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK17FD4D04105A3` FOREIGN KEY (`car_kind_id`) REFERENCES `car_kind` (`id`),
  CONSTRAINT `FK17FD4E01389BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`car`
--

/*!40000 ALTER TABLE `car` DISABLE KEYS */;
LOCK TABLES `car` WRITE;
INSERT INTO `trp3`.`car` VALUES  (-2,'C0002','12','546ع34','ایران','0000002','00002','T0002','ایران خودرو','1380','cart0002','0001','2009-06-05 00:00:00','2009-06-05 00:00:00','2009-06-05 00:00:00',0x00,-1,-1,-6),
 (-1,'C0001','11','546ع26','ایران','0000001','00001','T0001','ایران خودرو','1380','cart0001','0001','2009-06-05 00:00:00','2009-06-05 00:00:00','2009-06-05 00:00:00',0x01,-1,-1,-6);
UNLOCK TABLES;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;


--
-- Definition of table `trp3`.`car_kind`
--

DROP TABLE IF EXISTS `trp3`.`car_kind`;
CREATE TABLE  `trp3`.`car_kind` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `capacity` int(11) NOT NULL,
  `quality` varchar(15) default NULL,
  `type` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`car_kind`
--

/*!40000 ALTER TABLE `car_kind` DISABLE KEYS */;
LOCK TABLES `car_kind` WRITE;
INSERT INTO `trp3`.`car_kind` VALUES  (-5,'بنز',40,'NORMAL','TYPE1'),
 (-4,'اسکانیا',40,'UP','TYPE1'),
 (-3,'بنز355',40,'UP','TYPE1'),
 (-2,'ولوو',45,'UP','TYPE1'),
 (-1,'ولوو',40,'UP','TYPE1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `car_kind` ENABLE KEYS */;


--
-- Definition of table `trp3`.`cash`
--

DROP TABLE IF EXISTS `trp3`.`cash`;
CREATE TABLE  `trp3`.`cash` (
  `id` bigint(20) NOT NULL auto_increment,
  `time_open` datetime default NULL,
  `time_close` datetime default NULL,
  `cash_enabled` bit(1) default NULL,
  `cash_expired` bit(1) default NULL,
  `count_ticket` int(11) default NULL,
  `total_price` bigint(20) default NULL,
  `number_cash` int(11) default NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2E7B332875F47A` (`company_id`),
  CONSTRAINT `FK2E7B332875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`cash`
--

/*!40000 ALTER TABLE `cash` DISABLE KEYS */;
LOCK TABLES `cash` WRITE;
INSERT INTO `trp3`.`cash` VALUES  (-3,'2008-07-28 00:00:00','2008-10-06 01:01:48',0x01,0x01,23,3411100,1,-1),
 (-2,'2008-10-06 01:23:18',NULL,0x01,0x00,0,0,2,-1),
 (-1,NULL,NULL,0x00,0x00,0,0,3,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `cash` ENABLE KEYS */;


--
-- Definition of table `trp3`.`chair`
--

DROP TABLE IF EXISTS `trp3`.`chair`;
CREATE TABLE  `trp3`.`chair` (
  `id` bigint(20) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`chair`
--

/*!40000 ALTER TABLE `chair` DISABLE KEYS */;
LOCK TABLES `chair` WRITE;
INSERT INTO `trp3`.`chair` VALUES  (1),
 (2),
 (3),
 (4),
 (5),
 (6),
 (7),
 (8),
 (9),
 (10),
 (11),
 (12),
 (13),
 (14),
 (15),
 (16),
 (17),
 (18),
 (19),
 (20),
 (21),
 (22),
 (23),
 (24),
 (25),
 (26),
 (27),
 (28),
 (29),
 (30),
 (31),
 (32),
 (33),
 (34),
 (35),
 (36),
 (37),
 (38),
 (39),
 (40),
 (41),
 (42),
 (43),
 (44),
 (45),
 (46),
 (47),
 (48),
 (49),
 (50);
UNLOCK TABLES;
/*!40000 ALTER TABLE `chair` ENABLE KEYS */;


--
-- Definition of table `trp3`.`city`
--

DROP TABLE IF EXISTS `trp3`.`city`;
CREATE TABLE  `trp3`.`city` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(150) NOT NULL,
  `organization_code` varchar(100) default NULL,
  `inner_code` varchar(100) default NULL,
  `country_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2E996B1EC771DA` (`country_id`),
  CONSTRAINT `FK2E996B1EC771DA` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`city`
--

/*!40000 ALTER TABLE `city` DISABLE KEYS */;
LOCK TABLES `city` WRITE;
INSERT INTO `trp3`.`city` VALUES  (-4,'یزد','789','987',-1),
 (-3,'سیرجان','789','987',-1),
 (-2,'بندرعباس','456','654',-1),
 (-1,'تهران','123','321',-1),
 (1,'آبادان','00','000',-1),
 (2,'آباده','00','000',-1),
 (3,'ایذه','123','321',-1),
 (4,'آشتیان','123','321',-1),
 (5,'ابرکوه','123','321',-1),
 (6,'امیدیه','123','321',-1),
 (7,'ابیانه','123','321',-1),
 (8,'ارسنجان','123','321',-1),
 (9,'اردکان','123','321',-1),
 (10,'اردبیل','123','321',-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


--
-- Definition of table `trp3`.`company`
--

DROP TABLE IF EXISTS `trp3`.`company`;
CREATE TABLE  `trp3`.`company` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `code` varchar(50) NOT NULL,
  `city_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FK38A73C7D9EDB51FA` (`city_id`),
  CONSTRAINT `FK38A73C7D9EDB51FA` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
LOCK TABLES `company` WRITE;
INSERT INTO `trp3`.`company` VALUES  (-3,'شرکت شماره 1','11111',-1),
 (-2,'شرکت شماره 14','11777',-1),
 (-1,'شرکت آزمایشی','11222',-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `trp3`.`country`
--

DROP TABLE IF EXISTS `trp3`.`country`;
CREATE TABLE  `trp3`.`country` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`country`
--

/*!40000 ALTER TABLE `country` DISABLE KEYS */;
LOCK TABLES `country` WRITE;
INSERT INTO `trp3`.`country` VALUES  (-3,'ارمنستان'),
 (-2,'سوریه'),
 (-1,'ایران');
UNLOCK TABLES;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


--
-- Definition of table `trp3`.`driver`
--

DROP TABLE IF EXISTS `trp3`.`driver`;
CREATE TABLE  `trp3`.`driver` (
  `id` bigint(20) NOT NULL auto_increment,
  `code` varchar(50) default NULL,
  `code_note` varchar(50) default NULL,
  `licence_number` varchar(50) NOT NULL,
  `licence_issue` varchar(100) NOT NULL,
  `insurance_number` varchar(50) NOT NULL,
  `insurance_deadline` datetime NOT NULL,
  `contract_deadline` datetime default NULL,
  `live_state` varchar(100) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `person_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `person_id` (`person_id`),
  KEY `FKB11C98282875F47A` (`company_id`),
  KEY `FKB11C9828E01389BA` (`person_id`),
  CONSTRAINT `FKB11C98282875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKB11C9828E01389BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`driver`
--

/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
LOCK TABLES `driver` WRITE;
INSERT INTO `trp3`.`driver` VALUES  (-9,'461','کد 4','10353881','تهران','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-13),
 (-8,'460','کد 4','10353880','تهران','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-12),
 (-7,'459','کد 4','10353879','تهران','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-11),
 (-6,'458','کد 4','10353878','تهران','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-10),
 (-5,'457','کد 4','10353877','تهران','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-9),
 (-4,'456','کد 4','10353876','خوی','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-8),
 (-3,'571994016','کد 3','10353875','خوی','0003','2009-06-23 00:00:00','2010-06-23 00:00:00','تهران',-1,-6),
 (-2,'1','کد 2','136781','تهران','0002','2009-06-03 00:00:00','2010-06-03 00:00:00','تهران',-1,-2),
 (-1,'111111111','کد 1','341633','تهران','0001','2009-06-03 00:00:00','2010-06-03 00:00:00','تهران',-1,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;


--
-- Definition of table `trp3`.`insurance_badaneh`
--

DROP TABLE IF EXISTS `trp3`.`insurance_badaneh`;
CREATE TABLE  `trp3`.`insurance_badaneh` (
  `id` bigint(20) NOT NULL auto_increment,
  `quality` varchar(10) default NULL,
  `jodaganeh` bit(1) NOT NULL,
  `min_km` bigint(20) NOT NULL,
  `max_km` bigint(20) NOT NULL,
  `price` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`insurance_badaneh`
--

/*!40000 ALTER TABLE `insurance_badaneh` DISABLE KEYS */;
LOCK TABLES `insurance_badaneh` WRITE;
INSERT INTO `trp3`.`insurance_badaneh` VALUES  (-14,'UP',0x01,501,10000,14500),
 (-13,'NORMAL',0x01,501,10000,6900),
 (-12,'UP',0x01,301,500,10500),
 (-11,'NORMAL',0x01,301,500,4600),
 (-10,'UP',0x01,1,300,4000),
 (-9,'NORMAL',0x01,1,300,2900),
 (-8,'UP',0x00,501,10000,30000),
 (-7,'NORMAL',0x00,501,10000,16000),
 (-6,'UP',0x00,301,500,24200),
 (-5,'NORMAL',0x00,301,500,12600),
 (-4,'UP',0x00,151,300,12800),
 (-3,'NORMAL',0x00,151,300,5800),
 (-2,'UP',0x00,1,150,7000),
 (-1,'NORMAL',0x00,1,150,3800);
UNLOCK TABLES;
/*!40000 ALTER TABLE `insurance_badaneh` ENABLE KEYS */;


--
-- Definition of table `trp3`.`insurance_sarneshin`
--

DROP TABLE IF EXISTS `trp3`.`insurance_sarneshin`;
CREATE TABLE  `trp3`.`insurance_sarneshin` (
  `id` bigint(20) NOT NULL auto_increment,
  `min_capacity` int(11) NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `distance` varchar(10) default NULL,
  `price` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`insurance_sarneshin`
--

/*!40000 ALTER TABLE `insurance_sarneshin` DISABLE KEYS */;
LOCK TABLES `insurance_sarneshin` WRITE;
INSERT INTO `trp3`.`insurance_sarneshin` VALUES  (-8,43,60,'UP300',11300),
 (-7,43,60,'DOWN300',6400),
 (-6,37,42,'UP300',11000),
 (-5,37,42,'DOWN300',6000),
 (-4,28,36,'UP300',9800),
 (-3,28,36,'DOWN300',4800),
 (-2,1,27,'UP300',5200),
 (-1,1,27,'DOWN300',3000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `insurance_sarneshin` ENABLE KEYS */;


--
-- Definition of table `trp3`.`lodger`
--

DROP TABLE IF EXISTS `trp3`.`lodger`;
CREATE TABLE  `trp3`.`lodger` (
  `id` bigint(20) NOT NULL auto_increment,
  `presenter` varchar(100) default NULL,
  `company_id` bigint(20) NOT NULL,
  `person_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `person_id` (`person_id`),
  KEY `FKBE9698732875F47A` (`company_id`),
  KEY `FKBE969873E01389BA` (`person_id`),
  CONSTRAINT `FKBE9698732875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKBE969873E01389BA` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`lodger`
--

/*!40000 ALTER TABLE `lodger` DISABLE KEYS */;
LOCK TABLES `lodger` WRITE;
INSERT INTO `trp3`.`lodger` VALUES  (-3,'معرف3',-1,-7),
 (-2,'معرف2',-1,-4),
 (-1,'معرف1',-1,-3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `lodger` ENABLE KEYS */;


--
-- Definition of table `trp3`.`passenger`
--

DROP TABLE IF EXISTS `trp3`.`passenger`;
CREATE TABLE  `trp3`.`passenger` (
  `id` bigint(20) NOT NULL auto_increment,
  `first_name` varchar(50) default NULL,
  `last_name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `passport_number` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`passenger`
--

/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
LOCK TABLES `passenger` WRITE;
INSERT INTO `trp3`.`passenger` VALUES  (-23,NULL,'حیدری','MALE',NULL),
 (-22,NULL,'کرمی','MALE',NULL),
 (-21,NULL,'احمدی','MALE',NULL),
 (-20,NULL,'طاهری','MALE',NULL),
 (-19,NULL,'نوربخش','MALE',NULL),
 (-18,NULL,'یاری','MALE',NULL),
 (-17,NULL,'میرجلیلی','MALE',NULL),
 (-16,NULL,'حسینی','MALE',NULL),
 (-15,NULL,'هاشمی نژاد','MALE',NULL),
 (-14,NULL,'فریدی','MALE',NULL),
 (-13,NULL,'بوجری','MALE',NULL),
 (-12,NULL,'فرجی','MALE',NULL),
 (-11,NULL,'خدامحمد','MALE',NULL),
 (-10,NULL,'اسفندیارپور','MALE',NULL),
 (-9,NULL,'امیدی','MALE',NULL),
 (-8,NULL,'امانی','MALE',NULL),
 (-7,NULL,'فرامرزی','MALE',NULL),
 (-6,NULL,'شنبویی','MALE',NULL),
 (-5,'','نیک نام','FEMALE',''),
 (-4,NULL,'قربعلی','MALE',NULL),
 (-3,NULL,'هاشمی','MALE',NULL),
 (-2,NULL,'طالبی','FEMALE',NULL),
 (-1,NULL,'محسنی','MALE',NULL),
 (1,'','مرادی','MALE',''),
 (2,'Neale','Rudd','MALE',':-)'),
 (3,'Neale','Rudd','MALE',':-)');
INSERT INTO `trp3`.`passenger` VALUES  (4,'Saeid','Moradi','MALE',':-)'),
 (5,'Hugh','Grant','FEMALE','E9135891'),
 (6,'','انیسی','MALE',''),
 (7,'','محمدی','FEMALE',''),
 (8,'Test','تست','MALE',''),
 (9,'','محسنی','MALE',''),
 (10,'','عباس','FEMALE',''),
 (11,'','تعلیمی','MALE',''),
 (12,'','hkjh','MALE',''),
 (13,'','hftgh','MALE','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;


--
-- Definition of table `trp3`.`path`
--

DROP TABLE IF EXISTS `trp3`.`path`;
CREATE TABLE  `trp3`.`path` (
  `id` bigint(20) NOT NULL auto_increment,
  `space` bigint(20) default NULL,
  `city_end_id` bigint(20) default NULL,
  `parent_id` bigint(20) default NULL,
  `city_start_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK346425D311F1DE` (`city_end_id`),
  KEY `FK34642516CC2FF7` (`city_start_id`),
  KEY `FK346425EB76F115` (`parent_id`),
  CONSTRAINT `FK34642516CC2FF7` FOREIGN KEY (`city_start_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FK346425D311F1DE` FOREIGN KEY (`city_end_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FK346425EB76F115` FOREIGN KEY (`parent_id`) REFERENCES `path` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`path`
--

/*!40000 ALTER TABLE `path` DISABLE KEYS */;
LOCK TABLES `path` WRITE;
INSERT INTO `trp3`.`path` VALUES  (-4,200,-4,-1,-1),
 (-3,300,-3,-1,-1),
 (-2,300,1,NULL,-1),
 (-1,800,-2,NULL,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `path` ENABLE KEYS */;


--
-- Definition of table `trp3`.`person`
--

DROP TABLE IF EXISTS `trp3`.`person`;
CREATE TABLE  `trp3`.`person` (
  `id` bigint(20) NOT NULL auto_increment,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `father_name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthday` datetime default NULL,
  `identity_number` varchar(50) NOT NULL,
  `identity_issue` varchar(50) NOT NULL,
  `passport_number` varchar(50) default NULL,
  `address` varchar(200) default NULL,
  `phone` varchar(50) default NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC4E39B552875F47A` (`company_id`),
  CONSTRAINT `FKC4E39B552875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
LOCK TABLES `person` WRITE;
INSERT INTO `trp3`.`person` VALUES  (-13,'بهرام','آذری','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-12,'بهرام','سهندی','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-11,'بهرام','سوادی','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-10,'بهرام','سامانی','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-9,'بهرام','سمیعی','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-8,'بهرام','سلمان پور','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1),
 (-7,'رضا','مهماندار3','پدر','MALE','1983-06-03 00:00:00','119','تهران','G111222',' !آدرس در اینجا درج شود','09127777777',-1);
INSERT INTO `trp3`.`person` VALUES  (-6,'رضا','محسنی فر','پدر','MALE','1963-06-03 00:00:00','830','تهران','G527523',' !آدرس در اینجا درج شود','09127777777',-1),
 (-5,'for Test!','testi','testBaba','MALE','2008-06-03 00:00:00','1414','Tehran','G527523',' !آدرس در اینجا درج شود','09127777777',-1),
 (-4,'رضا','مهماندار2','پدر','MALE','1988-06-03 00:00:00','125','تهران','G111111',' !آدرس در اینجا درج شود','09127777777',-1),
 (-3,'رضا','مهماندار','پدر','MALE','1987-06-03 00:00:00','110','تهران','U6789990',' !آدرس در اینجا درج شود','0217777777',-1),
 (-2,'حسن','رحیم پور ناثینی','پدر','MALE','1973-06-03 00:00:00','130','تهران','R765768',' !آدرس در اینجا درج شود','09127777777',-1),
 (-1,'ابراهیم','آقاجانی','پدر','MALE','1983-06-03 00:00:00','1316','تهران','G189056',' !آدرس در اینجا درج شود','0217777777',-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


--
-- Definition of table `trp3`.`rate`
--

DROP TABLE IF EXISTS `trp3`.`rate`;
CREATE TABLE  `trp3`.`rate` (
  `id` bigint(20) NOT NULL auto_increment,
  `rate_type` varchar(20) NOT NULL,
  `quality` varchar(15) NOT NULL,
  `price` bigint(20) NOT NULL,
  `toll` bigint(20) NOT NULL,
  `snack` bigint(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK354CE040E39FBA` (`path_id`),
  CONSTRAINT `FK354CE040E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`rate`
--

/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
LOCK TABLES `rate` WRITE;
INSERT INTO `trp3`.`rate` VALUES  (-3,'ORDINARY','UP',73100,3025,1900,-4),
 (-2,'ORDINARY','UP',108100,4441,1900,-3),
 (-1,'ORDINARY','UP',148100,6059,1900,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;


--
-- Definition of table `trp3`.`role`
--

DROP TABLE IF EXISTS `trp3`.`role`;
CREATE TABLE  `trp3`.`role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `description` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
LOCK TABLES `role` WRITE;
INSERT INTO `trp3`.`role` VALUES  (-6,'ROLE_CASHIER','Cashier role for cash process'),
 (-5,'ROLE_RESERVER','Reserver role for reserve process'),
 (-4,'ROLE_MANAGER','Manager role for management process'),
 (-3,'ROLE_OWNER','Owner role for owner'),
 (-2,'ROLE_USER','Default role for all Users'),
 (-1,'ROLE_ADMIN','Administrator role (can privileges Users)');
UNLOCK TABLES;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `trp3`.`service`
--

DROP TABLE IF EXISTS `trp3`.`service`;
CREATE TABLE  `trp3`.`service` (
  `id` bigint(20) NOT NULL auto_increment,
  `timed` varchar(10) NOT NULL,
  `weekday` varchar(10) default NULL,
  `datebook` datetime NOT NULL,
  `service_enabled` bit(1) default NULL,
  `service_locked` bit(1) default NULL,
  `service_opened` bit(1) default NULL,
  `service_expired` bit(1) default NULL,
  `soorat_type` varchar(10) NOT NULL,
  `path_id` bigint(20) default NULL,
  `car_kind_id` bigint(20) NOT NULL,
  `car_id` bigint(20) default NULL,
  `company_id` bigint(20) NOT NULL,
  `template_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7643C6B52875F47A` (`company_id`),
  KEY `FK7643C6B540E39FBA` (`path_id`),
  KEY `FK7643C6B5E3A5911A` (`car_id`),
  KEY `FK7643C6B5ADA75D2F` (`template_id`),
  KEY `FK7643C6B5D04105A3` (`car_kind_id`),
  CONSTRAINT `FK7643C6B52875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK7643C6B540E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`),
  CONSTRAINT `FK7643C6B5ADA75D2F` FOREIGN KEY (`template_id`) REFERENCES `service_template` (`id`),
  CONSTRAINT `FK7643C6B5D04105A3` FOREIGN KEY (`car_kind_id`) REFERENCES `car_kind` (`id`),
  CONSTRAINT `FK7643C6B5E3A5911A` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
LOCK TABLES `service` WRITE;
INSERT INTO `trp3`.`service` VALUES  (-3,'18:00','TUESDAY','2008-06-17 00:00:00',0x01,0x00,0x00,0x00,'INNER',-1,-1,-1,-1,-1),
 (-2,'8:00','THURSDAY','2008-06-19 00:00:00',0x00,0x00,0x00,0x00,'PRIVATE',NULL,-1,-1,-1,-1),
 (-1,'8:00','THURSDAY','2008-06-19 00:00:00',0x01,0x00,0x00,0x00,'OUTER',-1,-1,-1,-1,-1),
 (1,'13:30','TUESDAY','2008-10-21 00:00:00',NULL,NULL,0x00,NULL,'PRIVATE',NULL,-4,-2,-1,NULL),
 (2,'12:00','MONDAY','2008-10-06 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (3,'12:00','TUESDAY','2008-10-07 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (4,'12:00','MONDAY','2008-10-06 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (5,'12:00','FRIDAY','2008-10-10 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (6,'12:00','TUESDAY','2008-10-07 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (7,'12:00','SATURDAY','2008-10-11 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (8,'12:00','WEDNESDAY','2008-10-08 00:00:00',0x01,NULL,0x01,0x00,'INNER',-2,-1,NULL,-1,-2),
 (9,'12:00','THURSDAY','2008-10-09 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2);
INSERT INTO `trp3`.`service` VALUES  (10,'12:00','FRIDAY','2008-10-10 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-1,NULL,-1,-2),
 (11,'18:30','TUESDAY','2008-10-07 00:00:00',NULL,NULL,NULL,0x00,'INNER',-2,-4,NULL,-1,-2),
 (12,'19:45','TUESDAY','2008-10-07 00:00:00',NULL,NULL,NULL,0x00,'INNER',-1,-3,NULL,-1,-1),
 (13,'19:45','THURSDAY','2008-10-09 00:00:00',NULL,NULL,NULL,0x00,'INNER',-1,-3,NULL,-1,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


--
-- Definition of table `trp3`.`service_driver`
--

DROP TABLE IF EXISTS `trp3`.`service_driver`;
CREATE TABLE  `trp3`.`service_driver` (
  `service_id` bigint(20) NOT NULL,
  `driver_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`service_id`,`driver_id`),
  KEY `FK75D2C8F24801F4DA` (`driver_id`),
  KEY `FK75D2C8F231CB297A` (`service_id`),
  CONSTRAINT `FK75D2C8F231CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK75D2C8F24801F4DA` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`service_driver`
--

/*!40000 ALTER TABLE `service_driver` DISABLE KEYS */;
LOCK TABLES `service_driver` WRITE;
INSERT INTO `trp3`.`service_driver` VALUES  (-2,-9),
 (1,-7),
 (-2,-4),
 (-3,-3),
 (-1,-3),
 (-3,-2),
 (11,-2),
 (-3,-1),
 (-1,-1),
 (1,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `service_driver` ENABLE KEYS */;


--
-- Definition of table `trp3`.`service_lodger`
--

DROP TABLE IF EXISTS `trp3`.`service_lodger`;
CREATE TABLE  `trp3`.`service_lodger` (
  `service_id` bigint(20) NOT NULL,
  `lodger_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`service_id`,`lodger_id`),
  KEY `FK834CC93D9DE40CFA` (`lodger_id`),
  KEY `FK834CC93D31CB297A` (`service_id`),
  CONSTRAINT `FK834CC93D31CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK834CC93D9DE40CFA` FOREIGN KEY (`lodger_id`) REFERENCES `lodger` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`service_lodger`
--

/*!40000 ALTER TABLE `service_lodger` DISABLE KEYS */;
LOCK TABLES `service_lodger` WRITE;
INSERT INTO `trp3`.`service_lodger` VALUES  (-3,-3),
 (-1,-3),
 (-1,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `service_lodger` ENABLE KEYS */;


--
-- Definition of table `trp3`.`service_path`
--

DROP TABLE IF EXISTS `trp3`.`service_path`;
CREATE TABLE  `trp3`.`service_path` (
  `service_id` bigint(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`service_id`,`path_id`),
  KEY `FK15743F6F40E39FBA` (`path_id`),
  KEY `FK15743F6F31CB297A` (`service_id`),
  CONSTRAINT `FK15743F6F31CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK15743F6F40E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`service_path`
--

/*!40000 ALTER TABLE `service_path` DISABLE KEYS */;
LOCK TABLES `service_path` WRITE;
INSERT INTO `trp3`.`service_path` VALUES  (-3,-4),
 (-1,-4),
 (-3,-3),
 (-1,-3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `service_path` ENABLE KEYS */;


--
-- Definition of table `trp3`.`service_template`
--

DROP TABLE IF EXISTS `trp3`.`service_template`;
CREATE TABLE  `trp3`.`service_template` (
  `id` bigint(20) NOT NULL auto_increment,
  `timed` varchar(10) default NULL,
  `company_id` bigint(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  `car_kind_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2127F2C42875F47A` (`company_id`),
  KEY `FK2127F2C440E39FBA` (`path_id`),
  KEY `FK2127F2C4D04105A3` (`car_kind_id`),
  CONSTRAINT `FK2127F2C42875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK2127F2C440E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`),
  CONSTRAINT `FK2127F2C4D04105A3` FOREIGN KEY (`car_kind_id`) REFERENCES `car_kind` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`service_template`
--

/*!40000 ALTER TABLE `service_template` DISABLE KEYS */;
LOCK TABLES `service_template` WRITE;
INSERT INTO `trp3`.`service_template` VALUES  (-3,'19:00',-2,-2,-3),
 (-2,'12:00',-1,-2,-1),
 (-1,'8:00',-1,-1,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `service_template` ENABLE KEYS */;


--
-- Definition of table `trp3`.`setting`
--

DROP TABLE IF EXISTS `trp3`.`setting`;
CREATE TABLE  `trp3`.`setting` (
  `id` bigint(20) NOT NULL,
  `rate_type` varchar(20) NOT NULL,
  `ticket_number` bigint(20) NOT NULL,
  `seri_inner` varchar(20) NOT NULL,
  `seri_outer` varchar(20) NOT NULL,
  `seri_private` varchar(20) NOT NULL,
  `serial_inner` bigint(20) NOT NULL,
  `serial_outer` bigint(20) NOT NULL,
  `serial_private` bigint(20) NOT NULL,
  `snack` bigint(20) NOT NULL,
  `commission` bigint(20) NOT NULL,
  `company_phone` varchar(20) NOT NULL,
  `company_place` varchar(100) NOT NULL,
  `stamp` bigint(20) default NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK765F0E502875F47A` (`company_id`),
  CONSTRAINT `FK765F0E502875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`setting`
--

/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
LOCK TABLES `setting` WRITE;
INSERT INTO `trp3`.`setting` VALUES  (1,'ORDINARY',46182031,'الف/د/86/1','الف/86/1','86/الف',429006,100001,100010,1900,10,'65489523','پایانه جنوب',100,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;


--
-- Definition of table `trp3`.`soorat`
--

DROP TABLE IF EXISTS `trp3`.`soorat`;
CREATE TABLE  `trp3`.`soorat` (
  `id` bigint(20) NOT NULL auto_increment,
  `seri` varchar(20) NOT NULL,
  `serial` varchar(20) NOT NULL,
  `issued` bit(1) default NULL,
  `destroyed` bit(1) default NULL,
  `driver_paid` bit(1) NOT NULL,
  `total` bigint(20) NOT NULL,
  `government_toll` bigint(20) NOT NULL,
  `insurance_sarneshin` bigint(20) NOT NULL,
  `insurance_badaneh` bigint(20) NOT NULL,
  `snack` bigint(20) NOT NULL,
  `total_is_t_a` bigint(20) default NULL,
  `commission` bigint(20) NOT NULL,
  `driver_pay` bigint(20) NOT NULL,
  `passenger_count` int(11) NOT NULL,
  `days` bigint(20) default NULL,
  `soorat_type` varchar(10) NOT NULL,
  `pathway` varchar(255) default NULL,
  `stamp` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `cashier_id` bigint(20) default NULL,
  `cash_id` bigint(20) default NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCA8DADB22875F47A` (`company_id`),
  KEY `FKCA8DADB2911E9CFA` (`cash_id`),
  KEY `FKCA8DADB2F7083402` (`cashier_id`),
  KEY `FKCA8DADB231CB297A` (`service_id`),
  CONSTRAINT `FKCA8DADB22875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKCA8DADB231CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKCA8DADB2911E9CFA` FOREIGN KEY (`cash_id`) REFERENCES `cash` (`id`),
  CONSTRAINT `FKCA8DADB2F7083402` FOREIGN KEY (`cashier_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`soorat`
--

/*!40000 ALTER TABLE `soorat` DISABLE KEYS */;
LOCK TABLES `soorat` WRITE;
INSERT INTO `trp3`.`soorat` VALUES  (-3,'الف/د/86/1','428997',0x00,0x00,0x00,3410000,135874,11000,14500,53200,3263096,326309,2869087,28,0,'INNER',NULL,30,-3,NULL,NULL,-1),
 (-2,'86/الف','100000',0x00,0x00,0x00,100000,1000,2000,2000,2000,96900,9690,83210,22,2,'PRIVATE','تهران - بندر',100,-2,NULL,NULL,-1),
 (-1,'الف/86/1','100000',0x00,0x00,0x00,111,222,333,444,555,0,666,777,0,0,'OUTER',NULL,10,-1,NULL,NULL,-1),
 (1,'86/الف','۱۰۰۰۰۳',0x01,NULL,0x00,100000,1000,1000,1000,1000,97900,9790,86110,10,1,'PRIVATE','تهران - بندرعباس',100,1,NULL,NULL,-1),
 (2,'الف/د/86/1','428998',0x01,NULL,0x00,3410000,135874,11000,14500,53200,3263026,326302,2869024,28,NULL,'INNER',NULL,100,-3,NULL,NULL,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `soorat` ENABLE KEYS */;


--
-- Definition of table `trp3`.`ticket`
--

DROP TABLE IF EXISTS `trp3`.`ticket`;
CREATE TABLE  `trp3`.`ticket` (
  `id` bigint(20) NOT NULL auto_increment,
  `number` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `toll` bigint(20) NOT NULL,
  `snack` bigint(20) NOT NULL,
  `total` bigint(20) NOT NULL,
  `reserve_date` datetime default NULL,
  `issue_date` datetime default NULL,
  `return_date` datetime default NULL,
  `issued` bit(1) NOT NULL,
  `reserver_id` varchar(255) default NULL,
  `passenger_id` bigint(20) NOT NULL,
  `cashier_id` bigint(20) NOT NULL,
  `cash_id` bigint(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  `returner_id` bigint(20) default NULL,
  `service_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCBE86B0C2875F47A` (`company_id`),
  KEY `FKCBE86B0C911E9CFA` (`cash_id`),
  KEY `FKCBE86B0C17CFD2A8` (`returner_id`),
  KEY `FKCBE86B0C35DFAB5A` (`passenger_id`),
  KEY `FKCBE86B0C40E39FBA` (`path_id`),
  KEY `FKCBE86B0CF7083402` (`cashier_id`),
  KEY `FKCBE86B0C31CB297A` (`service_id`),
  CONSTRAINT `FKCBE86B0C17CFD2A8` FOREIGN KEY (`returner_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FKCBE86B0C2875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKCBE86B0C31CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKCBE86B0C35DFAB5A` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`),
  CONSTRAINT `FKCBE86B0C40E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`),
  CONSTRAINT `FKCBE86B0C911E9CFA` FOREIGN KEY (`cash_id`) REFERENCES `cash` (`id`),
  CONSTRAINT `FKCBE86B0CF7083402` FOREIGN KEY (`cashier_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`ticket`
--

/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
LOCK TABLES `ticket` WRITE;
INSERT INTO `trp3`.`ticket` VALUES  (-23,46182030,1,108100,4441,1900,110000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-23,-2,-3,-3,NULL,-3,-1),
 (-22,46182027,3,73100,3025,1900,240000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-22,-2,-3,-4,NULL,-3,-1),
 (-21,46182028,1,73100,3025,1900,80000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-21,-2,-3,-4,NULL,-3,-1),
 (-20,46182020,2,148100,6059,1900,300000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-20,-2,-3,-1,NULL,-3,-1),
 (-19,46182029,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-19,-2,-3,-1,NULL,-3,-1),
 (-18,46182025,1,108100,4441,1900,110000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-18,-2,-3,-3,NULL,-3,-1),
 (-17,46182021,1,73100,3025,1900,80000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-17,-2,-3,-4,NULL,-3,-1),
 (-16,46182024,1,73100,3025,1900,80000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-16,-2,-3,-4,NULL,-3,-1),
 (-15,46182014,4,148100,6059,1900,600000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-15,-2,-3,-1,NULL,-3,-1);
INSERT INTO `trp3`.`ticket` VALUES  (-14,46182017,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-14,-2,-3,-1,NULL,-3,-1),
 (-13,46182015,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-13,-2,-3,-1,NULL,-3,-1),
 (-12,46182016,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-12,-2,-3,-1,NULL,-3,-1),
 (-11,46182013,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-11,-2,-3,-1,NULL,-3,-1),
 (-10,46182007,1,108100,4441,1900,110000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-10,-2,-3,-3,NULL,-3,-1),
 (-9,46182009,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-9,-2,-3,-1,NULL,-3,-1),
 (-8,46182006,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-8,-2,-3,-1,NULL,-3,-1),
 (-7,46182026,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-7,-2,-3,-1,NULL,-3,-1),
 (-6,46182004,1,148100,6059,1900,150000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-6,-2,-3,-1,NULL,-3,-1);
INSERT INTO `trp3`.`ticket` VALUES  (-5,46182012,2,73100,3025,1900,160000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-5,-2,-3,-4,NULL,-3,-1),
 (-4,46182002,1,73100,3025,1900,80000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-4,-2,-3,-4,NULL,-3,-1),
 (-3,46182001,1,108100,4441,1900,110000,'2008-06-10 00:00:00','2008-06-10 00:00:00',NULL,0x01,'reserver',-3,-2,-3,-3,NULL,-3,-1),
 (-2,46182022,2,14810,6059,190,-270000,'2008-06-10 00:00:00','2008-06-10 00:00:00','2008-10-06 00:57:35',0x00,'reserver',-2,-2,-3,-1,-2,-3,-1),
 (-1,46182018,1,74050,6059,950,-75000,'2008-06-10 00:00:00','2008-06-10 00:00:00','2008-10-06 00:58:11',0x00,'reserver',-1,-2,-3,-1,-2,-3,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;


--
-- Definition of table `trp3`.`ticket_chair`
--

DROP TABLE IF EXISTS `trp3`.`ticket_chair`;
CREATE TABLE  `trp3`.`ticket_chair` (
  `ticket_id` bigint(20) NOT NULL,
  `chair_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`ticket_id`,`chair_id`),
  KEY `FK9B372BD2B0005A5A` (`ticket_id`),
  KEY `FK9B372BD2E662E77A` (`chair_id`),
  CONSTRAINT `FK9B372BD2B0005A5A` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  CONSTRAINT `FK9B372BD2E662E77A` FOREIGN KEY (`chair_id`) REFERENCES `chair` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`ticket_chair`
--

/*!40000 ALTER TABLE `ticket_chair` DISABLE KEYS */;
LOCK TABLES `ticket_chair` WRITE;
INSERT INTO `trp3`.`ticket_chair` VALUES  (-23,40),
 (-22,34),
 (-22,35),
 (-22,36),
 (-21,33),
 (-20,31),
 (-20,32),
 (-19,30),
 (-18,29),
 (-17,28),
 (-16,27),
 (-15,23),
 (-15,24),
 (-15,25),
 (-15,26),
 (-14,22),
 (-13,21),
 (-12,20),
 (-11,19),
 (-10,18),
 (-9,17),
 (-8,15),
 (-7,14),
 (-6,13),
 (-5,11),
 (-5,12),
 (-4,10),
 (-3,9),
 (-2,7),
 (-2,8),
 (-1,5);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ticket_chair` ENABLE KEYS */;


--
-- Definition of table `trp3`.`ticket_temp`
--

DROP TABLE IF EXISTS `trp3`.`ticket_temp`;
CREATE TABLE  `trp3`.`ticket_temp` (
  `id` bigint(20) NOT NULL auto_increment,
  `count` int(11) default NULL,
  `reserve_date` datetime default NULL,
  `commit_date` datetime default NULL,
  `committed` bit(1) NOT NULL,
  `reserver_id` varchar(255) default NULL,
  `ticket_temp_type` varchar(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `passenger_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK158D99272875F47A` (`company_id`),
  KEY `FK158D992735DFAB5A` (`passenger_id`),
  KEY `FK158D992740E39FBA` (`path_id`),
  KEY `FK158D992731CB297A` (`service_id`),
  CONSTRAINT `FK158D992731CB297A` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FK158D99272875F47A` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK158D992735DFAB5A` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`),
  CONSTRAINT `FK158D992740E39FBA` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`ticket_temp`
--

/*!40000 ALTER TABLE `ticket_temp` DISABLE KEYS */;
LOCK TABLES `ticket_temp` WRITE;
INSERT INTO `trp3`.`ticket_temp` VALUES  (-3,3,'2008-06-24 00:00:00',NULL,0x00,'cust003@yahoo.com','SELL',-4,-1,-7,-1),
 (-2,2,'2008-06-24 00:00:00',NULL,0x00,'reserver','RESERVE',-3,-1,-6,-1),
 (-1,1,'2008-06-24 00:00:00',NULL,0x00,'admin','SELL',-1,-1,-5,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ticket_temp` ENABLE KEYS */;


--
-- Definition of table `trp3`.`ticket_temp_chair`
--

DROP TABLE IF EXISTS `trp3`.`ticket_temp_chair`;
CREATE TABLE  `trp3`.`ticket_temp_chair` (
  `ticket_temp_id` bigint(20) NOT NULL,
  `chair_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`ticket_temp_id`,`chair_id`),
  KEY `FK480A19ADFCCCF453` (`ticket_temp_id`),
  KEY `FK480A19ADE662E77A` (`chair_id`),
  CONSTRAINT `FK480A19ADE662E77A` FOREIGN KEY (`chair_id`) REFERENCES `chair` (`id`),
  CONSTRAINT `FK480A19ADFCCCF453` FOREIGN KEY (`ticket_temp_id`) REFERENCES `ticket_temp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`ticket_temp_chair`
--

/*!40000 ALTER TABLE `ticket_temp_chair` DISABLE KEYS */;
LOCK TABLES `ticket_temp_chair` WRITE;
INSERT INTO `trp3`.`ticket_temp_chair` VALUES  (-3,25),
 (-3,26),
 (-3,27),
 (-2,5),
 (-2,6),
 (-1,17);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ticket_temp_chair` ENABLE KEYS */;


--
-- Definition of table `trp3`.`user_role`
--

DROP TABLE IF EXISTS `trp3`.`user_role`;
CREATE TABLE  `trp3`.`user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FK143BF46AC222F21A` (`role_id`),
  KEY `FK143BF46A674DB5FA` (`user_id`),
  CONSTRAINT `FK143BF46A674DB5FA` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK143BF46AC222F21A` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trp3`.`user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
LOCK TABLES `user_role` WRITE;
INSERT INTO `trp3`.`user_role` VALUES  (-6,-6),
 (-5,-6),
 (-2,-6),
 (3,-6),
 (-6,-5),
 (-3,-5),
 (-2,-5),
 (-6,-4),
 (-4,-4),
 (-2,-4),
 (2,-4),
 (-7,-3),
 (-6,-3),
 (2,-3),
 (-7,-2),
 (-6,-2),
 (-5,-2),
 (-4,-2),
 (-3,-2),
 (-2,-2),
 (-1,-2),
 (1,-2),
 (2,-2),
 (3,-2),
 (5,-2),
 (-2,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
