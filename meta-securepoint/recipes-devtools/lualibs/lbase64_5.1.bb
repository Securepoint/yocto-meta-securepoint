# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A base64 library for Lua."
HOMEPAGE = "http://www.tecgraf.puc-rio.br/~lhf/ftp/lua/#lbase64"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1"
PR = "r0"

SRC_URI = "git://${GIT_HOSTNAME}/mas/admin-backend-util-base64.git;protocol=${GIT_PROTO};branch=master \
file://lua-base64-make-no-test.patch \
"
S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"

inherit autotools-brokensep
EXTRA_OEMAKE += " PREFIX=/usr CC='${CC} -fPIC -Wl,--hash-style=gnu' LUA=${D}/usr"

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -m 555 -o root base64.so ${D}${libdir}/lua/5.1/
}

FILES:${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev = "/usr/src"
FILES:${PN} += "${libdir}/lua/5.1/"
