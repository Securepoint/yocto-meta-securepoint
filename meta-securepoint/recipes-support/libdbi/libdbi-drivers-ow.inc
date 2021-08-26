# libdbi-drivers OE build file
# Copyright (C) 2005, Koninklijke Philips Electronics NV.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

DESCRIPTION = "Database Drivers for libdbi"
HOMEPAGE = "http://libdbi-drivers.sourceforge.net/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499"
SECTION = "libs"

DEPENDS = "libdbi"

SRC_URI = "${SOURCEFORGE_MIRROR}/libdbi-drivers/libdbi-drivers-${PV}.tar.gz"
S = "${WORKDIR}/libdbi-drivers-${PV}"

inherit autotools

PACKAGES += " libdbd-sqlite libdbd-sqlite3 libdbd-mysql libdbd-pgsql"
PACKAGECONFIG ??= "sqlite3"

PACKAGECONFIG[sqlite] = "--with-sqlite,--without-sqlite,sqlite"
PACKAGECONFIG[sqlite3] = "--with-sqlite3,--without-sqlite3,sqlite3"
PACKAGECONFIG[mysql] = "--with-mysql,--without-mysql,mysql5"
PACKAGECONFIG[pgsql] = "--with-pgsql,--without-pgsql,postgresql"

EXTRA_OECONF = "--with-dbi-incdir=${STAGING_INCDIR} \
                --with-dbi-libdir=${STAGING_LIBDIR} \
   		--disable-docs"

# don't build the tests
do_configure_prepend () {
	sed -i -e "s/^\(SUBDIRS.*\) tests\(.*\)/\1\2/g" ${S}/Makefile.am
}

FILES_${PN}-dbg += " ${libdir}/dbd/.debug/*.so"
FILES_${PN}-dev += " ${libdir}/dbd/*.la"
FILES_${PN}-staticdev += " ${libdir}/dbd/*.a"

DESCRIPTION_libdbd-sqlite = "SQLite database driver for libdbi"
FILES_libdbd-sqlite = "${libdir}/dbd/libdbdsqlite.so"

DESCRIPTION_libdbd-sqlite3 =  "SQLite3 database driver for libdbi"
FILES_libdbd-sqlite3 = "${libdir}/dbd/libdbdsqlite3.so"

DESCRIPTION_libdbd-mysql = "MySQL5 database driver for libdbi"
FILES_libdbd-mysql = "${libdir}/dbd/libdbdmysql.so"

DESCRIPTION_libdbd-psql = "Postgres SQL database driver for libdbi"
FILES_libdbd-pgsql = "${libdir}/dbd/libdbdpgsql.so"

