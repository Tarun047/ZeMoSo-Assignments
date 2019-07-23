-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: code
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `CustNo` varchar(8) NOT NULL,
  `CustName` varchar(30) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Internal` varchar(3) NOT NULL,
  `Contact` varchar(35) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `City` varchar(30) NOT NULL,
  `State` varchar(2) NOT NULL,
  `Zip` varchar(10) NOT NULL,
  PRIMARY KEY (`CustNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES ('C100','Football','Box 352200','Yes','Mary Manager','6857100','Boulder','CO','80309'),('C101','Men\'s Basketball','Box 352400','Yes','Sally Supervisor','5431700','Boulder','CO','80309'),('C103','Baseball','Box 352020','Yes','Bill Baseball','5431234','Boulder','CO','80309'),('C104','Women\'s Softball','Box 351200','Yes','Sue Softball','5434321','Boulder','CO','80309'),('C105','High School Football','123 AnyStreet','No','Coach Bob','4441234','Louisville','CO','80027');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE` (
  `empno` varchar(5) NOT NULL,
  `empname` varchar(25) NOT NULL,
  `department` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE`
--

LOCK TABLES `EMPLOYEE` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE` DISABLE KEYS */;
INSERT INTO `EMPLOYEE` VALUES ('E100','Chuck Coordinator','Administration','chuck@colorado.edu','3-1111'),('E101','Mary Manager','Football','mary@colorado.edu','5-1111'),('E102','Sally Supervisor','Planning','sally@colorado.edu','3-2222'),('E103','Alan Administrator','Administration','alan@colorado.edu','3-3333');
/*!40000 ALTER TABLE `EMPLOYEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventPlan`
--

DROP TABLE IF EXISTS `EventPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventPlan` (
  `planno` varchar(9) NOT NULL,
  `eventno` varchar(5) DEFAULT NULL,
  `workdate` date NOT NULL,
  `notes` varchar(30) DEFAULT NULL,
  `activity` varchar(30) NOT NULL,
  `empno` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`planno`),
  KEY `FK_EVENTNO` (`eventno`),
  KEY `FK_EMPNO` (`empno`),
  CONSTRAINT `EventPlan_ibfk_1` FOREIGN KEY (`eventno`) REFERENCES `EventRequest` (`EventNo`),
  CONSTRAINT `EventPlan_ibfk_2` FOREIGN KEY (`empno`) REFERENCES `EMPLOYEE` (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventPlan`
--

