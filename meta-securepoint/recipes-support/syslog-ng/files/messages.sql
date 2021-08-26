CREATE TABLE "messages" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "audit" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "firewall" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "auth" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "http" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "mail" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);
CREATE TABLE "errors" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "level" INTEGER,
  "message"  CHAR);


CREATE TABLE "config" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "value"    INTEGER);

INSERT INTO config (id, value) VALUES (1, 10000); -- messages
INSERT INTO config (id, value) VALUES (2, 10000); -- audit
INSERT INTO config (id, value) VALUES (3, 10000); -- firewall
INSERT INTO config (id, value) VALUES (4, 10000); -- auth
INSERT INTO config (id, value) VALUES (5, 10000); -- http
INSERT INTO config (id, value) VALUES (6, 10000); -- mail
INSERT INTO config (id, value) VALUES (7, 10000); -- errors

-- persistent setting (survives the database connection)
PRAGMA journal_mode=WAL;

CREATE TRIGGER "tr_config" AFTER UPDATE ON "config"
BEGIN
  DELETE FROM messages WHERE seq > ( SELECT value FROM config WHERE id = 1 );
  DELETE FROM audit WHERE seq > ( SELECT value FROM config WHERE id = 2 );
  DELETE FROM firewall WHERE seq > ( SELECT value FROM config WHERE id = 3 );
  DELETE FROM auth WHERE seq > ( SELECT value FROM config WHERE id = 4 );
  DELETE FROM http WHERE seq > ( SELECT value FROM config WHERE id = 5 );
  DELETE FROM mail WHERE seq > ( SELECT value FROM config WHERE id = 6 );
  DELETE FROM errors WHERE seq > ( SELECT value FROM config WHERE id = 7 );
END;

CREATE TRIGGER "tr_messages" AFTER INSERT ON "messages"
BEGIN
  update messages set seq = id % ( SELECT value FROM config WHERE id = 1) where id=new.id;
END;
CREATE TRIGGER "tr_audit" AFTER INSERT ON "audit"
BEGIN
  update audit set seq = id % (SELECT value FROM config WHERE id = 2) where id=new.id;
END;
CREATE TRIGGER "tr_firewall" AFTER INSERT ON "firewall"
BEGIN
  update firewall set seq = id % (SELECT value FROM config WHERE id = 3) where id=new.id;
END;
CREATE TRIGGER "tr_auth" AFTER INSERT ON "auth"
BEGIN
  update auth set seq = id % (SELECT value FROM config WHERE id = 4) where id=new.id;
END;
CREATE TRIGGER "tr_http" AFTER INSERT ON "http"
BEGIN
  update http set seq = id % (SELECT value FROM config WHERE id = 5) where id=new.id;
END;
CREATE TRIGGER "tr_mail" AFTER INSERT ON "mail"
BEGIN
  update mail set seq = id % (SELECT value FROM config WHERE id = 6) where id=new.id;
END;
CREATE TRIGGER "tr_errors" AFTER INSERT ON "errors"
BEGIN
  update errors set seq = id % (SELECT value FROM config WHERE id = 7) where id=new.id;
END;
