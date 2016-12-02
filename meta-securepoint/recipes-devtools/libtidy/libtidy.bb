# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "tidy up html/xml"
HOMEPAGE = "http://tidy.sourceforge.net"
LICENSE = "CLOSED"
SECTION = "libs"
DEPENDS = ""
PR = "r0"

#SRC_URI = "cvs://tidy.cvs.sourceforge.net/cvsroot/tidy;protocol=cvs;module=tidy/tidy;date=2009-03-25"
SRC_URI = "git://github.com/toddr/libtidy.git;protocol=http"
SRCREV = "438749153abffcfb7214ae2632e17c5e51bc7bb3"
S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OEMAKE = "-C build/gmake/ CC='${CC}' devinst_prefix='${D}/usr' runinst_prefix='${D}/usr'"
PARALLEL_MAKE = ""
FILES_${PN}-dev += "/usr/man"

do_configure(){
    #manpage install needs tidy itself to install so if you really want them, 
    #you have to build tidy native AND manipulate the static Makefile to use the native tidy
    sed -i 's/install: installhdrs installib installexes installmanpage/install: installhdrs installib installexes/' ${S}/build/gmake/Makefile
}
