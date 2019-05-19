DROP TABLE customers IF EXISTS;
DROP TABLE accounts IF EXISTS;
DROP TABLE workers IF EXISTS;
DROP TABLE credits IF EXISTS;
DROP TABLE deposits IF EXISTS;

DROP TABLE types IF EXISTS;


CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
 );
 CREATE INDEX types_name ON types (name);
 
 CREATE TABLE currency_types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
 );
 CREATE INDEX currency_types_name ON currency_types (name);

CREATE TABLE workers (

id 			INTEGER IDENTITY PRIMARY KEY,
first_name	VARCHAR(30),
last_name	VARCHAR(30)
);
CREATE INDEX workers_last_name ON workers (last_name);


CREATE TABLE customers (
id 			INTEGER IDENTITY PRIMARY KEY,
first_name	VARCHAR(30),
last_name	VARCHAR(30),
passport_serial VARCHAR(20),
birth_date DATE,
mobile_phone VARCHAR(20),
address VARCHAR(50),
status VARCHAR(50),
workers_id	INTEGER,
type_id INTEGER NOT NULL
);
ALTER TABLE customers ADD CONSTRAINT fk_customer_helper FOREIGN KEY (workers_id) REFERENCES workers (id);

CREATE INDEX customers_last_name ON customers (last_name);


CREATE TABLE accounts(
id INTEGER IDENTITY PRIMARY KEY,
currency_type_id INTEGER NOT NULL,
ballance DOUBLE NOT NULL,
customer_id INTEGER
);





CREATE TABLE credits(
id INTEGER PRIMARY KEY,
account_id INTEGER NOT NULL,
name VARCHAR(50),
rate FLOAT NOT NULL,
initial_balance DOUBLE NOT NULL,
current_balance DOUBLE NOT NULL,
take_date DATE NOT NULL,
return_date DATE NOT NULL,
status BOOLEAN NOT NULL
);
ALTER TABLE credits ADD CONSTRAINT fk_credit_account FOREIGN KEY (account_id) REFERENCES accounts (id);

CREATE TABLE deposits(
id INTEGER PRIMARY KEY,
account_id INTEGER NOT NULL,
rate FLOAT NOT NULL,
initial_balance DOUBLE NOT NULL,
current_balance DOUBLE NOT NULL,
take_date DATE NOT NULL,
return_date DATE NOT NULL,
status BOOLEAN NOT NULL
);
ALTER TABLE deposits ADD CONSTRAINT fk_deposit_account FOREIGN KEY (account_id) REFERENCES accounts (id);




