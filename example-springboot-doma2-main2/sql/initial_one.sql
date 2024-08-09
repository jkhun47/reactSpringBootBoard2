CREATE schema example_one;
CREATE TABLE example_one.account_one (
  user_id int PRIMARY KEY,
  username varchar ( 128 ) UNIQUE NOT NULL
);
INSERT INTO example_one.account_one values (1, 'username_one_01');
INSERT INTO example_one.account_one values (2, 'username_one_02');
INSERT INTO example_one.account_one values (3, 'username_one_03');
