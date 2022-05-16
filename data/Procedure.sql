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
-- Dumping routines for database 'bpprojektnizadatak'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_appointment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_appointment`(in pName varchar(255), in pSurname varchar (255), in pEmail varchar (255), in pPhoneNumber varchar(50), in pIDVeterinarian int, in pDate date, in pReason longtext, out pStatus tinyint, out pMessage varchar(255))
BEGIN
	DECLARE selectedID INT DEFAULT NULL;
    SET pStatus = FALSE;
	CALL add_petowner(pName, pSurname, pEmail, pPhoneNumber, selectedID);
    IF selectedID IS NULL THEN
		SET pMessage = 'Greska pri zakazivanju termina';
	ELSE 
		INSERT INTO appointment (PetOwner, Veterinarian, Date, EntryReason) VALUES (selectedID, pIDVeterinarian, pDate, pReason);
        SET pStatus = TRUE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_examination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_examination`(IN pIDVet INT, IN pIDPet INT, IN pDate DATE, IN pTime TIME, IN pDescription LONGTEXT, IN pApp INT, OUT pStatus TINYINT, OUT pMessage VARCHAR(1023))
BEGIN
	DECLARE idOwner INT DEFAULT NULL;
    DECLARE petTmp INT DEFAULT NULL;
    DECLARE vetTmp INT DEFAULT NULL;
    IF (pApp != 0) AND ((SELECT Scheduled FROM appointment WHERE IDAppointment = pApp) != 1) THEN 
		SELECT Veterinarian INTO vetTmp FROM appointment WHERE IDAppointment = pApp;
	END IF;
    IF (vetTmp IS NOT NULL) AND (vetTmp = pIDVet) AND ((SELECT COUNT(*) FROM examination WHERE Date = pDate AND Time = pTime AND Veterinarian = pIDVet) = 0) THEN
		IF (pIDPet = 0) AND (pApp = 0) THEN 
			SET pStatus = FALSE; SET pMessage = 'Niste unijeli validne podatke';
		ELSEIF (pIDPet = 0) AND (pApp != 0) THEN
			SELECT PetOwner INTO idOwner FROM appointment WHERE IDAppointment = pApp;
            INSERT INTO pet(Owner) VALUES (idOwner);
            SELECT MAX(IDPet) INTO petTmp FROM pet;
            SET pStatus = TRUE; SET pMessage = 'Ljubimac je uspjesno dodat.';
		ELSE
			SET petTmp = pIDPet;
            SET pStatus = TRUE;
		END IF;
        IF (pStatus = TRUE) AND (petTmp IS NOT NULL) AND (pIDVet != 0) THEN
			INSERT INTO examination(Veterinarian, Pet, Date, Time, Description, Appointment) VALUES (pIDVet, petTmp, pDate, pTime, pDescription, pApp);
            IF (pApp != 0) THEN 
				UPDATE appointment SET Scheduled = TRUE WHERE IDAppointment = pApp;
			END IF;
            SET pStatus = TRUE;
            SET pMessage = CONCAT(pMessage, ' Pregled je uspjesno dodat.');
		ELSE 
			SET pStatus = FALSE;
            SET pMessage = CONCAT(pMessage, 'Pregled nije uspjesno dodat.');
		END IF;
	ELSE
		SET pStatus = FALSE;
        SET pMessage = CONCAT(pMessage,' Nevalidni parametri veterinara/Postoji vec zakazan termin.');
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_examination_bill` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_examination_bill`(IN charge DECIMAL, IN tmstmp TIMESTAMP, IN paymentType INT, IN idExamination INT, IN idOwner INT, OUT pStatus TINYINT)
BEGIN
	IF((SELECT COUNT(*) FROM examinationbill WHERE Examination = idExamination) != 0) THEN
		SET pStatus = FALSE;
	ELSE
		INSERT INTO examinationbill (TotalPrice, TimeStamp, PaymentType, Examination, PetOwner) VALUES (charge, tmstmp, paymentType, idExamination, idOwner);
        SET pStatus = TRUE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_petowner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_petowner`(in pName varchar(255), in pSurname varchar(255), in pEmail varchar(255), in pPhoneNumber varchar(50), out pCurrentID int)
BEGIN
	DECLARE selectedID INT DEFAULT NULL;
    SELECT IDPetOwner INTO selectedID FROM petowner WHERE Email = pEmail;
    IF selectedID IS NULL THEN
		INSERT INTO petowner (Name, Surname, Email, PhoneNumber) VALUES (pName, pSurname, pEmail, pPhoneNumber);
        SELECT MAX(IDPetOwner) INTO pCurrentID FROM petowner;
	ELSE 
		UPDATE petowner SET Name = pName, Surname = pSurname, PhoneNumber = pPhoneNumber WHERE IDPetOwner=selectedID;
        SET pCurrentID = selectedID;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_pet_breed` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_pet_breed`(IN vPet INT, IN vBreed INT)
BEGIN
	IF((SELECT COUNT(*) FROM pethasbreed WHERE Pet = vPet AND Breed = vBreed) = 0) THEN
		INSERT INTO pethasbreed (Pet, Breed) VALUES (vPet, vBreed);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_service_examination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_service_examination`(IN pExamination INT, IN pService INT, IN pQuantity INT, OUT pStatus TINYINT)
