# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Integrate the yajl JSON library with Lua."
HOMEPAGE = "https://github.com/brimworks/lua-yajl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=26;md5=2e0dfe4eec78b5add7c622e01b109c22"
SECTION = "lualibs"
DEPENDS = "yajl lua5.1"
PR = "r0"

SRC_URI = "git://github.com/brimworks/lua-yajl;protocol=https;branch=master \
file://yajl-rettype.patch \
"
#SRCREV = "v${PV}"
SRCREV = "078e48147e89d34b8224a07129675aa9b5820630"
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DINSTALL_CMOD=${libdir}/lua/5.1"

FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN} += "${libdir}/lua/5.1/"
