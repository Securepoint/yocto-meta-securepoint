# Copyright (C) 2017 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Ctags generates an index (or tag) file of language objects found in source files that allows these items to be quickly and easily located by a text editor or other utility. A tag signifies a language object for which an index entry is available (or, alternatively, the index entry created for that object)."
HOMEPAGE = "http://ctags.sourceforge.net/"
LICENSE = "GPLv2"
SECTION = "tools"
DEPENDS = ""
PR = "r0"

SRC_URI = "http://prdownloads.sourceforge.net/ctags/${BP}.tar.gz"

SRC_URI[md5sum] = "c00f82ecdcc357434731913e5b48630d"
SRC_URI[sha256sum] = "0e44b45dcabe969e0bbbb11e30c246f81abe5d32012db37395eb57d66e9e99c7"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit autotools

#make install doesnt honour $DESTDIR
do_install(){
    mkdir -p ${D}/usr/bin/
    cp ctags ${D}/usr/bin/
}