BEGIN
	DECLARE done BOOL DEFAULT FALSE;
    DECLARE vMedicine INT;
    DECLARE vQuantity INT;
    DECLARE medQuantity INT;
    DECLARE loopStatus BOOL;
    DECLARE scost DECIMAL;
    DECLARE serviceMedicine CURSOR FOR SELECT Medicine, Quantity FROM servicehasmedicine WHERE Service = pService;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    IF((SELECT COUNT(*) FROM examinationhasservice WHERE Examination = pExamination AND Service = pService) != 0) THEN
		SET pStatus = FALSE;
	ELSE 
		OPEN serviceMedicine;
        medicineLoop: LOOP
			FETCH serviceMedicine INTO vMedicine, vQuantity;
            IF done THEN
				LEAVE medicineLoop;
			END IF;
            SELECT Quantity INTO medQuantity FROM medicine WHERE IDMedicine = vMedicine;
            IF(medQuantity < (pQuantity * vQuantity)) THEN
				SET done = TRUE;
                SET loopStatus = FALSE;
			END IF;
		END LOOP medicineLoop;
        CLOSE serviceMedicine;
        
        IF NOT loopStatus THEN 
			SET pStatus = FALSE;
		ELSE
			SELECT Cost INTO scost FROM service WHERE IDService = pService;
            IF (scost IS NOT NULL) THEN
				INSERT INTO examinationhasservice(Examination, Service, Quantity, Cost) VALUES (pExamination, pService, pQuantity, scost);
                SET pStatus = TRUE;
			ELSE
				SET pStatus = FALSE;
			END IF;
		END IF;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_spent_medicine` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_spent_medicine`(IN pExamination INT, IN pMedicine INT, IN pQuantity INT, OUT pStatus TINYINT)
BEGIN
	DECLARE stock_num INT;
    IF((SELECT COUNT(*) FROM spentmedicine WHERE Examination = pExamination AND Medicine = pMedicine) != 0) THEN
		CALL update_spent_medicine(pExamination, pMedicine, pQuantity, pStatus);
	ELSE
		SELECT m.Quantity INTO stock_num FROM medicine m WHERE m.IDMedicine = pMedicine;
		IF (stock_num IS NOT NULL) AND (stock_num > pQuantity) THEN 
			INSERT INTO spentmedicine (Examination, Medicine, Quantity) VALUES (pExamination, pMedicine, pQuantity);
			SET pStatus = TRUE;
		ELSE 
			SET pStatus = FALSE;
		END IF;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_treatment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_treatment`(IN pExamination INT, IN pMedicine INT, IN pName VARCHAR(255), IN pDose INT, IN pFrequency VARCHAR(255), IN pDate DATE, IN pDuration INT, IN pInstructions LONGTEXT, OUT pStatus TINYINT)
BEGIN
	IF((SELECT COUNT(*) FROM treatment WHERE Examination = pExamination AND Medicine = pMedicine) != 0) THEN
		CALL update_treatment(pExamination, pMedicine, pName, pDose, pFrequency, pDate, pDuration, pInstructions, pStatus);
	ELSE 
		INSERT INTO treatment (Examination, Medicine, Name, Dose, Frequency, StartDate, Duration, Instructions) VALUES (pExamination, pMedicine, pName, pDose, pFrequency, pDate, pDuration, pInstructions);
        SET pStatus = TRUE;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `search_medicine` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_medicine`(in pIDManufacturer int, in pIDType int)
