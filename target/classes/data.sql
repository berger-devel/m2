DROP TABLE IF EXISTS user_breed;
DROP TABLE IF EXISTS breed;
DROP TABLE IF EXISTS user;

CREATE TABLE user (id INT auto_increment PRIMARY KEY, name VARCHAR(250), pet VARCHAR(250));
CREATE TABLE breed (id INT auto_increment PRIMARY KEY, pet VARCHAR(250), name VARCHAR(250));
CREATE TABLE user_breed (user_id INT, breed_id INT, PRIMARY KEY (user_id, breed_id), FOREIGN KEY (user_id) REFERENCES user(id), FOREIGN KEY (breed_id) REFERENCES breed(id));

INSERT INTO user (name, pet) values ('Stefan', 'Dog');

INSERT INTO breed (pet, name) values ('Cat', 'Persian');
INSERT INTO breed (pet, name) values ('Cat', 'Scottish Fold');
INSERT INTO breed (pet, name) values ('Cat', 'Manx');
INSERT INTO breed (pet, name) values ('Cat', 'British Shorthair');
INSERT INTO breed (pet, name) values ('Cat', 'Maine Coon');

INSERT INTO breed (pet, name) values ('Dog', 'Labrador');
INSERT INTO breed (pet, name) values ('Dog', 'Shepherd');
INSERT INTO breed (pet, name) values ('Dog', 'Bulldog');
INSERT INTO breed (pet, name) values ('Dog', 'Beagle');
INSERT INTO breed (pet, name) values ('Dog', 'Poodle');

INSERT INTO user_breed (user_id, breed_id) VALUES (1, 6);
INSERT INTO user_breed (user_id, breed_id) VALUES (1, 7);
