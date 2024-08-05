-- Create Product Table
CREATE TABLE Product (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Product_Name VARCHAR(255) NOT NULL,
  Company_Name VARCHAR(255),
  Product_Id_C VARCHAR(255),
  Hazardous BOOLEAN,
  Approve VARCHAR(255),
  Date_Created DATE,
  Date_Reviewed DATE,
  Purpose VARCHAR(255),
  Photo_URL VARCHAR(255),
  Descriptions VARCHAR(1500),
  Quantity_Metric VARCHAR(255),
  Quantity_Numeric VARCHAR(255),
  Position VARCHAR(255),
  Room_Number INT
);

-- Create Test Table
CREATE TABLE Test (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Test_Name VARCHAR(255) NOT NULL,
  Test_Date DATE,
  Descriptions VARCHAR(1500),
  Result VARCHAR(255),
  Test_Sheet VARCHAR(255),
  Instrument_Id INT,
  FOREIGN KEY (Instrument_Id) REFERENCES Instrument(Id)
);

-- Create ProductTest Table
CREATE TABLE ProductTest (
  Product_Id INT,
  Test_Id INT,
  PRIMARY KEY (Product_Id, Test_Id),
  FOREIGN KEY (Product_Id) REFERENCES Product(Id),
  FOREIGN KEY (Test_Id) REFERENCES Test(Id)
);

-- Create Instrument Table
CREATE TABLE Instrument (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Instrument_Name VARCHAR(255) NOT NULL,
  Descriptions VARCHAR(1500)
);

-- Create Material Table
CREATE TABLE Material (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Material_Name VARCHAR(255) NOT NULL
);

-- Create ProductMaterial Table
CREATE TABLE ProductMaterial (
  Product_Id INT,
  Material_Id INT,
  PRIMARY KEY (Product_Id, Material_Id),
  FOREIGN KEY (Product_Id) REFERENCES Product(Id),
  FOREIGN KEY (Material_Id) REFERENCES Material(Id)
);

--------------------------------------------------------------------------------------------------------------

-- Insert data into Product Table
INSERT INTO Product (
  Product_Name,
  Company_Name,
  Product_Id_C,
  Hazardous,
  Approve,
  Date_Created,
  Date_Reviewed,
  Purpose,
  Photo_URL,
  Descriptions,
  Quantity_Metric,
  Quantity_Numeric,
  Position,
  Room_Number
) VALUES (
  'Plastic-film',
  'Google',
  '123nds',
  TRUE,
  'Yes',
  '2008-12-24',
  '2014-12-24',
  'Example record',
  'ksjflkasf.png',
  'Plastic is essential for recycling',
  'sheets',
  12,
  'Cabinet 2',
  1800
);

INSERT INTO Product (
  Product_Name,
  Company_Name,
  Hazardous,
  Approve,
  Date_Reviewed,
  Purpose,
  Descriptions,
  Quantity_Metric,
  Quantity_Numeric,
  Position,
  Room_Number
) VALUES (
  'Nylon-fabric',
  'Apple',
  FALSE,
  'With',
  '2020-09-18',
  'Exhibition',
  'Approved with blah blah blah',
  'in^2',
  30,
  'Drawer 5',
  1800
);

INSERT INTO Product (
  Product_Name,
  Company_Name,
  Product_Id_C,
  Purpose,
  Photo_URL,
  Descriptions,
  Quantity_Metric,
  Quantity_Numeric,
  Position,
  Room_Number
) VALUES (
  'Ice-cube',
  'Antartica',
  '987dfac',
  'Conservation treatment',
  '984289ufasdfas.jpg',
  'This is frozen water',
  'oz',
  24,
  'Shelve 9',
  1600
);

-- Insert data into Test Table
INSERT INTO Test (
  Test_Name,
  Test_Date,
  Descriptions,
  Result,
  Test_Sheet,
  Instrument_Id
) VALUES (
  'Burn-test',
  '2024-07-16',
  'They burnt stuff',
  'pass',
  '282oadfnd.csv',
  1
);

INSERT INTO Test (
  Test_Name,
  Descriptions,
  Result,
  Instrument_Id
) VALUES (
  'Age-test',
  'They aged things',
  'ongoing',
  2
);

INSERT INTO Test (
  Test_Name,
  Test_Date,
  Descriptions,
  Result,
  Instrument_Id
) VALUES (
  'Melt-test',
  '1990-04-01',
  'They melted ice',
  'see description',
  1
);

INSERT INTO Product (
  Product_Name,
  Company_Name,
  Hazardous,
  Approve,
  Date_Created,
  Purpose,
  Descriptions,
  Quantity_Metric,
  Quantity_Numeric,
  Position,
  Room_Number
) VALUES (
  'Cardboard-box',
  'Microsoft',
  FALSE,
  'No',
  '2019-10-11',
  'Holdings maintenance',
  'This is a literal cardboard box',
  'box',
  2,
  'Counter 1',
  1500
);

