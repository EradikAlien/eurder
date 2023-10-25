-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO test_user (id, firstname, lastname, emailAddress, address, phoneNumber) VALUES (1 , 'Laurent', 'Herry', 'email@test.be', '22 rue du chien', '123456789');
INSERT INTO test_user (id, firstname, lastname, emailAddress, address, phoneNumber) VALUES (2 , 'Tina', 'Puffin', 'test@email.be', '22 rue du chat', '987654321');
INSERT INTO Item (id, name, description, price, stock) VALUES (1 , 'Item 1', 'The first item', '57', '5');
INSERT INTO Item (id, name, description, price, stock) VALUES (2 , 'Item 2', 'The second item', '31', '10');
