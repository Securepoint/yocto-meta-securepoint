# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Squid interface for embedded adaptation modules"
HOMEPAGE = "http://www.e-cap.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c67103c8eb4811d77077483b82025b3"
SECTION = "libs"
PR = "r0"

SRC_URI = "https://www.e-cap.org/archive/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "ba3b9025264e6c23e6413666fff9fdab"
SRC_URI[sha256sum] = "7c166d997a079110dcc55e9b81eb70b4d73c08b2bcb986fa81bbf78bb62ec8f4"

inherit autotools-brokensep

do_configure:prepend () {
    cd ${S}
    touch NEWS README AUTHORS ChangeLog
}
