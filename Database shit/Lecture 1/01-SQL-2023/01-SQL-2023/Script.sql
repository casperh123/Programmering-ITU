DROP TABLE IF EXISTS Sells;
DROP TABLE IF EXISTS Coffees;
DROP TABLE IF EXISTS Coffeehouses;

CREATE TABLE Coffees (
	Name VARCHAR(255),
	Manufacturer VARCHAR(255),
	PRIMARY KEY (Name)
);

CREATE TABLE Coffeehouses (
    Name VARCHAR(255),
    Address  VARCHAR(255),
    License VARCHAR(255),
	PRIMARY KEY (Name)
);

CREATE TABLE Sells (
	Coffeehouse VARCHAR(255),
	Coffee VARCHAR(255),
	Price INTEGER,
	FOREIGN KEY (Coffeehouse) REFERENCES Coffeehouses(Name),
	FOREIGN KEY (Coffee) REFERENCES Coffees(Name),
	PRIMARY KEY (Coffeehouse, Coffee)
);

