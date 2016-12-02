# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Securepoint eCAP antivirus adapter for Squid"
HOMEPAGE = "https://github.com/Securepoint/squid-ecap-av/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "\
    file://LICENSE.txt;md5=751419260aa954499f7abaabaa882bbe \
    file://LICENSE-eCAP;md5=37eb2a32669d1a045a3b2235894af9dd \
"
SECTION = "net"
DEPENDS = "file libecap"
PR = "r0"

SRC_URI = "https://github.com/Securepoint/squid-ecap-av/archive/${PN}-v${PV}.tar.gz \
           file://custom-style.patch \
"
SRC_URI[md5sum] = "11e89175394103b834e8d41e9719d80e"
SRC_URI[sha256sum] = "d2d48a309cbd207252c6c25965bfaa97c8d1daaf1a95dc0c849229dea994fc24"

S = "${WORKDIR}/${PN}-${PN}-v${PV}"

inherit cmake

FILES_${PN} = "/usr/libexec/squid/ecap_adapter_av.so"
FILES_${PN}-dbg += "/usr/libexec/squid/.debug/ecap_adapter_av.so"
