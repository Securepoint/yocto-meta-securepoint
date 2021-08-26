# enables an optional ORDER BY and LIMIT clause on UPDATE and DELETE statements
BUILD_CFLAGS += " -DSQLITE_ENABLE_UPDATE_DELETE_LIMIT -DSQLITE_SECURE_DELETE"
TARGET_CFLAGS += " -DSQLITE_ENABLE_UPDATE_DELETE_LIMIT -DSQLITE_SECURE_DELETE"

PACKAGECONFIG = "editline dyn_ext"

require sqlite3_version.inc
