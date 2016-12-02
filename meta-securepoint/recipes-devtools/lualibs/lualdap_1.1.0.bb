# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "LuaLDAP is a simple interface from Lua to an LDAP client (in fact it's a bind to OpenLDAP client). LuaLDAP is free software and uses the same license as Lua 5.0"
HOMEPAGE = "http://luaforge.net/projects/lualdap/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1 openldap"
PR = "r0"

SRC_URI = "git://github.com/luaforge/${BPN}.git;protocol=https \
"

SRCREV = "812aaea85b1041033a9fd0abab2f0a3948452e2f"

S = "${WORKDIR}/git/lualdap"

inherit autotools-brokensep

EXTRA_OEMAKE += "CC='${CC}' CFLAGS='${CFLAGS} -fPIC' LUA_LIBDIR=${D}${libdir}/lua/5.1 LIB_OPTION='-shared -lldap' LUA_VERSION_NUM=501 COMPAT_DIR=."


INSANE_SKIP_${PN} += " dev-so "
FILES_${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES_${PN}-dev = "/usr/src"
FILES_${PN} += "${libdir}/lua/5.1/"
