# Copyright (C) 2016 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = ""
HOMEPAGE = "http://humdi.net/vnstat/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SECTION = "console/utils"
DEPENDS = "gd"
PR = "r0"

SRC_URI = "\
  http://humdi.net/vnstat/vnstat-${PV}.tar.gz \
  file://vnstat-cross-build.patch \
"
SRC_URI[md5sum] = "351051ef3005e3ca99123eec07ac0a7d"
SRC_URI[sha256sum] = "c3814b5baa8b627198a8debfe1dce4b4346a342523818cc8668a5497971dbc39"

inherit autotools pkgconfig

PACKAGES_prepend += " ${PN}i "
FILES_${PN}i += "/usr/bin/vnstati"
RDEPENDS_${PN}i = "${PN}"
