# Copyright (C) 2013 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "djbs dns/dhcp server and tools"
HOMEPAGE = "http://cr.yp.to/djbdns.html"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=b96ee8c257dbd13132e48a5c31e6b20c"
SECTION = "djb"

SRC_URI = "http://cr.yp.to/djbdns/${PN}-${PV}.tar.gz \
           file://cross.patch \
           file://errno.patch"
SRC_URI[md5sum] = "3147c5cd56832aa3b41955c7a51cbeb2"
SRC_URI[sha256sum] = "3ccd826a02f3cde39be088e1fc6aed9fd57756b8f970de5dc99fcd2d92536b48"

do_configure:append(){
    echo ${CC} > conf-cc
    echo ${CC} > conf-ld
    echo /${D} > conf-home
    echo -e "#include \"auto_home.h\"
const char auto_home[]=\"/${D}\";" > auto_home.c
}

PARALLEL_MAKE = ""

do_compile(){
    make prog
}

