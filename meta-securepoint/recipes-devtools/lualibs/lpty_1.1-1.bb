# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "a library that allows lua to start processes and control them via pty"
HOMEPAGE = "http://www.tset.de/lpty/"
LICENSE = "free"
LIC_FILES_CHKSUM = "file://doc/LICENSE;md5=f01dc254a838d0aac5517c635439977c"
SECTION = "lualibs"
DEPENDS = "lua5.1"
PR = "r0"

SRC_URI = "http://www.tset.de/downloads/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "bfff072ba9a090a38fbf4458875245c3"
SRC_URI[sha256sum] = "0d4ffda654dcf37dd8c99bcd100d0ee0dde7782cbd0ba9200ef8711c5cab02f1"

inherit autotools

EXTRA_OEMAKE += "INST_DIR=${D}/usr LUAVERSION=5.1 LUA_INCDIR=. LUA_LIBDIR=."

FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN} += "${libdir}/lua/5.1/"
