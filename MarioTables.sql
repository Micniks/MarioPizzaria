DROP DATABASE IF EXISTS Marios_Pizza;
CREATE DATABASE Marios_Pizza;
USE Marios_Pizza;
DROP TABLE if exists Pizza_Orders;
DROP TABLE if exists Pizza;
DROP TABLE if exists Orders;

CREATE TABLE Orders(
OrderNo INT NOT NULL,
OrderPrice float NOT NULL,
Pickup_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (OrderNo)
);

CREATE TABLE Pizza(
PizzaNo INT NOT NULL,
PizzaName VARCHAR (30) NOT NULL,
PizzaPrice INT NOT NULL,
PRIMARY KEY (PizzaNo)
);

CREATE TABLE Pizza_Orders (
OrderNo INT NOT NULL,
PizzaNo INT NOT NULL,
Quantity INT NOt NULL,
PRIMARY KEY (OrderNo, PizzaNo),
FOREIGN KEY (OrderNo) REFERENCES Orders(OrderNo),
FOREIGN KEY (PizzaNo) REFERENCES Pizza(PizzaNo)
);

INSERT INTO Pizza (PizzaNo, PizzaName, PizzaPrice)
values
(1, "Vesuvio", 57),
(2, "Amerikaner", 53),
(3, "Cacciatore", 57),
(4, "Carbona", 63),
(5, "Dennis", 65),
(6, "Bertil", 57),
(7, "Silvia", 61),
(8, "Victoria", 61),
(9, "Toronfo", 61),
(10, "Capricciosa", 61),
(11, "Hawai", 61),
(12, "Le Blissola", 61),
(13, "Venezia", 61),
(14, "Mafia", 61)
;

SELECT * from Pizza