LOCK TABLES `EventPlan` WRITE;
/*!40000 ALTER TABLE `EventPlan` DISABLE KEYS */;
INSERT INTO `EventPlan` VALUES ('P100','E100','2018-10-25','Standard operation','Operation','E102'),('P101','E104','2018-12-03','Watch for gate crashers','Operation','E100'),('P102','E105','2018-12-05','Standard operation','Operation','E102'),('P103','E106','2018-12-12','Watch for seat switching','Operation',NULL),('P104','E101','2018-10-26','Standard cleanup','Cleanup','E101'),('P105','E100','2018-10-25','Light cleanup','Cleanup','E101'),('P199','E102','2018-12-10','Standard operation','Operation','E101'),('P299','E101','2018-10-26',NULL,'Operation','E101'),('P349','E106','2018-12-12',NULL,'Cleanup','E101'),('P85','E100','2018-10-25','Standard operation','Setup','E102'),('P95','E101','2018-10-26','Extra security','Setup','E102');
/*!40000 ALTER TABLE `EventPlan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventPlanLine`
--

DROP TABLE IF EXISTS `EventPlanLine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventPlanLine` (
  `planno` varchar(9) NOT NULL,
  `lineno` varchar(5) NOT NULL,
  `timestart` time NOT NULL,
  `timeend` time NOT NULL,
  `numberfld` int(11) NOT NULL,
  `locno` varchar(8) DEFAULT NULL,
  `resno` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`planno`,`lineno`),
  KEY `FK2_LOCNO` (`locno`),
  KEY `FK_RESNO` (`resno`),
  CONSTRAINT `EventPlanLine_ibfk_1` FOREIGN KEY (`planno`) REFERENCES `EventPlan` (`planno`),
  CONSTRAINT `EventPlanLine_ibfk_2` FOREIGN KEY (`locno`) REFERENCES `Location` (`LocNo`),
  CONSTRAINT `EventPlanLine_ibfk_3` FOREIGN KEY (`resno`) REFERENCES `Resource` (`resno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventPlanLine`
--

LOCK TABLES `EventPlanLine` WRITE;
/*!40000 ALTER TABLE `EventPlanLine` DISABLE KEYS */;
INSERT INTO `EventPlanLine` VALUES ('P100','1','08:00:00','17:00:00',2,'L100','R100'),('P100','2','12:00:00','17:00:00',2,'L101','R101'),('P100','3','07:00:00','16:30:00',1,'L102','R102'),('P100','4','18:00:00','22:00:00',2,'L100','R102'),('P101','1','18:00:00','20:00:00',2,'L103','R100'),('P101','2','18:30:00','19:00:00',4,'L105','R100'),('P101','3','19:00:00','20:00:00',2,'L103','R103'),('P102','1','18:00:00','19:00:00',2,'L103','R100'),('P102','2','18:00:00','21:00:00',4,'L105','R100'),('P102','3','19:00:00','22:00:00',2,'L103','R103'),('P103','1','18:00:00','21:00:00',2,'L103','R100'),('P103','2','18:00:00','21:00:00',4,'L105','R100'),('P103','3','19:00:00','22:00:00',2,'L103','R103'),('P104','1','18:00:00','22:00:00',4,'L101','R104'),('P104','2','18:00:00','22:00:00',4,'L100','R104'),('P105','1','18:00:00','22:00:00',4,'L101','R104'),('P105','2','18:00:00','22:00:00',4,'L100','R104'),('P199','1','08:00:00','12:00:00',1,'L100','R100'),('P349','1','12:00:00','15:30:00',1,'L103','R100'),('P85','1','09:00:00','17:00:00',5,'L100','R100'),('P85','2','08:00:00','17:00:00',2,'L102','R101'),('P85','3','10:00:00','15:00:00',3,'L104','R100'),('P95','1','08:00:00','17:00:00',4,'L100','R100'),('P95','2','09:00:00','17:00:00',4,'L102','R101'),('P95','3','10:00:00','15:00:00',4,'L106','R100'),('P95','4','13:00:00','17:00:00',2,'L100','R103'),('P95','5','13:00:00','17:00:00',2,'L101','R104');
/*!40000 ALTER TABLE `EventPlanLine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventRequest`
--

DROP TABLE IF EXISTS `EventRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventRequest` (
  `EventNo` varchar(5) NOT NULL,
  `dateheld` date NOT NULL,
  `datereq` date NOT NULL,
  `FacNo` varchar(8) DEFAULT NULL,
  `CustNo` varchar(8) DEFAULT NULL,
  `dateauth` date DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `estcost` decimal(13,2) NOT NULL,
  `estaudience` decimal(10,0) DEFAULT NULL,
  `budno` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`EventNo`),
  KEY `FK2_FACNO` (`FacNo`),
  KEY `FK2_CUSTNO` (`CustNo`),
  CONSTRAINT `FK2_CUSTNO` FOREIGN KEY (`CustNo`) REFERENCES `Customer` (`CustNo`),
  CONSTRAINT `FK2_FACNO` FOREIGN KEY (`FacNo`) REFERENCES `Facility` (`FacNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventRequest`
--

LOCK TABLES `EventRequest` WRITE;
/*!40000 ALTER TABLE `EventRequest` DISABLE KEYS */;
INSERT INTO `EventRequest` VALUES ('E100','2018-10-25','2018-06-06','F100','C100','2018-06-08','Approved',5000.00,80000,'B1000'),('E101','2018-10-26','2018-07-28','F100','C100',NULL,'Pending',5000.00,80000,'B1000'),('E102','2018-09-14','2018-07-28','F100','C100','2018-07-31','Approved',5000.00,80000,'B1000'),('E103','2018-09-21','2018-07-28','F100','C100','2018-08-01','Approved',5000.00,80000,'B1000'),('E104','2018-12-03','2018-07-28','F101','C101','2018-07-31','Approved',2000.00,12000,'B1000'),('E105','2018-12-05','2018-07-28','F101','C101','2018-08-01','Approved',2000.00,10000,'B1000'),('E106','2018-12-12','2018-07-28','F101','C101','2018-07-31','Approved',2000.00,10000,'B1000'),('E107','2018-11-23','2018-07-28','F100','C105','2018-07-31','Denied',10000.00,5000,NULL);
/*!40000 ALTER TABLE `EventRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Facility`
--

DROP TABLE IF EXISTS `Facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Facility` (
  `FacNo` varchar(8) NOT NULL,
  `FacName` varchar(30) NOT NULL,
  PRIMARY KEY (`FacNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Facility`
--

LOCK TABLES `Facility` WRITE;
/*!40000 ALTER TABLE `Facility` DISABLE KEYS */;
INSERT INTO `Facility` VALUES ('F100','Football stadium'),('F101','Basketball arena'),('F102','Baseball field'),('F103','Recreation room');
/*!40000 ALTER TABLE `Facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `LocNo` varchar(8) NOT NULL,
  `FacNo` varchar(8) NOT NULL,
  `LocName` varchar(30) NOT NULL,
  PRIMARY KEY (`LocNo`),
  KEY `FK_FACNO` (`FacNo`),
  CONSTRAINT `FK_FACNO` FOREIGN KEY (`FacNo`) REFERENCES `Facility` (`FacNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES ('L100','F100','Locker room'),('L101','F100','Plaza'),('L102','F100','Vehicle gate'),('L103','F101','Locker room'),('L104','F100','Ticket Booth'),('L105','F101','Gate'),('L106','F100','Pedestrian gate');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resource`
--

DROP TABLE IF EXISTS `Resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Resource` (
  `resno` varchar(10) NOT NULL,
  `resname` varchar(25) NOT NULL,
  `rate` decimal(13,2) NOT NULL,
  PRIMARY KEY (`resno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resource`
--

LOCK TABLES `Resource` WRITE;
/*!40000 ALTER TABLE `Resource` DISABLE KEYS */;
INSERT INTO `Resource` VALUES ('R100','attendant',10.00),('R101','police',15.00),('R102','usher',10.00),('R103','nurse',20.00),('R104','janitor',15.00),('R105','food service',10.00);
/*!40000 ALTER TABLE `Resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-23 14:49:07
