INSERT INTO CLIENT (last_Name,first_Name,email,phone,address) VALUES ('Zaghmouri','Roua','zaghmouri.roua@gmail.com','52487027','Tunis');
INSERT INTO CLIENT (last_Name,first_Name,email,phone,address) VALUES ('Chahed','Lamia','Chahed.lamia@gmail.com','52487027','Tunis');

INSERT INTO ACCOUNT(account_balance,account_number,account_type,client_id) VALUES (100,5432,'COURANT',1);
INSERT INTO ACCOUNT(account_balance,account_number,account_type,client_id) VALUES (500,6785,'EPARGNE',1);

INSERT INTO ACCOUNT(account_id,account_balance,account_number,account_type,client_id) VALUES (3,300,3456,'EPARGNE',2);