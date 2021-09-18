-- DROP TABLE IF EXISTS students;
-- DROP TABLE IF EXISTS roles;
-- DROP TABLE IF EXISTS privileges;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS roles_privileges;
-- DROP TABLE IF EXISTS users_roles;

CREATE TABLE IF NOT EXISTS students (
  id SERIAL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  CONSTRAINT pk_students PRIMARY KEY (id)
);
ALTER SEQUENCE students_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS roles (
  id SERIAL,
  name VARCHAR(80),
  CONSTRAINT pk_roles PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_roles_name ON roles (name);
ALTER SEQUENCE roles_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS privileges (
  id SERIAL,
  name VARCHAR(100),
  CONSTRAINT pk_privileges PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_privileges_name ON privileges (name);
ALTER SEQUENCE privileges_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS users (
  id SERIAL,
  username VARCHAR(150),
  password VARCHAR(200),
  CONSTRAINT pk_users PRIMARY KEY (id)
);
ALTER SEQUENCE users_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS roles_privileges (
  role_id BIGINT NOT NULL,
  privilege_id BIGINT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles(id),
  FOREIGN KEY (privilege_id) REFERENCES privileges(id)
);

CREATE TABLE IF NOT EXISTS users_roles (
   user_id BIGINT NOT NULL,
   role_id BIGINT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users(id),
   FOREIGN KEY (role_id) REFERENCES roles(id)
);



