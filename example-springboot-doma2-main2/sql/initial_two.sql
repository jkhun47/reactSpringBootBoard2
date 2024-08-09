CREATE schema example_two;
CREATE TABLE example_two.account_two (
  user_id int PRIMARY KEY,
  username varchar ( 128 ) UNIQUE NOT NULL
);
INSERT INTO example_two.account_two values (1, 'username_two_01');
INSERT INTO example_two.account_two values (2, 'username_two_02');
INSERT INTO example_two.account_two values (3, 'username_two_03');