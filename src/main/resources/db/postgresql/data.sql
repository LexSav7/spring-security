INSERT INTO privileges VALUES (1, 'student:read') ON CONFLICT DO NOTHING;
INSERT INTO privileges VALUES (2, 'student:write') ON CONFLICT DO NOTHING;
INSERT INTO privileges VALUES (3, 'course:read') ON CONFLICT DO NOTHING;
INSERT INTO privileges VALUES (4, 'course:write') ON CONFLICT DO NOTHING;

INSERT INTO roles VALUES (1, 'ROLE_STUDENT') ON CONFLICT DO NOTHING;
INSERT INTO roles VALUES (2, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

-----------------------------------------------------------
-- СТО ПЯТСОТ РЕКОРДОВ. ИЛИ ОДИН РАЗ ИНИТ, ИЛИ ИГНОР УСЛОВИЕ КАКОЕ-ТО
------------------------------------------------------------
INSERT INTO roles_privileges VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO roles_privileges VALUES (1, 2) ON CONFLICT DO NOTHING;
INSERT INTO roles_privileges VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO roles_privileges VALUES (2, 2) ON CONFLICT DO NOTHING;

INSERT INTO users VALUES (1, 'lexsav', '$2a$10$y1NMKZhMFeKA/y9jEklU5Of.d3hvAZ5RBqcSN.ocmghKV/BxajFDO') ON CONFLICT DO NOTHING;
INSERT INTO users VALUES (2, 'annasmith', '$2a$10$Z5fb86DvaTDLDSJmKKKJTes8FgjtqFUBu1Tf2XKjaR3XmhOXVsR6i') ON CONFLICT DO NOTHING;
INSERT INTO users VALUES (3, 'ololovich', '$2a$10$oo8iBfnsdnsiVbeYv8vak.Gdy64hH82ZQ.7qhxKjtlauXhPORczxS') ON CONFLICT DO NOTHING;

INSERT INTO users_roles VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO users_roles VALUES (1, 2) ON CONFLICT DO NOTHING;
INSERT INTO users_roles VALUES (2, 2) ON CONFLICT DO NOTHING;
INSERT INTO users_roles VALUES (3, 1) ON CONFLICT DO NOTHING;

INSERT INTO students VALUES (1, 'alex') ON CONFLICT DO NOTHING;
INSERT INTO students VALUES (2, 'john') ON CONFLICT DO NOTHING;
INSERT INTO students VALUES (3, 'peter') ON CONFLICT DO NOTHING;
