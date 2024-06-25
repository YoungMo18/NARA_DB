CREATE TABLE Product (
  Id INT,
  Product_Id_C VARCHAR(255),
  Materials VARCHAR(255),
  Hazardous BOOLEAN,
  Approve VARCHAR,
  Date_Created DATE,
  Date_Reviewed DATE,
  Purpose VARCHAR(255),
  Photo_URL VARCHAR(255),
  Descriptions VARCHAR(1000),
  Product_Name VARCHAR(255),
  Company_Name VARCHAR(255),
  Quantity VARCHAR(255)
);

CREATE TABLE Test (
  Id INT,
  Test_Name VARCHAR(255),
  Test_Date DATE,
  Descriptions VARCHAR(1000),
  Result VARCHAR(255),
  Test_Sheet VARCHAR(255),
  Instrument_Id INT REFERENCES Instrument(Id)
);

CREATE TABLE Employs (
  Product_Id INT REFERENCES Product(Id),
  Test_Id INT REFERENCES Test(Id)
);

CREATE TABLE Instrument (
  Id INT,
  Instrument_Name VARCHAR(255),
  Descriptions VARCHAR(1000),
);