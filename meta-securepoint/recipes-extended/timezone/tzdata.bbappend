# from tzdata.bb:
# "slim" is the default since 2020b
# "fat" is needed by e.g. MariaDB's mysql_tzinfo_to_sql
# with slim syslog-ng always use UTC zone -> Ticket #20775
ZIC_FMT = "fat"
