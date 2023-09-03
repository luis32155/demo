DROP TABLE IF EXISTS tbl_currency;
CREATE TABLE tbl_currency (
currency_id INT AUTO_INCREMENT  PRIMARY KEY,
currency VARCHAR(5) NOT NULL,
currency_desc VARCHAR(50) NOT NULL,
currency_register DATETIME NOT NULL
);

INSERT INTO tbl_currency(currency, currency_desc, currency_register)
VALUES('S/.', 'Soles', '2021-08-10 10:50:00');
INSERT INTO tbl_currency(currency, currency_desc, currency_register)
VALUES('$USD', 'Dolares', '2021-08-10 10:50:00');
INSERT INTO tbl_currency(currency, currency_desc, currency_register)
VALUES('€', 'Euros', '2021-08-10 10:50:00');

DROP TABLE IF EXISTS tbl_exchange;
CREATE TABLE tbl_exchange (
exchange_id INT AUTO_INCREMENT  PRIMARY KEY,
currency_change_from INT NOT NULL,
currency_change_to INT NOT NULL,
exchange_rate DECIMAL(10,2) NOT NULL,
exchange_register DATETIME NOT NULL,
exchange_update DATETIME NOT NULL
);

INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(1, 2, 0.24, '2021-08-10 10:50:00', '2021-08-10 10:50:00');
INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(1, 3, 0.21, '2021-08-10 10:50:00', '2021-08-10 10:50:00');

INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(2, 1, 4.12, '2021-08-10 10:50:00', '2021-08-10 10:50:00');
INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(2, 3, 0.85, '2021-08-10 10:50:00', '2021-08-10 10:50:00');

INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(3, 1, 4.82, '2021-08-10 10:50:00', '2021-08-10 10:50:00');
INSERT INTO tbl_exchange(currency_change_from, currency_change_to, exchange_rate, exchange_register, exchange_update)
VALUES(3, 2, 1.17, '2021-08-10 10:50:00', '2021-08-10 10:50:00');

