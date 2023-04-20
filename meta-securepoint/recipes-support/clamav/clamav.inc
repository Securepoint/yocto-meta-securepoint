# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Clam AntiVirus"
HOMEPAGE = "http://www.clamav.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "\
    file://COPYING.txt;md5=2c0b5770a62017a3121c69bb9f680b0c \
    file://COPYING/COPYING.bzip2;md5=ae8d555c34b656ff864ea9437a10d3a0 \
    file://COPYING/COPYING.file;md5=e63a61022c36cff2fdfbf02dd51674bd \
    file://COPYING/COPYING.getopt;md5=5d05c2548fde51bf7c7132e09b2947de \
    file://COPYING/COPYING.LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://COPYING/COPYING.llvm;md5=c82fc668ef1809acdd0684811df93bfc \
    file://COPYING/COPYING.lzma;md5=84044362a179fe47ab28fec03be352d0 \
    file://COPYING/COPYING.regex;md5=7a04124fed6082129732f23a7d16db60 \
    file://COPYING/COPYING.YARA;md5=58810d217b67bebe14e636bfe2595bdf \
    file://COPYING/COPYING.unrar;md5=6a741ba21afc8b71aeaee3b5f86a8111 \
    file://COPYING/COPYING.zlib;md5=3648a0b9713ab246e11536055165a41a \
"

SECTION = "Applications/File"
DEPENDS = "libxml2 zlib bzip2 openssl libpcre2 libtool json-c"
PR = "r0"

SRC_URI = "\
    https://www.clamav.net/downloads/production/${PN}-${PV}.tar.gz \
    file://clamav_fdpassing.patch \
    file://etc_sv_clamd_run \
    file://etc_sv_clamd_down \
"

inherit cmake useradd runit pkgconfig

RUNIT_SERVICES = "clamd"
PACKAGES:prepend = " ${PN}-clamd ${PN}-clamdscan ${PN}-update ${PN}-libs ${PN}-common "

USERADD_PACKAGES += "${PN}-common"
USERADD_PARAM:${PN}-common = "-r -d /var/empty -g clamav -s /bin/false clamav;"
GROUPADD_PARAM:${PN}-common = "-r -f clamav;"

EXTRA_OECMAKE += " -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_INSTALL_LIBDIR=/usr/lib \
                   -DAPP_CONFIG_DIRECTORY=/etc -DDATABASE_DIRECTORY=/data/clamav \
                   -DCMAKE_CROSSCOMPILING=false -DCMAKE_SKIP_RPATH=TRUE -DBYTECODE_RUNTIME=none \
                   -DPCRE2_LIBRARY=${STAGING_LIBDIR}/libpcre2-8.so -DPCRE2_INCLUDE_DIR=${STAGING_INCDIR} \
                   -DJSONC_LIBRARY=${STAGING_LIBDIR}/libjson-c.so -DJSONC_INCLUDE_DIR=${STAGING_INCDIR}/json-c \
                   -DZLIB_LIBRARY=${STAGING_LIBDIR}/libz.so -DZLIB_INCLUDE_DIR=${STAGING_INCDIR} \
                   -DLIBXML2_LIBRARY=${STAGING_LIBDIR}/libxml2.so -DLIBXML2_INCLUDE_DIR=${STAGING_INCDIR}/libxml2 \
                 "

PACKAGECONFIG = "unrar"
PACKAGECONFIG[milter] = "-DENABLE_MILTER=ON ,-DENABLE_MILTER=OFF, curl, curl"
PACKAGECONFIG[clamonacc] = "-DENABLE_CLAMONACC=ON ,-DENABLE_CLAMONACC=OFF,"
PACKAGECONFIG[unrar] = "-DENABLE_UNRAR=ON ,-DENABLE_UNRAR=OFF,"
PACKAGECONFIG[systemd] = "-DENABLE_SYSTEMD=ON -DSYSTEMD_UNIT_DIR=${systemd_system_unitdir}, -DENABLE_SYSTEMD=OFF, systemd"
PACKAGECONFIG[man] = "-DENABLE_MAN_PAGES=ON, -DENABLE_MAN_PAGES=OFF,"
PACKAGECONFIG[ctest] = "-DENABLE_TESTS=ON, -DENABLE_TESTS=OFF,"

do_configure:append() {
    # configure can't detect this if cross compiling
    echo "#define HAVE_FD_PASSING 1" >> ${B}/clamav-config.h
}

do_install:prepend() {
    # for ${PN}-common
    install -d ${D}/var/empty
}

FILES:${PN}-libs       = "${libdir}/libclam*.so.* ${libdir}/libfresh*.so.*"
FILES:${PN}-clamd      = "/usr/sbin/clamd /etc/clamd.* /etc/sv/clamd/* /var/service/clamd"
FILES:${PN}-clamdscan  = "/usr/bin/clamdscan"
FILES:${PN}-update     = "/usr/bin/freshclam /etc/freshclam.*"
FILES:${PN}-common     = "/var/empty"

RDEPENDS:${PN}        = "${PN}-common"
RDEPENDS:${PN}-clamd  = "${PN}-common"
RDEPENDS:${PN}-update = "${PN}-common"
