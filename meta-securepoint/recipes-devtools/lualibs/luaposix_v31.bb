# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "luaposix is a POSIX binding, including curses, for Lua 5.1 and 5.2; like most libraries it simply binds to C APIs on the underlying system, so it won't work on non-POSIX systems. However, it does try to detect the level of POSIX conformance of the underlying system and bind only available APIs."
HOMEPAGE = "https://github.com/luaposix/luaposix"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=0de24acdcec9cb21ff095356966cbef2"

SECTION = "lualibs"
DEPENDS = "lua5.1 lua5.1-native libgcrypt"
PR = "r0"

RDEPENDS:${PN} += "luabitop"
SRC_URI = "git://github.com/luaposix/luaposix;protocol=https;branch=master;name=luaposix \
git://github.com/gvvaughan/slingshot.git;protocol=https;name=slingshot;branch=master;destsuffix=git/slingshot \
git://git.sv.gnu.org/gnulib.git;name=gnulib;branch=master;destsuffix=git/gnulib \
"
#SRCREV = "${PV}"
SRCREV_luaposix = "8a836e379c40e40d7b35a570c01cdd77c93a1d68"
SRCREV_slingshot = "7e3f4e7052d3dadce57357edf5f5d75630b75286"
SRCREV_gnulib = "efa0065f1682f53fb15ad427555ddedec6ec51eb"
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

EXTRA_OEMAKE += " DESTDIR=${D} datadir=${datadir}/lua/5.1 libdir=${libdir}/lua/5.1"
PARALLEL_MAKE = ""

do_configure:prepend(){
    #the bootstraps clones the gnulib from gnu.org. the url
    # is not configurable, so if download fails, the next line can be used to
    # get the sources from somewhere else
    
    #git clone https://github.com/gagern/gnulib.git ./gnulib
    #
    #sed -i 's|git://github.com/gvvaughan/slingshot.git|https://github.com/gvvaughan/slingshot.git|' ./bootstrap.slingshot
    #GIT_SSL_CAINFO="${STAGING_DIR_NATIVE}/etc/ssl/certs/ca-certificates.crt" ./bootstrap --gnulib-srcdir=./gnulib
    ./bootstrap --gnulib-srcdir=./gnulib
}

FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev += "/usr/include/lua.hpp"
FILES:${PN} += "${libdir}/lua/5.1/ ${datadir}/lua/5.1/"

