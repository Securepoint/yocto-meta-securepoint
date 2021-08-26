# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A library for storing and manipulating dates and times"
HOMEPAGE = "http://cr.yp.to/libtai.html"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://BLURB;md5=92b31ddb13d410b776ae656988682372"
SECTION = "djb"
DEPENDS = "groff-native"
PR = "r0"

SRC_URI = "http://cr.yp.to/libtai/${PN}-${PV}.tar.gz \
           file://dynlib.patch \
           file://rettype.patch \
"
SRC_URI[md5sum] = "97b581ec4aefc00f461cefe3c64be244"
SRC_URI[sha256sum] = "06dc42ccb77aee681fbd0e0754fc2180e1e9aeaa650838e9d74933d447a3d219"

FILES_${PN} = "/usr/lib/libtai.so.* /usr/include/libtai"

inherit autotools-brokensep

CLEANBROKEN = "1"

CC_append = " ${LDFLAGS}"

do_configure(){
    echo "${CC} -fPIC" > ${S}/conf-cc
    echo "${CC}" > ${S}/conf-ld
}

do_install(){
    install -d ${D}/usr/include/libtai
    install -d ${D}/usr/lib
    install -m 0644 ${WORKDIR}/${PN}-${PV}/*.h ${D}/usr/include/libtai
    install -m 0644 ${WORKDIR}/${PN}-${PV}/libtai.a ${D}/usr/lib/
    install -m 0644 ${WORKDIR}/${PN}-${PV}/libtai.so.1 ${D}/usr/lib/
    ln -sf libtai.so.1 ${D}/usr/lib/libtai.so
}

BBCLASSEXTEND = "native"
