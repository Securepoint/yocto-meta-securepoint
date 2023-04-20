# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Lanes is a lightweight, native, lazy evaluating multithreading library for Lua 5.1 and 5.2."
HOMEPAGE = "https://github.com/LuaLanes/lanes"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=f06b0c2e052dc17b49def968ecfc53e6"

SECTION = "lualibs"
DEPENDS = "lua5.1"
PR = "r0"

SRC_URI = "git://github.com/LuaLanes/lanes;protocol=https;branch=master"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OEMAKE += " CFLAGS='-Wno-error' PREFIX=/usr DESTDIR=${D}/usr"

do_install(){
    install -d ${D}${datadir}/lua/5.1
    install -d ${D}${libdir}/lua/5.1
    install -m 555 src/lanes/core.so ${D}${libdir}/lua/5.1/
    install -m 555 src/lanes.lua ${D}${datadir}/lua/5.1/
}

FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN} += "${libdir}/lua/5.1/ ${datadir}/lua/5.1/"

