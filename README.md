# CS364Fitness
CREATE DATABASE `Fitness`;

USE `Fitness`;

CREATE TABLE `User`(
	`Username` NVARCHAR(20) PRIMARY KEY NOT NULL,
    `Password` NVARCHAR(20) NOT NULL,
    `EmailAddress` NVARCHAR(32) NOT NULL,
    `FirstName` NVARCHAR(24) NOT NULL,
    `MiddleName` NVARCHAR(24),
    `LastName` NVARCHAR(24) NOT NULL,
    `Location` NVARCHAR(24),
    Birthdate DATE 
);

CREATE TABLE `Sleep`(
	`Date` DATE PRIMARY KEY NOT NULL,
    Quality INT,
    Bedtime TIME,
    WakeTime TIME,
	FOREIGN KEY Username_Const(Username) REFERENCES Customer(Username) 
);

CREATE TABLE BodyMeasurements(
	`Date` DATE PRIMARY KEY NOT NULL,
    Weight INT,
    Height INT,
    BodyFatPercentage NUMERIC(2,2),
    BMI Numeric(2,2),
    WaistSize INT,
	FOREIGN KEY Username_Const(Username) REFERENCES Customer(Username) 
);

CREATE TABLE Activity(
	`Date` DATE PRIMARY KEY NOT NULL,
    Duration INT,
    CaloriesBurned INT,
    `Name` NVARCHAR(24),
    FOREIGN KEY Username_Const(Username) REFERENCES Customer(Username) 
);

CREATE TABLE Nutrition(
	`Date` DATE PRIMARY KEY NOT NULL,
    CalorieIntake INT,
    UnsaturatedFat INT,
    SaturatedFat INT,
    Protein INT,
    Fiber INT,
    Carbohydrate INT,
    FOREIGN KEY Username_Const(Username) REFERENCES Customer(Username) 
);
