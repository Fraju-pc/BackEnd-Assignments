DROP TABLE IF EXISTS StoreInventory;
DROP TABLE IF EXISTS Instrument;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Store;

CREATE TABLE Store (
    store_id INT AUTO_INCREMENT,
    store_name VARCHAR(60) NOT NULL,
    street_address VARCHAR(128) NOT NULL,
	city VARCHAR(60),
	state VARCHAR(40),
	zip VARCHAR(20),
	phone VARCHAR(30),
	PRIMARY KEY(store_id)
);

CREATE TABLE Category (
    category_id INT AUTO_INCREMENT,
    category_name VARCHAR(80) NOT NULL,
    PRIMARY KEY(category_id)
);

CREATE TABLE Instrument (
    instrument_id INT AUTO_INCREMENT,
    instrument_name VARCHAR(80) NOT NULL,
    instrument_color VARCHAR(80),
    instrument_description VARCHAR(255),
    instrument_price INT,
    instrument_image VARCHAR(128),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE CASCADE,
    PRIMARY KEY(instrument_id)
);

CREATE TABLE StoreInventory (
    store_id INT,
    instrument_id INT,
    inventory_amount INT,
    PRIMARY KEY (store_id, instrument_id),
    FOREIGN KEY (store_id) REFERENCES Store(store_id) ON DELETE CASCADE,
    FOREIGN KEY (instrument_id) REFERENCES Instrument(instrument_id) ON DELETE CASCADE
);