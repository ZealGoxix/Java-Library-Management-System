USE library_db;

CREATE TABLE issued(iid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, bid INT, issued_date VARCHAR(20), return_date VARCHAR(20), period INT, fine INT)