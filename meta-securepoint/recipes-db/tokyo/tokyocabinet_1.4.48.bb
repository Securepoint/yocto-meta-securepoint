DESCRIPTION = "Tokyo Cabinet is a library of routines for managing a database. The database is a simple data file containing records, each is a pair of a key and a value. Every key and value is serial bytes with variable length. Both binary data and character string can be used as a key and a value. There is neither concept of data tables nor data types. Records are organized in hash table, B+ tree, or fixed-length array."
SECTION = "database"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"


SRC_URI = "http://fallabs.com/tokyocabinet/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "fd03df6965f8f56dd5b8518ca43b4f5e"
SRC_URI[sha256sum] = "a003f47c39a91e22d76bc4fe68b9b3de0f38851b160bbb1ca07a4f6441de1f90"


DEPENDS = "zlib bzip2"

inherit gettext autotools-brokensep

EXTRA_OEMAKE += " PREFIX=/usr "

do_configure:prepend(){
    sed -i 's/^PATH=.*//g' configure.in
    sed -i 's/^CPATH=.*//g' configure.in
    sed -i 's/^LIBRARY_PATH=.*//g' configure.in
    sed -i 's/^LD_LIBRARY_PATH=.*//g' configure.in
    sed -i 's/^PKG_CONFIG_PATH=.*//g' configure.in
    sed -i -e "s:^MYCPPFLAGS=\"-I.*:MYCPPFLAGS=\"-I. -I${STAGING_INCDIR}\":" configure.in
    sed -i 's/^MYLDFLAGS=.*/MYLDFLAGS=\"-L\.\"/g' configure.in

}
FILES:${PN}-dev += " /usr/share/tokyocabinet/doc"