BEGIN
	IF pIDManufacturer = 0 AND pIDType = 0 THEN
		SELECT * FROM all_medicine_view;
	ELSEIF pIDManufacturer = 0 AND (pIDType <> 0) THEN 
		SELECT * FROM all_medicine_view WHERE IDType = pIDType;
	ELSEIF pIDType = 0 AND (pIDManufacturer <> 0) THEN
		SELECT * FROM all_medicine_view WHERE IDManufacturer = pIDManufacturer;
	ElSE 
		SELECT * FROM all_medicine_view WHERE IDType = pIDType AND IDManufacturer = pIDManufacturer;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `total_cost_examination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `total_cost_examination`(IN id INT, OUT totalCost DECIMAL)
BEGIN
    DECLARE smCost INT;
    DECLARE serviceCost INT;
	SET totalCost = 0;
    # SpentMedicine
    SELECT SUM(sm.Quantity * medicine.Price) INTO smCost FROM spentmedicine sm JOIN medicine ON medicine.IDMedicine = sm.Medicine WHERE sm.Examination = id;
    # Services
    SELECT SUM(ehs.Cost * ehs.Quantity) INTO serviceCost FROM examinationhasservice ehs WHERE ehs.Examination = id;
    IF(smCost IS NOT NULL) THEN 
		SET totalCost = totalCost + smCost;
	END IF;
    IF(serviceCost IS NOT NULL) THEN
		SET totalCost = totalCost + serviceCost;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_examination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_examination`(IN pID INT, IN pDate DATE, IN pTime TIME, IN pDescription LONGTEXT, IN pCity INT, IN pStreet VARCHAR(255), IN pNumber VARCHAR(50), IN pCompleted TINYINT, OUT pStatus TINYINT)
BEGIN
	DECLARE idAddr INT;
	IF((SELECT COUNT(*) FROM address WHERE Street = pStreet AND Number = pNumber AND City = pCity) = 0) THEN
		INSERT INTO address (Street, Number, City) VALUES (pStreet, pNumber, pCity);
        SELECT MAX(IDAddress) INTO idAddr FROM address;
	ELSE
		SELECT IDAddress INTO idAddr FROM address WHERE Street = pStreet AND Number = pNumber AND City = pCity;
	END IF;
    IF (idAddr IS NOT NULL) THEN
		UPDATE examination SET Date = pDate, Time = pTime, Description = pDescription, Place = idAddr, Completed = pCompleted WHERE IDExamination = pID;
        SET pStatus = TRUE;
	ELSE 
		SET pStatus = FALSE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_pet` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_pet`(IN pIDPet INT, IN pName VARCHAR(255), IN pDate DATE, IN pEstimatedAge INT, IN pGender INT, IN pWeight DOUBLE, IN pSpecies INT, IN pHealthCondition LONGTEXT, IN pDiagnosis LONGTEXT, OUT pStatus TINYINT)
