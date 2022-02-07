DROP DATABASE chung_ACMEOnline;
CREATE DATABASE chung_ACMEOnline;
USE chung_ACMEOnline;
CREATE table CATEGORY(
  Category_Name char(35),
  ShipppingPerPound decimal(4, 2),
  OffersAllowed char(1) CHECK (OffersAllowed IN ('y', 'n')),
  CONSTRAINT PRIMARY KEY(Category_Name)
);
DESCRIBE CATEGORY;
CREATE table ITEM(
  Item_Number int unsigned AUTO_INCREMENT,
  Item_Name char(35) not null,
  Description char(255),
  Model char(50) not null,
  Price decimal(8, 2) not null,
  Category_Name char(35),
  CONSTRAINT item_pk PRIMARY KEY (Item_Number),
  CONSTRAINT item_foreign_key FOREIGN KEY (Category_Name) REFERENCES CATEGORY(Category_Name)
);
DESCRIBE ITEM;
CREATE table OFFER(
  OfferCode char(15),
  Discount_Amt char(35) not null,
  MinAmount decimal(4, 2),
  ExpirationDate date not null,
  CONSTRAINT offer_pk PRIMARY KEY (OfferCode)
);
DESCRIBE OFFER;
CREATE table CUSTOMER(
  CustomerID int unsigned AUTO_INCREMENT,
  CustomerName varchar(50) not null,
  Address varchar(150) not null,
  Email varchar(80),
  Home TINYINT(1) NOT NULL DEFAULT 0,
  Business TINYINT(1) NOT NULL DEFAULT 0,
  CONSTRAINT CUSTOMER_pk PRIMARY KEY(CustomerID)
);
DESCRIBE CUSTOMER;
CREATE table ORDERED(
  CustomerID int unsigned,
  OrderID int unsigned AUTO_INCREMENT,
  Total decimal(10, 2),
  OfferCode char(15),
  CONSTRAINT ordered_pk PRIMARY KEY (OrderID),
  CONSTRAINT odered_foreign_key1 FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
  CONSTRAINT odered_foreign_key2 FOREIGN KEY (OfferCode) REFERENCES OFFER(OfferCode)
);
DESCRIBE ORDERED;
CREATE table BUSINESS(
  CustomerID int unsigned,
  PaymentTerms varchar(50) not null,
  CONSTRAINT business_pk PRIMARY KEY (CustomerID),
  CONSTRAINT business_foreign_key FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID)
);
DESCRIBE BUSINESS;
CREATE table HOME (
  CustomerID int unsigned,
  CreditCardNum char(16) not null,
  CardExpirationDate char(6) not null,
  CONSTRAINT home_pk PRIMARY KEY (CustomerID),
  CONSTRAINT home_foreign_key FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID)
);
DESCRIBE HOME;
CREATE table LINE_ITEM(
  OrderID int unsigned,
  Item_Number int unsigned,
  Quantity int,
  Shipping_Amount decimal(6, 2),
  CHECK (Quantity <= 255),
  CONSTRAINT line_item_pk PRIMARY KEY (Item_Number, OrderID),
  CONSTRAINT line_item_foreign_key FOREIGN KEY (Item_Number) REFERENCES ITEM(Item_Number),
  CONSTRAINT line_item_foreign_key2 FOREIGN KEY (OrderID) REFERENCES ORDERED(OrderID)
);
DESCRIBE LINE_ITEM;
CREATE table PURCHASE_CONTACT(
  ContactName varchar(50),
  ContactPhone char(12) not null,
  CustomerID int unsigned,
  CONSTRAINT PURCAHSE_CONTACT_PK PRIMARY KEY (ContactName, CustomerID),
  CONSTRAINT PURCHASE_CONTACT_FK FOREIGN KEY (CustomerID) REFERENCES BUSINESS(CustomerID)
);
DESCRIBE PURCHASE_CONTACT;
CREATE table GUARANTEE (
  OrderID int unsigned,
  CustomerID int unsigned,
  URL varchar(50),
  RefundedAmount decimal (12, 2),
  CONSTRAINT GUARANTEE_PK PRIMARY KEY (OrderID, CustomerID),
  CONSTRAINT GUARANTEE_FK FOREIGN KEY (OrderID) REFERENCES ORDERED (OrderID),
  CONSTRAINT GUARANTEE_FK2 FOREIGN KEY (CustomerID) REFERENCES HOME (CustomerID)
);
DESCRIBE GUARANTEE;

/*Inserting General Data*/

INSERT into CATEGORY VALUES ('Books', 0.99, 'y');
INSERT into CATEGORY VALUES ('Home', 1.99, 'y');
INSERT into CATEGORY VALUES ('Jewelry', 0.99, 'n');
INSERT into CATEGORY VALUES ('Toys', 0.99, 'y');

INSERT into OFFER VALUES ('345743213', '20% off', 20.00, '2013-12-31');
INSERT into OFFER VALUES ('4567890123', '30% off', 30.00, '2013-12-31');

INSERT into ITEM VALUES (NULL, 'Cabbage Patch Doll', 'Baby boy doll', 'Boy', 39.95, 'Toys');
INSERT into ITEM VALUES (NULL, 'The Last Lecture', 'Written by randy Pausch', 'Hardcover', 9.95, 'Books');
INSERT into ITEM VALUES (NULL, 'Keurig Beverage Maker', 'Keurig Platinum Edition Beverage Maker in Red', 'Platinum Edition', 299.95, 'Home');
INSERT into ITEM VALUES (NULL, 'lct diamond ring in white gold', 'diamond is certified vvs, D, round', '64gt32', 4000.00, 'Jewelry');

/*Inserting for Janine Jeffers*/

INSERT into CUSTOMER VALUES (NULL, 'Janine Jeffers', '152 Lomb Memorial DR., Rochester, NY 14623', 'jxj1234@rit.edu', DEFAULT, DEFAULT);
INSERT into HOME VALUES (1, '1234567890123456', '012014');
UPDATE CUSTOMER SET Home=1 WHERE CustomerID = 1;

INSERT into ORDERED VALUES (1, NULL, 4919.75, '4567890123');

INSERT into LINE_ITEM VALUES (1, 4, 1, .99);
INSERT into LINE_ITEM VALUES (1, 2, 2, 3.99);
INSERT into LINE_ITEM VALUES (1, 3, 3, NULL);

/*Inserting for Joe John Barber Shop*/

INSERT into CUSTOMER VALUES (NULL, 'Joey John Barber Shop', '15 John St., Rochester, NY 14623', 'jj1978@hotmail.com', DEFAULT, DEFAULT);
INSERT INTO BUSINESS VALUES (2, '30/90 days');

UPDATE CUSTOMER SET Business=1 where CustomerID=2;

INSERT INTO ORDERED VALUES (2, NULL, 299.95, '345743213');

INSERT INTO LINE_ITEM VALUES (2, 3, 1, NULL);

INSERT INTO PURCHASE_CONTACT VALUES ('Joey James', '585-475-1234', 2);

SELECT * FROM PURCHASE_CONTACT;
SELECT * FROM BUSINESS;
SELECT * FROM HOME;
SELECT * FROM CUSTOMER;
SELECT * FROM ORDERED;
SELECT * FROM ITEM;
SELECT * FROM CATEGORY;
SELECT * FROM LINE_ITEM;
SELECT * FROM OFFER;
