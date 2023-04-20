# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "tidy up html/xml"
HOMEPAGE = "http://www.html-tidy.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README/LICENSE.txt;md5=fca2fcc0c318cb66d3871f8906117b17"
SECTION = "libs"
DEPENDS = ""
PR = "r0"

#SRC_URI = "cvs://tidy.cvs.sourceforge.net/cvsroot/tidy;protocol=cvs;module=tidy/tidy;date=2009-03-25"
#SRC_URI = "git://github.com/toddr/libtidy.git;protocol=http"

SRC_URI = "git://github.com/htacg/tidy-html5.git;protocol=https;branch=next"
SRCREV = "3a30f6a4300417674026f6dddea5973debc6b808"
#branch=release/5.6"

S = "${WORKDIR}/git"

inherit cmake
EXTRA_OECMAKE += "\
 -DINCLUDE_INSTALL_DIR=include/tidy \
"

do_install:append() {
 # install deprecated compatibility headers
 install -m 0644 ${S}/include/buffio.h ${D}/usr/include/tidy/
 install -m 0644 ${S}/include/platform.h ${D}/usr/include/tidy/
}