-- Insert data into ProductTest Table
INSERT INTO ProductTest (Product_Id, Test_Id) VALUES (1, 1);
INSERT INTO ProductTest (Product_Id, Test_Id) VALUES (2, 1);
INSERT INTO ProductTest (Product_Id, Test_Id) VALUES (1, 2);
INSERT INTO ProductTest (Product_Id, Test_Id) VALUES (3, 3);

-- Insert data into Instrument Table
INSERT INTO Instrument (Instrument_Name, Descriptions) VALUES ('Lighter', 'Lights stuff on fire');
INSERT INTO Instrument (Instrument_Name, Descriptions) VALUES ('Sensor', 'Senses temperature');

-- Insert data into Material Table
INSERT INTO Material (Material_Name) VALUES ('Plastic');
INSERT INTO Material (Material_Name) VALUES ('Paper');
INSERT INTO Material (Material_Name) VALUES ('Nylon');
INSERT INTO Material (Material_Name) VALUES ('Water');

-- Insert data into ProductMaterial Table
INSERT INTO ProductMaterial (Product_Id, Material_Id) VALUES (1, 1);
INSERT INTO ProductMaterial (Product_Id, Material_Id) VALUES (1, 2);
INSERT INTO ProductMaterial (Product_Id, Material_Id) VALUES (2, 1);
INSERT INTO ProductMaterial (Product_Id, Material_Id) VALUES (2, 3);
INSERT INTO ProductMaterial (Product_Id, Material_Id) VALUES (3, 4);

---------------------------------------------------------------------------------

-- Select all (DEFAULT SCREEN)
SELECT * FROM Product;
SELECT * FROM Material;
SELECT * FROM Instrument;
SELECT * FROM Test;

-- Select every combination of product and test
SELECT p.Id, p.Product_Name, t.Id, t.Test_Name
  FROM Product p
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id;


-- Select every product that has NO test
SELECT p.Id, p.Product_Name
  FROM Product p
  LEFT JOIN ProductTest pt ON p.Id = pt.Product_Id
  WHERE pt.Test_Id IS NULL;


-- Select every product that has at least one test
SELECT DISTINCT p.Id, p.Product_Name
	FROM Product p
	INNER JOIN ProductTest pt ON p.Id = pt.Product_Id;


-- Select every combination of product and material
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id;


-- Select everything with Material, Product, Test, and Instrument joined
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id;

-- Filter for plastic material example
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE m.Id = 1;

-- Filter for plastic material and 2014-12-24 date
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name, p.date_created, p.date_reviewed
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE m.Id = 1
	AND (p.date_created = 2014-12-24 OR p.date_reviewed = 2014-12-24);

-- Select every combination of everything of products that have NO test
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name
  FROM Product p
  LEFT JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  LEFT JOIN Material m ON m.Id = pm.Material_Id
  LEFT JOIN ProductTest pt ON p.Id = pt.Product_Id
  LEFT JOIN Test t ON t.Id = pt.Test_Id
  LEFT JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE pt.Test_Id IS NULL;

-- Select every combination of everything of products that have NO test and filter for product name Cardboard-box and date of 2019-10-11
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, p.date_created, p.date_reviewed
  FROM Product p
  LEFT JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  LEFT JOIN Material m ON m.Id = pm.Material_Id
  LEFT JOIN ProductTest pt ON p.Id = pt.Product_Id
  LEFT JOIN Test t ON t.Id = pt.Test_Id
  LEFT JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE pt.Test_Id IS NULL
	AND p.Id = 4
    AND (date_created = '2019-10-11' OR p.date_reviewed = '2019-10-11');

-- Select every combination of everything of products that have at least 1 test
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name
  FROM Product p
  INNER JOIN ProductTest pt ON p.Id = pt.Product_Id
  INNER JOIN Test t ON t.Id = pt.Test_Id
  INNER JOIN Instrument i ON i.Id = t.Instrument_Id
  LEFT JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  LEFT JOIN Material m ON m.Id = pm.Material_Id;

-- Select every combination of everything of products that have at least 1 test and filter for material name plastic and date of 2014-12-24
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name, p.date_created, p.date_reviewed
  FROM Product p
  INNER JOIN ProductTest pt ON p.Id = pt.Product_Id
  INNER JOIN Test t ON t.Id = pt.Test_Id
  INNER JOIN Instrument i ON i.Id = t.Instrument_Id
  LEFT JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  LEFT JOIN Material m ON m.Id = pm.Material_Id
  WHERE m.Id = 2
	AND (date_created = '2014-12-24' OR p.date_reviewed = '2014-12-24');

