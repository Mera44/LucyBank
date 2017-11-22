
INSERT INTO Role (id, role) VALUES (1,'ROLE_BANKER');
INSERT INTO Role (id, role) VALUES (2,'ROLE_TELLER');
INSERT INTO Role (id, role) VALUES (3,'ROLE_CUSTOMER');

INSERT INTO Address (id, state, street, zipcode) VALUES (1,'IA','ZY st','45334');
INSERT INTO Address (id, state, street, zipcode) VALUES (4,'CA','Wahington st','97655');
INSERT INTO Address (id, state, street, zipcode) VALUES (5,'IA','Clay st','52556');

INSERT INTO Profile (id, email, firstName, lastName, password, userName, address_id, role_id) VALUES (1,'lucybanker@lucy.com','Filmon','Semere','admin','admin',1,1);
INSERT INTO Profile (id, email, firstName, lastName, password, userName, address_id, role_id) VALUES (4,'selenaCustomer@lucybank.com','Selena','Robert','root','root',4,3);
INSERT INTO Profile (id, email, firstName, lastName, password, userName, address_id, role_id) VALUES (5,'donaldTeller@mum.edu','Donald','Mugabe','teller','teller',5,2);

INSERT INTO Customer (id, profile_id) VALUES (1,4);
INSERT INTO Teller (id, profile_id) VALUES (1,5);
INSERT INTO Banker (id, profile_id) VALUES (1,1);


INSERT INTO SavingAccount (id, accountNumber, balance, status, typeAccount, cardNumber_id, interestRate) VALUES (2,33203,0,'','Saving',NULL,0.05);

INSERT INTO CheckingAccount (id, accountNumber, balance, status, typeAccount, cardNumber_id) VALUES (1,49188,0,'','Checking',NULL);


INSERT INTO Customer_Account (Customer_id, accounts_id) VALUES (1,1);
INSERT INTO Customer_Account (Customer_id, accounts_id) VALUES (1,2);