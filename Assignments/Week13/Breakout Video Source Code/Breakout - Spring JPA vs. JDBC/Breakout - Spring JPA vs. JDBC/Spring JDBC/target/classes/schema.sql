-- This data was taken from: 
-- https://www.coopsandcages.com.au/blog/ultimate-list-rabbit-breeds/

DROP TABLE IF EXISTS breed_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS alt_name;
DROP TABLE IF EXISTS breed;

CREATE TABLE breed (
  breed_id int AUTO_INCREMENT NOT NULL,
  breed_name varchar(64) NOT NULL,
  description text,
  PRIMARY KEY (breed_id),
  UNIQUE KEY (breed_name)
);

CREATE TABLE alt_name (
  alternate_id int AUTO_INCREMENT NOT NULL,
  breed_id int NOT NULL,
  alternate_name varchar(64),
  PRIMARY KEY (alternate_id),
  FOREIGN KEY (breed_id) REFERENCES breed (breed_id) ON DELETE CASCADE
);

CREATE TABLE category (
  category_id int AUTO_INCREMENT NOT NULL,
  category_name varchar(32) NOT NULL,
  PRIMARY KEY (category_id),
  UNIQUE KEY (category_name)
);

CREATE TABLE breed_category (
  breed_id int NOT NULL,
  category_id int NOT NULL,
  FOREIGN KEY (breed_id) REFERENCES breed (breed_id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
  UNIQUE KEY (breed_id, category_id)
);
