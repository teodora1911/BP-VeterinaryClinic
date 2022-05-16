CREATE DATABASE  IF NOT EXISTS `bpprojektnizadatak` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bpprojektnizadatak`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: bpprojektnizadatak
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `IDAddress` int NOT NULL AUTO_INCREMENT,
  `Street` varchar(255) DEFAULT NULL,
  `Number` varchar(50) DEFAULT NULL,
  `City` int NOT NULL,
  PRIMARY KEY (`IDAddress`),
  KEY `fk_Address_City1_idx` (`City`),
  CONSTRAINT `FK_Address_City` FOREIGN KEY (`City`) REFERENCES `city` (`IDCity`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `IDAppointment` int NOT NULL AUTO_INCREMENT,
  `PetOwner` int NOT NULL,
  `Veterinarian` int NOT NULL,
  `Date` date NOT NULL,
  `EntryReason` longtext,
  `Scheduled` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDAppointment`),
  KEY `FK_PetOwner_idx` (`PetOwner`),
  KEY `FK_Veterinarian_idx` (`Veterinarian`),
  CONSTRAINT `FK_Appointment_PetOwner` FOREIGN KEY (`PetOwner`) REFERENCES `petowner` (`IDPetOwner`),
  CONSTRAINT `FK_Appointment_Veterinarian` FOREIGN KEY (`Veterinarian`) REFERENCES `veterinarian` (`IDVeterinarian`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breed`
--

DROP TABLE IF EXISTS `breed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `breed` (
  `IDBreed` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL COMMENT 'Belgijski ovčar ....',
  `Description` longtext,
  `Species` int DEFAULT NULL,
  PRIMARY KEY (`IDBreed`),
  KEY `FK_Species_idx` (`Species`),
  CONSTRAINT `FK_Breed_Species` FOREIGN KEY (`Species`) REFERENCES `species` (`IDSpecies`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breed`
--

LOCK TABLES `breed` WRITE;
/*!40000 ALTER TABLE `breed` DISABLE KEYS */;
/*!40000 ALTER TABLE `breed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `IDCity` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `ZIPCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IDCity`),
  UNIQUE KEY `ZIPCode_UNIQUE` (`ZIPCode`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination`
--

DROP TABLE IF EXISTS `examination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examination` (
  `IDExamination` int NOT NULL AUTO_INCREMENT,
  `Veterinarian` int NOT NULL,
  `Pet` int NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Description` longtext,
  `Place` int DEFAULT NULL,
  `Completed` tinyint DEFAULT '0',
  `Appointment` int DEFAULT NULL,
  PRIMARY KEY (`IDExamination`),
  KEY `Vet_ID_idx` (`Veterinarian`),
  KEY `FK_Pet_idx` (`Pet`),
  KEY `FK_Address_idx` (`Place`),
  KEY `fk_Examination_Appointment1_idx` (`Appointment`),
  CONSTRAINT `FK_Examination_Address` FOREIGN KEY (`Place`) REFERENCES `address` (`IDAddress`),
  CONSTRAINT `FK_Examination_Appointment` FOREIGN KEY (`Appointment`) REFERENCES `appointment` (`IDAppointment`),
  CONSTRAINT `FK_Examination_Pet` FOREIGN KEY (`Pet`) REFERENCES `pet` (`IDPet`),
  CONSTRAINT `FK_Examination_Vet` FOREIGN KEY (`Veterinarian`) REFERENCES `veterinarian` (`IDVeterinarian`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination`
--

LOCK TABLES `examination` WRITE;
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examinationbill`
--

DROP TABLE IF EXISTS `examinationbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examinationbill` (
  `Code` int NOT NULL AUTO_INCREMENT,
  `TotalPrice` decimal(10,0) DEFAULT NULL,
  `TimeStamp` timestamp NULL DEFAULT NULL,
  `PaymentType` int DEFAULT NULL,
  `Examination` int NOT NULL,
  `PetOwner` int NOT NULL,
  PRIMARY KEY (`Code`),
  KEY `FK_PaymentType_idx` (`PaymentType`),
  KEY `fk_ExaminationBill_Examination1_idx` (`Examination`),
  KEY `fk_ExaminationBill_PetOwner1_idx` (`PetOwner`),
  CONSTRAINT `FK_ExaminationBill_Examination` FOREIGN KEY (`Examination`) REFERENCES `examination` (`IDExamination`),
  CONSTRAINT `FK_ExaminationBill_PaymentType` FOREIGN KEY (`PaymentType`) REFERENCES `payment` (`IDPayment`),
  CONSTRAINT `FK_ExaminationBill_PetOwner` FOREIGN KEY (`PetOwner`) REFERENCES `petowner` (`IDPetOwner`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinationbill`
--

LOCK TABLES `examinationbill` WRITE;
/*!40000 ALTER TABLE `examinationbill` DISABLE KEYS */;
/*!40000 ALTER TABLE `examinationbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examinationhasservice`
--

DROP TABLE IF EXISTS `examinationhasservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examinationhasservice` (
  `Examination` int NOT NULL,
  `Service` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  `Cost` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`Examination`,`Service`),
  KEY `fk_Examination_has_Service_Service1_idx` (`Service`),
  KEY `fk_Examination_has_Service_Examination1_idx` (`Examination`),
  CONSTRAINT `FK_ExaminationHasService_Examination` FOREIGN KEY (`Examination`) REFERENCES `examination` (`IDExamination`),
  CONSTRAINT `FK_ExaminationHasService_Service` FOREIGN KEY (`Service`) REFERENCES `service` (`IDService`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinationhasservice`
--

LOCK TABLES `examinationhasservice` WRITE;
/*!40000 ALTER TABLE `examinationhasservice` DISABLE KEYS */;
/*!40000 ALTER TABLE `examinationhasservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `IDGender` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`IDGender`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `IDManufacturer` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Description` longtext,
  PRIMARY KEY (`IDManufacturer`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `IDMedicine` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Price` decimal(10,0) NOT NULL,
  `Description` longtext NOT NULL,
  `Manufacturer` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  `Type` int NOT NULL,
  `ManufactureDate` datetime NOT NULL,
  `ExpirationDate` datetime NOT NULL,
  PRIMARY KEY (`IDMedicine`),
  KEY `Brand_ID_idx` (`Manufacturer`),
  KEY `Type_ID_idx` (`Type`),
  CONSTRAINT `FK_Medicine_Manufacturer` FOREIGN KEY (`Manufacturer`) REFERENCES `manufacturer` (`IDManufacturer`),
  CONSTRAINT `FK_Medicine_MedicineType` FOREIGN KEY (`Type`) REFERENCES `medicinetype` (`IDMedicineType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicinetype`
--

DROP TABLE IF EXISTS `medicinetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicinetype` (
  `IDMedicineType` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) NOT NULL,
  PRIMARY KEY (`IDMedicineType`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinetype`
--

LOCK TABLES `medicinetype` WRITE;
/*!40000 ALTER TABLE `medicinetype` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicinetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `IDOrder` int NOT NULL AUTO_INCREMENT,
  `Customer` int DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `ShippingDate` datetime DEFAULT NULL,
  `Supplier` int NOT NULL,
  `PaymentType` int DEFAULT NULL,
  PRIMARY KEY (`IDOrder`),
  KEY `fk_Order_Supplier1_idx` (`Supplier`),
  KEY `FK_Payment_idx` (`PaymentType`),
  CONSTRAINT `FK_Order_Payment` FOREIGN KEY (`PaymentType`) REFERENCES `payment` (`IDPayment`),
  CONSTRAINT `FK_Order_Supplier` FOREIGN KEY (`Supplier`) REFERENCES `supplier` (`IDSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `Order` int NOT NULL,
  `Medicine` int NOT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`Order`,`Medicine`),
  KEY `fk_Order_has_Medicine_Medicine1_idx` (`Medicine`),
  KEY `fk_Order_has_Medicine_Order1_idx` (`Order`),
  CONSTRAINT `FK_OrderDetails_Medicine` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`IDMedicine`),
  CONSTRAINT `FK_OrderDetails_Order` FOREIGN KEY (`Order`) REFERENCES `order` (`IDOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `IDPayment` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPayment`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `IDPet` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `EstimatedAge` int DEFAULT NULL,
  `Gender` int DEFAULT NULL,
  `Weight` double DEFAULT NULL,
  `Owner` int NOT NULL,
  `Species` int DEFAULT NULL,
  `HealthCondition` longtext,
  `Diagnosis` longtext,
  PRIMARY KEY (`IDPet`),
  KEY `FK_Owner_idx` (`Owner`),
  KEY `FK_Species_idx` (`Species`),
  KEY `FK_Pet_Gender_idx` (`Gender`),
  CONSTRAINT `FK_Pet_Gender` FOREIGN KEY (`Gender`) REFERENCES `gender` (`IDGender`),
  CONSTRAINT `FK_Pet_PetOwner` FOREIGN KEY (`Owner`) REFERENCES `petowner` (`IDPetOwner`),
  CONSTRAINT `FK_Pet_Species` FOREIGN KEY (`Species`) REFERENCES `species` (`IDSpecies`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pethasbreed`
--

DROP TABLE IF EXISTS `pethasbreed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pethasbreed` (
  `Breed` int NOT NULL,
  `Pet` int NOT NULL,
  `Primary` tinyint DEFAULT NULL,
  PRIMARY KEY (`Breed`,`Pet`),
  KEY `FK_Pet` (`Pet`),
  KEY `FK_Breed` (`Breed`),
  CONSTRAINT `FK_PetHasBreed_Breed` FOREIGN KEY (`Breed`) REFERENCES `breed` (`IDBreed`),
  CONSTRAINT `FK_PetHasBreed_Pet` FOREIGN KEY (`Pet`) REFERENCES `pet` (`IDPet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pethasbreed`
--

LOCK TABLES `pethasbreed` WRITE;
/*!40000 ALTER TABLE `pethasbreed` DISABLE KEYS */;
/*!40000 ALTER TABLE `pethasbreed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petowner`
--

DROP TABLE IF EXISTS `petowner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petowner` (
  `IDPetOwner` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IDPetOwner`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petowner`
--

LOCK TABLES `petowner` WRITE;
/*!40000 ALTER TABLE `petowner` DISABLE KEYS */;
/*!40000 ALTER TABLE `petowner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provides`
--

DROP TABLE IF EXISTS `provides`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provides` (
  `Veterinarian` int NOT NULL,
  `Service` int NOT NULL,
  PRIMARY KEY (`Veterinarian`,`Service`),
  KEY `fk_Veterinarian_has_Service_Service1_idx` (`Service`),
  KEY `fk_Veterinarian_has_Service_Veterinarian1_idx` (`Veterinarian`),
  CONSTRAINT `FK_Provides_Service` FOREIGN KEY (`Service`) REFERENCES `service` (`IDService`),
  CONSTRAINT `FK_Provides_Veterinarian` FOREIGN KEY (`Veterinarian`) REFERENCES `veterinarian` (`IDVeterinarian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provides`
--

LOCK TABLES `provides` WRITE;
/*!40000 ALTER TABLE `provides` DISABLE KEYS */;
/*!40000 ALTER TABLE `provides` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `IDService` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Cost` decimal(10,0) DEFAULT NULL,
  `Description` longtext,
  PRIMARY KEY (`IDService`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicehasmedicine`
--

DROP TABLE IF EXISTS `servicehasmedicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicehasmedicine` (
  `Medicine` int NOT NULL,
  `Service` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`Medicine`,`Service`),
  KEY `fk_Medicine_has_Service_Service1_idx` (`Service`),
  KEY `fk_Medicine_has_Service_Medicine1_idx` (`Medicine`),
  CONSTRAINT `FK_ServiceHasMedicine_Medicine` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`IDMedicine`),
  CONSTRAINT `FK_ServiceHasMedicine_Service` FOREIGN KEY (`Service`) REFERENCES `service` (`IDService`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicehasmedicine`
--

LOCK TABLES `servicehasmedicine` WRITE;
/*!40000 ALTER TABLE `servicehasmedicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicehasmedicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialization` (
  `IDSpecialization` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Description` longtext,
  PRIMARY KEY (`IDSpecialization`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `IDSpecies` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL COMMENT 'Pas / Mačka ...',
  `Description` longtext,
  PRIMARY KEY (`IDSpecies`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spentmedicine`
--

DROP TABLE IF EXISTS `spentmedicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spentmedicine` (
  `Medicine` int NOT NULL,
  `Examination` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`Medicine`,`Examination`),
  KEY `fk_Medicine_has_Examination_Examination1_idx` (`Examination`),
  KEY `fk_Medicine_has_Examination_Medicine1_idx` (`Medicine`),
  CONSTRAINT `FK_SpentMedicine_Examination` FOREIGN KEY (`Examination`) REFERENCES `examination` (`IDExamination`),
  CONSTRAINT `FK_SpentMedicine_Medicine` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`IDMedicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spentmedicine`
--

LOCK TABLES `spentmedicine` WRITE;
/*!40000 ALTER TABLE `spentmedicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `spentmedicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `IDSupplier` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(1023) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Residence` int DEFAULT NULL,
  PRIMARY KEY (`IDSupplier`),
  KEY `FK_Address_idx` (`Residence`),
  CONSTRAINT `FK_Supplier_Address` FOREIGN KEY (`Residence`) REFERENCES `address` (`IDAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `title` (
  `Veterinarian` int NOT NULL,
  `Specialization` int NOT NULL,
  PRIMARY KEY (`Veterinarian`,`Specialization`),
  KEY `fk_Veterinarian_has_Specialization_Specialization1_idx` (`Specialization`),
  KEY `fk_Veterinarian_has_Specialization_Veterinarian1_idx` (`Veterinarian`),
  CONSTRAINT `FK_Title_Specialization` FOREIGN KEY (`Specialization`) REFERENCES `specialization` (`IDSpecialization`),
  CONSTRAINT `FK_Title_Veterinarian` FOREIGN KEY (`Veterinarian`) REFERENCES `veterinarian` (`IDVeterinarian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment` (
  `Name` varchar(255) DEFAULT NULL,
  `Dose` int DEFAULT NULL,
  `Frequency` varchar(255) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `Duration` int DEFAULT NULL,
  `Instructions` longtext,
  `Examination` int NOT NULL,
  `Medicine` int NOT NULL,
  PRIMARY KEY (`Examination`,`Medicine`),
  KEY `fk_Treatment_Examination1_idx` (`Examination`),
  KEY `fk_Treatment_Medicine1_idx` (`Medicine`),
  CONSTRAINT `FK_Treatment_Examination` FOREIGN KEY (`Examination`) REFERENCES `examination` (`IDExamination`),
  CONSTRAINT `FK_Treatment_Medicine` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`IDMedicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinarian`
--

DROP TABLE IF EXISTS `veterinarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinarian` (
  `IDVeterinarian` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `PhoneNumber` varchar(50) NOT NULL,
  `HomeNumber` varchar(50) DEFAULT NULL,
  `HomeAddress` int DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(1023) NOT NULL,
  PRIMARY KEY (`IDVeterinarian`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `FK_Address_idx` (`HomeAddress`),
  CONSTRAINT `FK_Veterinarian_Address` FOREIGN KEY (`HomeAddress`) REFERENCES `address` (`IDAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinarian`
--

LOCK TABLES `veterinarian` WRITE;
/*!40000 ALTER TABLE `veterinarian` DISABLE KEYS */;
/*!40000 ALTER TABLE `veterinarian` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 13:39:55
