# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "LuaFileSystem is a Lua library developed to complement the set of functions related to file systems offered by the standard Lua distribution."
HOMEPAGE = "http://keplerproject.github.com/luafilesystem"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1"
PR = "r0"

SRC_URI = "git://github.com/keplerproject/luafilesystem.git;branch=master;protocol=https;branch=master \
"
SRCREV = "8ff20138af779f6f38cbff12d22df1a3151d9fa6"

S = "${WORKDIR}/git"

inherit autotools-brokensep
EXTRA_OEMAKE += " PREFIX=/usr CC='${CC} ${CFLAGS} ${LDFLAGS} -fPIC' LUA_INC='${D}/${includedir}'"

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -m 555 -o root src/lfs.so ${D}${libdir}/lua/5.1/
}

FILES:${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev = "/usr/src"
FILES:${PN} += "${libdir}/lua/5.1/"
