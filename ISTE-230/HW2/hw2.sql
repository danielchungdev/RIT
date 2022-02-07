-- HW2 SCRIPT
/*
Daniel Chung
*/
DROP DATABASE HW2;

CREATE DATABASE hw2;

SHOW databases; 

USE hw2;

CREATE TABLE ITEM (
ItemID varchar(25),
ItemName varchar(25),
Name varchar(25),
Street varchar(25),
City varchar(25),
Color varchar(25),
State char(2),
Zipcode varchar(10),
Cost varchar(10),
Retail_price varchar(10),
Notes varchar(255),
Description varchar(255),
Returnable char(1),
Perishable char(1),
Shelf_Qty int
);

SHOW tables;

INSERT INTO ITEM (
ItemID,
ItemName,
Name,
Street,
City,
Color,
State,
Zipcode,
Cost,
Retail_price,
Notes,
Description,
Returnable,
Perishable,
Shelf_Qty)
VALUES
(
    'ABCD32',
    'Test',
    'Daniel',
    'Street1',
    'city1',
    'Color1',
    'NY',
    'zipcode',
    '54$',
    '56$',
    'kekekek',
    '11111',
    'Y',
    'N',
    54
);

SELECT * FROM ITEM;
