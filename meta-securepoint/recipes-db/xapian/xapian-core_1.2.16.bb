# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Xapian is an Open Source Search Engine Library, released under the GPL. It's written in C++, with bindings to allow use from Perl, Python, PHP, Java, Tcl, C#, Ruby, Lua and Erlang (so far!)"
HOMEPAGE = "http://xapian.org/"
LICENSE = "GPLv2"
SECTION = "databases"
DEPENDS = "util-linux"

SRC_URI = "http://oligarchy.co.uk/xapian/1.2.16/xapian-core-1.2.16.tar.xz"
SRC_URI[md5sum] = "c6cc420f464711434edd19594b9d434b"
SRC_URI[sha256sum] = "f4ecef14297136e498f55670df9b80800244bc21abb8e01d5f9dab65a1fae9b9"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

inherit autotools

FILES_${PN}-dev += "/usr/lib/cmake"

do_configure_append(){
    sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' ${TARGET_ARCH}-poky-${TARGET_OS}-libtool
    sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' ${TARGET_ARCH}-poky-${TARGET_OS}-libtool
}
