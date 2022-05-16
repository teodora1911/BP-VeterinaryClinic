CREATE DEFINER=`root`@`localhost` TRIGGER `examinationhasservice_AFTER_INSERT` AFTER INSERT ON `examinationhasservice` FOR EACH ROW BEGIN
	DECLARE vMedicine INT;
    DECLARE vQuantitySM INT;
    DECLARE done BOOL DEFAULT FALSE;
    DECLARE cServiceMedicine CURSOR FOR SELECT Medicine, Quantity FROM servicehasmedicine WHERE Service = new.Service;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cServiceMedicine;
    medicineLoop: LOOP
		FETCH cServiceMedicine INTO vMedicine, vQuantitySM;
        IF done THEN
			LEAVE medicineLoop;
		END IF;
        UPDATE medicine SET medicine.Quantity = medicine.Quantity - (new.Quantity * vQuantitySM) WHERE medicine.IDMedicine = vMedicine;
	END LOOP medicineLoop;
    CLOSE cServiceMedicine;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `examinationhasservice_AFTER_UPDATE` AFTER UPDATE ON `examinationhasservice` FOR EACH ROW BEGIN
	DECLARE vMedicine INT;
    DECLARE vQuantitySM INT;
    DECLARE done BOOL DEFAULT FALSE;
    DECLARE cServiceMedicine CURSOR FOR SELECT Medicine, Quantity FROM servicehasmedicine WHERE Service = new.Service;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cServiceMedicine;
    medicineLoop: LOOP
		FETCH cServiceMedicine INTO vMedicine, vQuantitySM;
        IF done THEN
			LEAVE medicineLoop;
		END IF;
        IF(new.Quantity < old.Quantity) THEN 
			UPDATE medicine SET medicine.Quantity = medicine.Quantity + ((old.Quantity - new.Quantity) * vQuantitySM) WHERE medicine.IDMedicine = vMedicine;
		ELSE 
			UPDATE medicine SET medicine.Quantity = medicine.Quantity - ((new.Quantity - old.Quantity) * vQuantitySM) WHERE medicine.IDMedicine = vMedicine;
        END IF;
	END LOOP medicineLoop;
    CLOSE cServiceMedicine;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `examinationhasservice_AFTER_DELETE` AFTER DELETE ON `examinationhasservice` FOR EACH ROW BEGIN
	DECLARE vMedicine INT;
    DECLARE vQuantitySM INT;
    DECLARE done BOOL DEFAULT FALSE;
    DECLARE cServiceMedicine CURSOR FOR SELECT Medicine, Quantity FROM servicehasmedicine WHERE Service = old.Service;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cServiceMedicine;
    medicineLoop: LOOP
		FETCH cServiceMedicine INTO vMedicine, vQuantitySM;
        IF done THEN
			LEAVE medicineLoop;
		END IF;
        UPDATE medicine SET medicine.Quantity = medicine.Quantity + (old.Quantity * vQuantitySM) WHERE medicine.IDMedicine = vMedicine;
	END LOOP medicineLoop;
    CLOSE cServiceMedicine;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `spentmedicine_AFTER_INSERT` AFTER INSERT ON `spentmedicine` FOR EACH ROW BEGIN
	UPDATE medicine SET medicine.Quantity = medicine.Quantity - new.Quantity WHERE medicine.IDMedicine = new.Medicine;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `spentmedicine_AFTER_UPDATE` AFTER UPDATE ON `spentmedicine` FOR EACH ROW BEGIN
	IF(old.Quantity < new.Quantity) THEN
		UPDATE medicine SET medicine.Quantity = medicine.Quantity - (new.Quantity - old.Quantity) WHERE medicine.IDMedicine = new.Medicine;
	ELSE 
		UPDATE medicine SET medicine.Quantity = medicine.Quantity + (old.Quantity - new.Quantity) WHERE medicine.IDMedicine = new.Medicine;
	END IF;
END

CREATE DEFINER=`root`@`localhost` TRIGGER `spentmedicine_AFTER_DELETE` AFTER DELETE ON `spentmedicine` FOR EACH ROW BEGIN
	UPDATE medicine SET medicine.Quantity = medicine.Quantity + old.Quantity WHERE medicine.IDMedicine = old.Medicine;
END