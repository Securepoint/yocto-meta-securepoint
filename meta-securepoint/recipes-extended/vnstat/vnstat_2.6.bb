# Copyright (C) 2016 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = ""
HOMEPAGE = "http://humdi.net/vnstat/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SECTION = "console/utils"
DEPENDS = "gd sqlite3"
PR = "r0"

SRC_URI = "\
  http://humdi.net/vnstat/vnstat-${PV}.tar.gz \
"
SRC_URI[md5sum] = "fe2928a81243cc8a532a357f97221736"
SRC_URI[sha256sum] = "89276e0a7281943edb554b874078278ad947dc312938a2451e03eb80679f7ff7"

inherit autotools pkgconfig

EXTRA_OECONF_append = "--disable-extra-paths"

PACKAGES_prepend += " ${PN}i "
FILES_${PN}i += "/usr/bin/vnstati"
RDEPENDS_${PN}i = "${PN}"
INSANE_SKIP_${PN} = "${ERROR_QA}"
