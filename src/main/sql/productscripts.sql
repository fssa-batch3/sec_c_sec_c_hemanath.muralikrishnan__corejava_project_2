CREATE DATABASE `agrokart_core_java`;

USE agrokart_core_java;

-- to store the id of category and names of the category
CREATE TABLE categories(id INT NOT NULL UNIQUE AUTO_INCREMENT, name VARCHAR(20) NOT NULL UNIQUE, PRIMARY KEY(id));

-- to store the available stock units kg,pkt,nos to not repeating the data
CREATE TABLE units(id INT NOT NULL UNIQUE AUTO_INCREMENT, unit  VARCHAR(20) NOT NULL UNIQUE, PRIMARY KEY(id));

-- to store the product status Available, Not available to not repeting the data
CREATE TABLE status(id INT NOT NULL UNIQUE AUTO_INCREMENT, name VARCHAR(20) NOT NULL UNIQUE, PRIMARY KEY(id));

-- insert the category into the category table
INSERT INTO categories (name)
VALUES
    ('exotic_fruits'),
    ('exotic_veggies'),
    ('fresh_veggies'),
    ('fresh_fruits'),
    ('leafy_green'),
    ('tubers');

-- insert the available stock units values
INSERT INTO units (unit) VALUES("kg"),("gm"),("pkt"),("nos"),("kcal");

-- insert into the product status table
INSERT INTO status (name) VALUES
	('available'),
    ('not_available');

-- create table to store the product details
CREATE TABLE product (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    eng_name VARCHAR(50) NOT NULL,
    tam_name VARCHAR(50) NOT NULL,
    image_url VARCHAR(300) NOT NULL,
    category_id INT NOT NULL,
    description VARCHAR(400) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES categories(id),
    FOREIGN KEY(status_id) REFERENCES status(id)
);



-- create table to store the available stock for each product
CREATE TABLE product_available_stock (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    product_id INT NOT NULL UNIQUE,
    weight DOUBLE NOT NULL,
    unit_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (unit_id) REFERENCES units(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);

-- create table to store the nutr values
CREATE TABLE product_nutr(
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    product_id INT NOT NULL UNIQUE,
    protein DOUBLE NOT NULL,
    protein_unit_id INT NOT NULL,
    carbohydrates DOUBLE NOT NULL,
    carbo_unit_id INT NOT NULL,
    calories DOUBLE NOT NULL,
    cal_unit_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(protein_unit_id) REFERENCES units(id),
    FOREIGN KEY(carbo_unit_id) REFERENCES units(id),
    FOREIGN KEY(cal_unit_id) REFERENCES units(id)
);

-- create table to store the product different quantities
CREATE TABLE product_quantities_cate(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	product_id INT NOT NULL,
	weight DOUBLE NOT NULL,
	unit_id INT NOT NULL,
	rupees DOUBLE NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(unit_id) REFERENCES units(id),
	FOREIGN KEY(product_id) REFERENCES product(id)
);




