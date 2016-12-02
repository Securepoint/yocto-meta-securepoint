CREATE TABLE "messages" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "message"  CHAR);
CREATE TABLE "audit" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "message"  CHAR);
CREATE TABLE "firewall" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "seq"      INTEGER UNIQUE ON CONFLICT REPLACE,
  "datetime" CHAR,
  "program"  CHAR,
  "pid"      INTEGER,
  "message"  CHAR);

CREATE TABLE "config" (
  "id"       INTEGER NOT NULL PRIMARY KEY ON CONFLICT REPLACE AUTOINCREMENT,
  "value"    INTEGER);

INSERT INTO config (id, value) VALUES (1, 10000); -- messages
INSERT INTO config (id, value) VALUES (2, 10000); -- audit
INSERT INTO config (id, value) VALUES (3, 10000); -- firewall

-- persistent setting (survives the database connection)
PRAGMA journal_mode=WAL;

CREATE TRIGGER "tr_config" AFTER UPDATE ON "config"
BEGIN
  DELETE FROM messages WHERE seq > ( SELECT value FROM config WHERE id = 1 );
  DELETE FROM audit WHERE seq > ( SELECT value FROM config WHERE id = 2 );
  DELETE FROM firewall WHERE seq > ( SELECT value FROM config WHERE id = 3 );
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
