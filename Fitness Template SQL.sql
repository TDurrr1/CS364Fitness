DROP DATABASE IF EXISTS `FitnessBuddy`;

CREATE DATABASE `FitnessBuddy`;

USE `FitnessBuddy`;
    
CREATE TABLE `User` (
	`UserID`       INT          PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`Username`     NVARCHAR(20) UNIQUE NOT NULL,
    `Password`     NVARCHAR(20) NOT NULL,
    `EmailAddress` NVARCHAR(32) NOT NULL,
    `FirstName`    NVARCHAR(24) NOT NULL,
    `MiddleName`   NVARCHAR(24),
    `LastName`     NVARCHAR(24) NOT NULL,
    `Location`     NVARCHAR(24),
    `Birthdate`    DATE
);

CREATE TABLE `Sleep` (
	`UserID`   INT  NOT NULL,
	`Date`     DATE PRIMARY KEY NOT NULL,
    `Quality`  INT,
    `Hours`  INT
);

CREATE TABLE `BodyMeasurements` (
	`UserID`            INT  NOT NULL,
	`Date`              DATE PRIMARY KEY NOT NULL,
    `Weight`            INT,
    `Height`            INT,
    `BodyFatPercentage` DECIMAL(4, 2),
    `BMI`               DECIMAL(4, 2),
    `WaistSize`         INT
);

CREATE TABLE `Activity` (
	`UserID`         INT  NOT NULL,
	`Date`           DATE PRIMARY KEY NOT NULL,
    `Duration`       INT,
    `CaloriesBurned` INT,
    `Name`           NVARCHAR(24)
);

CREATE TABLE `Nutrition` (
	`UserID`         INT  NOT NULL,
	`Date`           DATE PRIMARY KEY NOT NULL,
    `CalorieIntake`  INT,
    `UnsaturatedFat` INT,
    `SaturatedFat`   INT,
    `Protein`        INT,
    `Fiber`          INT,
    `Carbohydrate`   INT
);