BEGIN
	IF((SELECT COUNT(pIDPet) FROM pet WHERE IDPet = pIDPet) != 0) THEN
		UPDATE pet SET Name = pName, Birthdate = pDate, EstimatedAge = pEstimatedAge, Gender = pGender, Weight = pWeight, Species = pSpecies, HealthCondition = pHealthCondition, Diagnosis = pDiagnosis WHERE IDPet = pIDPet;
        SET pStatus = TRUE;
	ELSE
		SET pStatus = FALSE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_service_examination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_service_examination`(IN pExamination INT, IN pService INT, IN newQuantity INT, OUT pStatus TINYINT)
BEGIN
	DECLARE oldQuantity INT DEFAULT NULL;
    DECLARE done BOOL DEFAULT FALSE;
    DECLARE vMedicine INT;
    DECLARE vQuantity INT;
    DECLARE medQuantity INT;
    DECLARE loopStatus BOOL DEFAULT TRUE;
	DECLARE serviceMedicine CURSOR FOR SELECT Medicine, Quantity FROM servicehasmedicine WHERE Service = pService;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    SELECT Quantity INTO oldQuantity FROM examinationhasservice WHERE Examination = pExamination AND Service = pService;
    IF (oldQuantity IS NOT NULL) THEN
		IF(newQuantity < oldQuantity) THEN
			UPDATE examinationhasservice SET Quantity = newQuantity WHERE Examination = pExamination AND Service = pService;
            SET pStatus = TRUE;
		ELSE 
			OPEN serviceMedicine;
			medicineLoop: LOOP
			FETCH serviceMedicine INTO vMedicine, vQuantity;
            IF done THEN
				LEAVE medicineLoop;
			END IF;
            SELECT Quantity INTO medQuantity FROM medicine WHERE IDMedicine = vMedicine;
            IF(medQuantity < ((newQuantity - oldQuantity) * vQuantity)) THEN
				SET done = TRUE;
                SET loopStatus = FALSE;
			END IF;
			END LOOP medicineLoop;
			CLOSE serviceMedicine;
            
            IF NOT loopStatus THEN
				SET pStatus = FALSE;
			ELSE 
				UPDATE examinationhasservice SET Quantity = newQuantity WHERE Examination = pExamination AND Service = pService;
                SET pStatus = TRUE;
            END IF;
		END IF;
	ELSE
		SET pStatus = FALSE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_spent_medicine` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_spent_medicine`(IN pExamination INT, IN pMedicine INT, IN pQuantity INT, OUT pStatus TINYINT)
BEGIN
	DECLARE old_quantity INT;
    DECLARE stock_num INT;
    SELECT m.Quantity INTO stock_num FROM medicine m WHERE m.IDMedicine = pMedicine;
    SELECT sm.Quantity INTO old_quantity FROM spentmedicine sm WHERE sm.Examination = pExamination AND sm.Medicine = pMedicine;
    IF (old_quantity IS NOT NULL) AND (stock_num IS NOT NULL) AND ((pQuantity < old_quantity) OR ((pQuantity - old_quantity) < stock_num)) THEN
		UPDATE spentmedicine SET Quantity = pQuantity WHERE Examination = pExamination AND Medicine = pMedicine;
        SET pStatus = TRUE;
	ELSE 
		SET pStatus = FALSE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_treatment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_treatment`(IN pExamination INT, IN pMedicine INT, IN pName VARCHAR(255), IN pDose INT, IN pFrequency VARCHAR(255), IN pDate DATE, IN pDuration INT, IN pInstructions LONGTEXT, OUT pStatus TINYINT)
BEGIN
	IF((SELECT COUNT(*) FROM treatment WHERE Examination = pExamination AND Medicine = pMedicine) != 0) THEN 
		UPDATE treatment SET Name = pName, Dose = pDose, Frequency = pFrequency, StartDate = pDate, Duration = pDuration, Instructions = pInstructions WHERE Examination = pExamination AND Medicine = pMedicine;
        SET pStatus = TRUE;
    ELSE 
		SET pStatus = FALSE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `veterinarian_authentication` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `veterinarian_authentication`(IN pUsername VARCHAR(255), IN pPassword VARCHAR(1023), OUT pStatus TINYINT, OUT pIDVet INT, OUT pName VARCHAR(255), OUT pSurname VARCHAR(255))
BEGIN
	SET pStatus = FALSE;
    SELECT IDVeterinarian, Name, Surname INTO pIDVet, pName, pSurname FROM veterinarian WHERE Username = pUsername AND Password = pPassword;
    IF (pIDVet IS NOT NULL) THEN 
		SET pStatus = TRUE;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 13:27:34
