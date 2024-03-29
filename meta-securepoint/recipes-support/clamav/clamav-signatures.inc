# Copyright (C) 2016 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Clam AntiVirus signatures"
HOMEPAGE = "http://www.clamav.net/"
LICENSE = "GPLv2"
PR = "r0"

inherit allarch

SRC_URI = "\
    http://build.intern.securepoint.de/downloads/${PN}/${PV}/main.cvd;name=main.cvd \
    http://build.intern.securepoint.de/downloads/${PN}/${PV}/daily.cvd;name=daily.cvd \
    http://build.intern.securepoint.de/downloads/${PN}/${PV}/bytecode.cvd;name=bytecode.cvd \
"

do_patch() {
}
do_configure() {
}
do_qa_configure() {
} 

do_install() {
    install -d ${D}/data/clamav
    cp ${WORKDIR}/main.cvd     ${D}/data/clamav/main.cvd
    cp ${WORKDIR}/daily.cvd    ${D}/data/clamav/daily.cvd
    cp ${WORKDIR}/bytecode.cvd ${D}/data/clamav/bytecode.cvd
}

FILES:${PN} = "/data/clamav/*.cvd"
