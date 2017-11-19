CREATE TABLE PROFILE (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY,
userName VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
enabled boolean
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ROLE (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY,
  profile_id INT(10) UNSIGNED NOT NULL,
  role VARCHAR(45) NOT NULL,
  FOREIGN KEY (profile_id) REFERENCES users (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO PROFILE (id, userName, password, enabled)
VALUES (101, 'admin', 'admin123', true),
       (102, 'john', 'john123', true),
       (103, 'tina', 'tina123', true);
 
INSERT INTO ROLE (id, profile_id, role)
VALUES (101, 101, 'ROLE_ADMIN'),
       (102, 102, 'ROLE_USER'),
       (103, 103, 'ROLE_ADMIN');