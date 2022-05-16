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
-- Temporary view structure for view `all_medicine_view`
--

DROP TABLE IF EXISTS `all_medicine_view`;
/*!50001 DROP VIEW IF EXISTS `all_medicine_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_medicine_view` AS SELECT 
 1 AS `IDMedicine`,
 1 AS `Name`,
 1 AS `Price`,
 1 AS `Manufacturer`,
 1 AS `Quantity`,
 1 AS `MedicineType`,
 1 AS `IDManufacturer`,
 1 AS `IDType`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `appointments_view`
--

DROP TABLE IF EXISTS `appointments_view`;
/*!50001 DROP VIEW IF EXISTS `appointments_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `appointments_view` AS SELECT 
 1 AS `IDAppointment`,
 1 AS `IDPetOwner`,
 1 AS `Name`,
 1 AS `Surname`,
 1 AS `Email`,
 1 AS `PhoneNumber`,
 1 AS `Date`,
 1 AS `EntryReason`,
 1 AS `Scheduled`,
 1 AS `Veterinarian`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `examination_view`
--

DROP TABLE IF EXISTS `examination_view`;
/*!50001 DROP VIEW IF EXISTS `examination_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `examination_view` AS SELECT 
 1 AS `IDExamination`,
 1 AS `PetName`,
 1 AS `OwnerName`,
 1 AS `OwnerSurname`,
 1 AS `Date`,
 1 AS `Time`,
 1 AS `Description`,
 1 AS `Street`,
 1 AS `StreetNumber`,
 1 AS `City`,
 1 AS `Completed`,
 1 AS `IDPet`,
 1 AS `IDOwner`,
 1 AS `IDVeterinarian`,
 1 AS `IDAddr`,
 1 AS `IDCity`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `examinationhasservice_view`
--

DROP TABLE IF EXISTS `examinationhasservice_view`;
/*!50001 DROP VIEW IF EXISTS `examinationhasservice_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `examinationhasservice_view` AS SELECT 
 1 AS `IDExamination`,
 1 AS `IDService`,
 1 AS `ServiceName`,
 1 AS `ServiceCost`,
 1 AS `Quantity`,
 1 AS `Cost`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `pet_details_view`
--

DROP TABLE IF EXISTS `pet_details_view`;
/*!50001 DROP VIEW IF EXISTS `pet_details_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `pet_details_view` AS SELECT 
 1 AS `IDPet`,
 1 AS `Name`,
 1 AS `Birtdate`,
 1 AS `EstimatedAge`,
 1 AS `Gender`,
 1 AS `Weight`,
 1 AS `Species`,
 1 AS `HealthCondition`,
 1 AS `Diagnosis`,
 1 AS `IDOwner`,
 1 AS `IDGender`,
 1 AS `IDSpecies`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `spent_medicine_view`
--

DROP TABLE IF EXISTS `spent_medicine_view`;
/*!50001 DROP VIEW IF EXISTS `spent_medicine_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `spent_medicine_view` AS SELECT 
 1 AS `IDExamination`,
 1 AS `IDMedicine`,
 1 AS `MedicineName`,
 1 AS `IDMedicineType`,
 1 AS `MedicineType`,
 1 AS `Quantity`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `treatment_view`
--

DROP TABLE IF EXISTS `treatment_view`;
/*!50001 DROP VIEW IF EXISTS `treatment_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `treatment_view` AS SELECT 
 1 AS `IDExamination`,
 1 AS `IDMedicine`,
 1 AS `MedicineName`,
 1 AS `IDMedicineType`,
 1 AS `MedicineType`,
 1 AS `Name`,
 1 AS `Dose`,
 1 AS `Frequency`,
 1 AS `StartDate`,
 1 AS `Duration`,
 1 AS `Instructions`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `veterinarian_services_view`
--

DROP TABLE IF EXISTS `veterinarian_services_view`;
/*!50001 DROP VIEW IF EXISTS `veterinarian_services_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `veterinarian_services_view` AS SELECT 
 1 AS `IDVeterinarian`,
 1 AS `IDService`,
 1 AS `ServiceName`,
 1 AS `ServiceCost`,
 1 AS `ServiceDesc`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `all_medicine_view`
--

/*!50001 DROP VIEW IF EXISTS `all_medicine_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_medicine_view` AS select `drug`.`IDMedicine` AS `IDMedicine`,`drug`.`Name` AS `Name`,`drug`.`Price` AS `Price`,`manufacturer`.`Name` AS `Manufacturer`,`drug`.`Quantity` AS `Quantity`,`medicinetype`.`Type` AS `MedicineType`,`manufacturer`.`IDManufacturer` AS `IDManufacturer`,`medicinetype`.`IDMedicineType` AS `IDType` from ((`medicine` `drug` join `manufacturer` on((`drug`.`Manufacturer` = `manufacturer`.`IDManufacturer`))) join `medicinetype` on((`drug`.`Type` = `medicinetype`.`IDMedicineType`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `appointments_view`
--

/*!50001 DROP VIEW IF EXISTS `appointments_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `appointments_view` AS select `app`.`IDAppointment` AS `IDAppointment`,`petowner`.`IDPetOwner` AS `IDPetOwner`,`petowner`.`Name` AS `Name`,`petowner`.`Surname` AS `Surname`,`petowner`.`Email` AS `Email`,`petowner`.`PhoneNumber` AS `PhoneNumber`,`app`.`Date` AS `Date`,`app`.`EntryReason` AS `EntryReason`,`app`.`Scheduled` AS `Scheduled`,`app`.`Veterinarian` AS `Veterinarian` from (`appointment` `app` join `petowner` on((`app`.`PetOwner` = `petowner`.`IDPetOwner`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `examination_view`
--

/*!50001 DROP VIEW IF EXISTS `examination_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `examination_view` AS select `ex`.`IDExamination` AS `IDExamination`,`pet`.`Name` AS `PetName`,`petowner`.`Name` AS `OwnerName`,`petowner`.`Surname` AS `OwnerSurname`,`ex`.`Date` AS `Date`,`ex`.`Time` AS `Time`,`ex`.`Description` AS `Description`,`address`.`Street` AS `Street`,`address`.`Number` AS `StreetNumber`,`city`.`Name` AS `City`,`ex`.`Completed` AS `Completed`,`pet`.`IDPet` AS `IDPet`,`petowner`.`IDPetOwner` AS `IDOwner`,`veterinarian`.`IDVeterinarian` AS `IDVeterinarian`,`address`.`IDAddress` AS `IDAddr`,`city`.`IDCity` AS `IDCity` from (((((`examination` `ex` join `pet` on(((`ex`.`Pet` = `pet`.`IDPet`) or (`ex`.`Pet` is null)))) join `veterinarian` on(((`ex`.`Veterinarian` = `veterinarian`.`IDVeterinarian`) or (`ex`.`Veterinarian` is null)))) left join `address` on((`ex`.`Place` = `address`.`IDAddress`))) left join `city` on((`address`.`City` = `city`.`IDCity`))) left join `petowner` on((`pet`.`Owner` = `petowner`.`IDPetOwner`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `examinationhasservice_view`
--

/*!50001 DROP VIEW IF EXISTS `examinationhasservice_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `examinationhasservice_view` AS select `ehs`.`Examination` AS `IDExamination`,`ehs`.`Service` AS `IDService`,`service`.`Name` AS `ServiceName`,`service`.`Cost` AS `ServiceCost`,`ehs`.`Quantity` AS `Quantity`,`ehs`.`Cost` AS `Cost` from (`examinationhasservice` `ehs` join `service` on((`service`.`IDService` = `ehs`.`Service`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pet_details_view`
--

/*!50001 DROP VIEW IF EXISTS `pet_details_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pet_details_view` AS select `p`.`IDPet` AS `IDPet`,`p`.`Name` AS `Name`,`p`.`Birthdate` AS `Birtdate`,`p`.`EstimatedAge` AS `EstimatedAge`,`gender`.`Name` AS `Gender`,`p`.`Weight` AS `Weight`,`species`.`Name` AS `Species`,`p`.`HealthCondition` AS `HealthCondition`,`p`.`Diagnosis` AS `Diagnosis`,`p`.`Owner` AS `IDOwner`,`p`.`Gender` AS `IDGender`,`p`.`Species` AS `IDSpecies` from ((`pet` `p` left join `gender` on((`p`.`Gender` = `gender`.`IDGender`))) left join `species` on((`p`.`Species` = `species`.`IDSpecies`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `spent_medicine_view`
--

/*!50001 DROP VIEW IF EXISTS `spent_medicine_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `spent_medicine_view` AS select `sm`.`Examination` AS `IDExamination`,`sm`.`Medicine` AS `IDMedicine`,`medicine`.`Name` AS `MedicineName`,`medicinetype`.`IDMedicineType` AS `IDMedicineType`,`medicinetype`.`Type` AS `MedicineType`,`sm`.`Quantity` AS `Quantity` from ((`spentmedicine` `sm` join `medicine` on((`medicine`.`IDMedicine` = `sm`.`Medicine`))) join `medicinetype` on((`medicinetype`.`IDMedicineType` = (select `medicine`.`Type` from `medicine` where (`medicine`.`IDMedicine` = `sm`.`Medicine`))))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `treatment_view`
--

/*!50001 DROP VIEW IF EXISTS `treatment_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `treatment_view` AS select `t`.`Examination` AS `IDExamination`,`t`.`Medicine` AS `IDMedicine`,`medicine`.`Name` AS `MedicineName`,`medicinetype`.`IDMedicineType` AS `IDMedicineType`,`medicinetype`.`Type` AS `MedicineType`,`t`.`Name` AS `Name`,`t`.`Dose` AS `Dose`,`t`.`Frequency` AS `Frequency`,`t`.`StartDate` AS `StartDate`,`t`.`Duration` AS `Duration`,`t`.`Instructions` AS `Instructions` from ((`treatment` `t` join `medicine` on((`medicine`.`IDMedicine` = `t`.`Medicine`))) join `medicinetype` on((`medicinetype`.`IDMedicineType` = (select `medicine`.`Type` from `medicine` where (`medicine`.`IDMedicine` = `t`.`Medicine`))))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `veterinarian_services_view`
--

/*!50001 DROP VIEW IF EXISTS `veterinarian_services_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `veterinarian_services_view` AS select `p`.`Veterinarian` AS `IDVeterinarian`,`p`.`Service` AS `IDService`,`service`.`Name` AS `ServiceName`,`service`.`Cost` AS `ServiceCost`,`service`.`Description` AS `ServiceDesc` from (`provides` `p` join `service` on((`service`.`IDService` = `p`.`Service`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 13:37:26
