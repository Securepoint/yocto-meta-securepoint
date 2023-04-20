# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "LuaCURL is Lua 5.x compatible module providing Internet browsing capabilities based on the cURL library. The module interface follows strictly the cURL architecture and is very easy to use if the programmer has already experience with cURL."
HOMEPAGE = "http://luaforge.net/projects/luacurl/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1 curl"
PR = "r0"

SRC_URI = "http://files.luaforge.net/releases/luacurl/luacurl/luacurl-1.2.1/luacurl-${PV}.zip \
"
SRC_URI[md5sum] = "4c83710a0fc5ca52818e5ec0101c4395"
SRC_URI[sha256sum] = "04318053e8eed6bc0b3da1574a07705378c59e1b6c4a01cadb48b11fb5964882"


do_configure(){
    :
}

do_compile(){
    ${CC} ${CFLAGS} -shared -fPIC -lcurl luacurl.c -o luacurl.so
}

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -m 555 -o root luacurl.so ${D}${libdir}/lua/5.1/
}

#INSANE_SKIP_${PN} += " dev-so "
FILES:${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev = "/usr/src"
FILES:${PN} += "${libdir}/lua/5.1/"
