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

SQUIDECAPAV_BRANCH ?= "master"
SQUIDECAPAV_SRCREV ?= "${AUTOREV}"

SRC_URI = "git://github.com/Securepoint/squid-ecap-av.git;protocol=https;branch=${SQUIDECAPAV_BRANCH} \
           file://custom-style.patch \
"
S = "${WORKDIR}/git"
SRCREV = "${SQUIDECAPAV_SRCREV}"
PV = "${SRCREV}+git${SRCPV}"
PR = "r1"

inherit cmake

FILES_${PN} = "/usr/libexec/squid/ecap_adapter_av.so"
FILES_${PN}-dbg += "/usr/libexec/squid/.debug/ecap_adapter_av.so"
