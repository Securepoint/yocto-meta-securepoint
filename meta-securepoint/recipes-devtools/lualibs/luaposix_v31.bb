# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "luaposix is a POSIX binding, including curses, for Lua 5.1 and 5.2; like most libraries it simply binds to C APIs on the underlying system, so it won't work on non-POSIX systems. However, it does try to detect the level of POSIX conformance of the underlying system and bind only available APIs."
HOMEPAGE = "https://github.com/luaposix/luaposix"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=0de24acdcec9cb21ff095356966cbef2"

SECTION = "lualibs"
DEPENDS = "lua5.1 lua5.1-native libgcrypt"
PR = "r0"

RDEPENDS_${PN} += "luabitop"
SRC_URI = "git://github.com/luaposix/luaposix;protocol=https;branch=master"
SRCREV = "${PV}"
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

EXTRA_OEMAKE += " DESTDIR=${D} datadir=${datadir}/lua/5.1 libdir=${libdir}/lua/5.1"
PARALLEL_MAKE = ""

do_configure_prepend(){
    #the bootstraps clones the gnulib from gnu.org. the url
    # is not configurable, so if download fails, the next line can be used to
    # get the sources from somewhere else
    
    #git clone git://github.com/gagern/gnulib ./gnulib

    ./bootstrap --gnulib-srcdir=./gnulib
}

FILES_${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES_${PN} += "${libdir}/lua/5.1/ ${datadir}/lua/5.1/"

