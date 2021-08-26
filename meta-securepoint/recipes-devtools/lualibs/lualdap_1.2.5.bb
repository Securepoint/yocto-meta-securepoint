# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "LuaLDAP is a simple interface from Lua to an LDAP client (in fact it's a bind to OpenLDAP client). LuaLDAP is free software and uses the same license as Lua 5.0"
HOMEPAGE = "http://luaforge.net/projects/lualdap/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1 openldap"
PR = "r0"

SRC_URI = "git://github.com/lualdap/lualdap.git;protocol=https \
"

SRCREV = "v${PV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OEMAKE += "CC='${CC} ${CFLAGS} ${LDFLAGS}' LD='${CC}' DESTDIR=${D}"

do_configure_prepend(){
	sed -ie 's#LUA_VERSION :=.*#LUA_VERSION := 5.1#' ${S}/config
	sed -ie 's#LUA_INCDIR =.*#LUA_INCDIR =${STAGING_INCDIR}/lua5.1 #' ${S}/config
	sed -ie 's#LUA_LIBDIR =.*#LUA_LIBDIR =${STAGING_LIBDIR}/lua/5.1 #' ${S}/config
	sed -ie 's#LDAP_INCDIR =.*#LDAP_INCDIR = ${STAGING_INCDIR} #' ${S}/config
	sed -ie 's#LDAP_LIBDIR =.*#LDAP_LIBDIR =${STAGING_LIBDIR} #' ${S}/config
	sed -ie 's#LBER_INCDIR =.*#LBER_INCDIR = ${STAGING_INCDIR} #' ${S}/config
	sed -ie 's#LBER_LIBDIR =.*#LBER_LIBDIR =${STAGING_LIBDIR} #' ${S}/config

}
do_install_prepend(){
	mkdir -p ${D}/${libdir}/lua/5.1
}

INSANE_SKIP_${PN} += " dev-so "

FILES_${PN}-dev = "/usr/src"
FILES_${PN} += "${libdir}/lua/5.1/"
