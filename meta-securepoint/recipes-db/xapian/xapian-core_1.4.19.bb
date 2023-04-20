# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Xapian is an Open Source Search Engine Library, released under the GPL. It's written in C++, with bindings to allow use from Perl, Python, PHP, Java, Tcl, C#, Ruby, Lua and Erlang (so far!)"
HOMEPAGE = "http://xapian.org/"
LICENSE = "GPLv2"
SECTION = "databases"
DEPENDS = "zlib"

SRC_URI = "http://oligarchy.co.uk/xapian/${PV}/xapian-core-${PV}.tar.xz"
SRC_URI[sha256sum] = "1fca48fca6cc3526cc4ba93dd194fe9c1326857b78edcfb37e68d086d714a9c3"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

inherit autotools

FILES:${PN}-dev += "/usr/lib/cmake"

#do_configure:append(){
#    sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' ${TARGET_ARCH}-poky-${TARGET_OS}-libtool
#    sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' ${TARGET_ARCH}-poky-${TARGET_OS}-libtool
#}