-- Sample INSERT statement
-- INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`) VALUES (N'abc', N'123',N'@gmail',N'A',N'B', N'c');

-- Add in foreign key relationships

ALTER TABLE `Sleep` ADD CONSTRAINT `FK_SleepUserID`
    FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `Activity` ADD CONSTRAINT `FK_ActivityUserID`
    FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `BodyMeasurements` ADD CONSTRAINT `FK_BodyMeasurementsUserID`
    FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `Nutrition` ADD CONSTRAINT `FK_NutritionUserID`
    FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- DATA INSERTION

-- INSERTING 7 USERS

INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'WYenter', N'secretpassword',N'yenter.william@uwlax.edu',N'Will',N' ', N'Yenter', N'Grafton', DATE '1995-09-05');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'User2', N'unicorn3',N'myemail@gmail.com',N'Sally',N' ', N'Smith', N'La Crosse', DATE '1995-01-014');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'URFIRED', N'WALL',N'president@whitehouse.gov',N'Donald',N'John', N'Drumpf', N'Washington D.C.', DATE '1946-06-14');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'randysavage102', N'password123',N'randysavage102@gmail.com',N'Truman',N'Walter', N'Brown', N'Milwaukee', DATE '1991-08-03');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'Austin1', N'kachow',N'drift85@gmail.com',N'Austin',N'Larry', N'Schmidt', N'La Crosse', DATE '1996-07-27');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'User1', N'password',N'asdfqweori@gmail.com',N'Jane',N'Rose', N'Smith', N'La Crosse', DATE '1995-09-10');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'TDurr', N'1234567890',N'tylerdurr@gmail.com',N'Tyler',N' ', N'Durr', N'Edgar', DATE '1996-10-03');
INSERT INTO `User` (`Username`, `Password`, `EmailAddress`, `FirstName`,`MiddleName`, `LastName`, `Location`, `Birthdate`)
	VALUES (N'JoeHimself', N'lemonysnicket13',N'schmoe.joe@uwlax.edu',N'Joe',N' ', N'Schmoe', N'La Crosse', DATE '1987-01-02');

-- Nutrition table data

INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (2, '2016-12-31', 2251, 50, 22, 70, 22, 273);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (2, '2016-02-29', 1931, 52, 20, 59, 30, 266);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (3, '2016-01-01', 1542, 53, 24, 48, 20, 293);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (4, '2017-02-12', 1558, 43, 21, 56, 23, 251);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (4, '2017-04-03', 1986, 57, 18, 48, 25, 253);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (4, '2017-04-04', 1689, 53, 21, 52, 26, 267);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (5, '2017-04-05', 2431, 60, 24, 56, 30, 321);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (5, '2017-04-06', 1722, 52, 18, 48, 29, 264);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (5, '2017-04-07', 2330, 42, 21, 74, 27, 292);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (5, '2017-04-08', 2446, 41, 24, 68, 30, 253);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (6, '2017-04-09', 2073, 49, 15, 50, 23, 289);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (6, '2017-04-10', 2346, 41, 25, 74, 26, 285);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (7, '2017-04-11', 2473, 47, 24, 41, 25, 280);
INSERT INTO `Nutrition` (`UserID`, `Date`, `CalorieIntake`, `UnsaturatedFat`, `SaturatedFat`, `Protein`, `Fiber`, `Carbohydrate`)
	VALUES (7, '2017-04-12', 2392, 52, 15, 75, 29, 296);

INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (1, '2017-02-06', 4, 7);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (1, '2017-02-07', 5, 3);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (1, '2017-02-08', 6, 8);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (2, '2017-02-09', 5, 9);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (2, '2017-02-10', 9, 10);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (2, '2017-02-11', 10, 6);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (2, '2017-02-12', 2, '23:53:00', '7:51:00');
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (3, '2017-02-13', 1, 3);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (3, '2017-02-14', 3, 2);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (3, '2017-02-15', 7, 12);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (3, '2017-02-16', 5, 7);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (3, '2017-02-17', 10, 9);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-18', 4, 4);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-19', 6, 5);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-20', 1, 6);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-21', 7, 6);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-22', 4, 9);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-23', 2, 11);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (5, '2017-02-24', 4, 10);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (4, '2017-02-25', 8, 8);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (5, '2017-02-26', 7, 7);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (5, '2017-02-27', 6, 5);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (5, '2017-02-28', 10, 11);
INSERT INTO `Sleep` (`UserId`, `Date`, `Quality`, `Hours`)
	VALUES (5, '2017-03-01', 1, 3);

INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (1, '2017-02-06', 145, 70, 12.21, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (1, '2017-02-07', 145, 70, 12.11, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (1, '2017-02-08', 145, 70, 12.13, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (2, '2017-02-09', 145, 70, 12.23, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (2, '2017-02-10', 145, 70, 12.48, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (2, '2017-02-11', 145, 70, 12.56, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (2, '2017-02-12', 145, 70, 12.10, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (3, '2017-02-13', 145, 70, 12.09, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (3, '2017-02-14', 145, 70, 12.12, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (3, '2017-02-15', 145, 70, 12.14, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (3, '2017-02-16', 146, 70, 12.54, 20.83, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (3, '2017-02-17', 147, 70, 12.14, 20.85, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-18', 148, 70, 12.19, 20.85, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-19', 148, 70, 12.45, 20.85, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-20', 148, 70, 12.10, 20.85, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-21', 148, 70, 12.40, 20.85, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-22', 147, 70, 12.11, 20.83, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-23', 147, 70, 12.78, 20.83, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (5, '2017-02-24', 147, 70, 12.10, 20.83, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (4, '2017-02-25', 146, 70, 12.43, 20.84, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (5, '2017-02-26', 147, 70, 12.10, 20.83, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (5, '2017-02-27', 146, 70, 12.90, 20.84, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (5, '2017-02-28', 145, 70, 12.87, 20.80, 40);
INSERT INTO `BodyMeasurements` (`UserId`, `Date`, `Weight`, `Height`, `BodyFatPercentage`, `BMI`, `WaistSize`)
	VALUES (5, '2017-03-01', 145, 70, 12.74, 20.80, 40);
