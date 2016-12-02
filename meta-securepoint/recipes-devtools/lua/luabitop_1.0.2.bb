# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Lua BitOp is a C extension module for Lua 5.1/5.2 which adds bitwise operations on numbers."
HOMEPAGE = "http://bitop.luajit.org/index.html"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lua"
DEPENDS = "lua5.1"
BBCLASSEXTEND = "native"
PR = "r0"

SRC_URI = "http://bitop.luajit.org/download/LuaBitOp-${PV}.tar.gz"
SRC_URI[md5sum] = "d0c1080fe0c844e8477279668e2d0d06"
SRC_URI[sha256sum] = "1207c9293dcd52eb9dca6538d1b87352bd510f4e760938f5048433f7f272ce99"
# YES! we are CamelCase!
S = "${WORKDIR}/LuaBitOp-${PV}"

inherit autotools-brokensep

EXTRA_OEMAKE += " CC='${CC}' LD='${CC}' PREFIX=/usr DESTDIR=${D}"
do_compile_prepend(){
   sed -i -e "s;CC= gcc;CC= ${CC};" Makefile
   sed -i -e "s;-I/usr/local/include;;" Makefile
}

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -p bit.so ${D}${libdir}/lua/5.1/
}

FILES_${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES_${PN} += "${libdir}/lua/5.1/"