-- Select every combination of everything of products that have at least 1 test and filter for anything with "plastic" and date of 2014-12-24
SELECT p.Id, p.Product_Name, m.Id, m.Material_Name, t.Id, t.Test_Name, i.Id, i.Instrument_Name, p.date_created, p.date_reviewed
  FROM Product p
  INNER JOIN ProductTest pt ON p.Id = pt.Product_Id
  INNER JOIN Test t ON t.Id = pt.Test_Id
  INNER JOIN Instrument i ON i.Id = t.Instrument_Id
  LEFT JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  LEFT JOIN Material m ON m.Id = pm.Material_Id
  WHERE
	  (p.Product_Name LIKE '%plas%' OR
    p.Company_Name LIKE '%plas%' OR
    p.Product_Id_C LIKE '%plas%' OR
    p.Company_Name LIKE '%plas%' OR
    p.Hazardous LIKE '%plas%' OR
    p.Approve LIKE '%plas%' OR
    p.Purpose LIKE '%plas%' OR
    p.Photo_URL LIKE '%plas%' OR
    p.Descriptions LIKE '%plas%' OR
    p.Quantity_Metric LIKE '%plas%' OR
    p.Position LIKE '%plas%' OR
    p.Room_Number LIKE '%plas%' OR
    t.Test_Name LIKE '%plas%' OR
    t.Descriptions LIKE '%plas%' OR
    t.Result LIKE '%plas%' OR
    t.Test_Sheet LIKE '%plas%' OR
    i.Instrument_Name LIKE '%plas%' OR
    i.Descriptions LIKE '%plas%' OR
    m.Material_Name LIKE '%plas%')
    AND
    (p.date_created = '2014-12-24' OR
    p.date_reviewed = '2014-12-24' OR
    t.Test_Date = '2014-12-24');

-- Select Material (Only name)
SELECT * FROM Material
  WHERE Material_Name LIKE '%pap%';

-- Select Instrument (name, description)
SELECT * FROM Instrument
  WHERE Instrument_Name LIKE '%li%' OR
    Descriptions LIKE '%li%';

-- Select Test (name, date, description, result, testsheet, instrument)
SELECT *
  FROM Test t
  INNER JOIN Instrument i ON t.Instrument_Id = i.Id
  WHERE
    (t.Test_Name LIKE '%bur%' OR
    t.Descriptions LIKE '%bur%' OR
    t.Result LIKE '%bur%' OR
    t.Test_Sheet LIKE '%bur%' OR
    i.Instrument_Name LIKE '%bur%')
    AND
    (t.Test_Date = '2024-07-16');

---------------------------------------------------------------------------------

-- Click on specific product
SELECT *
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE p.Product_Name = 'Plastic-film';

-- Click on specific test
SELECT *
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE t.Test_Name = 'Burn-test';

-- Click on specific material
SELECT *
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE m.Material_Name = 'water';

-- Click on specific instrument
SELECT *
  FROM Product p
  JOIN ProductMaterial pm ON p.Id = pm.Product_Id
  JOIN Material m ON m.Id = pm.Material_Id
  JOIN ProductTest pt ON p.Id = pt.Product_Id
  JOIN Test t ON t.Id = pt.Test_Id
  JOIN Instrument i ON i.Id = t.Instrument_Id
  WHERE i.Instrument_Name = 'lighter';

---------------------------------------------------------------------------------

-- get ID of company where product_name = Plastic-film
SELECT Id FROM Product WHERE Product_Name = 'Plastic-film';

-- get ID of test where test_name = Burn-test
SELECT Id FROM Test WHERE Test_Name = 'Burn-test';

-- get ID of material where material_name = Plastic
SELECT Id FROM Material WHERE Material_Name = 'Plastic';

-- get ID of instrument where instrument_name = Lighter
SELECT Id FROM Instrument WHERE Instrument_Name = 'Lighter';

---------------------------------------------------------------------------------

-- update company name of product (USE ID)
UPDATE Product SET Company_Name = 'Amazon' WHERE Id = 1;

-- update description of test (USE ID)
UPDATE Test SET Descriptions = 'Burny things' WHERE Id = 1;

-- update name of material (USE ID)
UPDATE Material SET Material_Name = 'PPLASTIC' WHERE Id = 1;

-- update name of instrument (USE ID)
UPDATE Instrument SET Descriptions = 'Lighters like lighting' WHERE Id = 1;

---------------------------------------------------------------------------------

-- u have to set this in MySQL
SET FOREIGN_KEY_CHECKS=0; -- to disable them
SET FOREIGN_KEY_CHECKS=1; -- to re-enable them

-- remove product (USE ID)
DELETE FROM Product WHERE Id = 1;
DELETE FROM ProductMaterial WHERE Product_Id = 1;
DELETE FROM ProductTest WHERE Product_Id = 1;

-- remove test (USE ID)
DELETE FROM Test WHERE Id = 1;
DELETE FROM ProductTest WHERE Test_Id = 1;

-- remove material (USE ID)
DELETE FROM Material WHERE Id = 1;
DELETE FROM ProductMaterial WHERE Material_Id = 1;

-- remove instrument (USE ID)
DELETE FROM Instrument WHERE Id = 1;
UPDATE Test SET Instrument_Id = NULL WHERE Instrument_Id = 1;

---------------------------------------------------------------------------------

-- remove all values from ALL table
TRUNCATE TABLE Product;
TRUNCATE TABLE Test;
TRUNCATE TABLE Material;
TRUNCATE TABLE Instrument;
TRUNCATE TABLE ProductTest;
TRUNCATE TABLE ProductMaterial;



















