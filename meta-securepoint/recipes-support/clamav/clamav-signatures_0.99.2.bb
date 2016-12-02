# Copyright (C) 2016 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Clam AntiVirus signatures"
HOMEPAGE = "http://www.clamav.net/"
LICENSE = "GPLv2"
PR = "r0"
BB_STRICT_CHECKSUM = "0"

inherit allarch

SRC_URI = "\
    http://database.clamav.net/main.cvd \
    http://database.clamav.net/daily.cvd \
    http://database.clamav.net/bytecode.cvd \
"

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

FILES_${PN} = "/data/clamav/*.cvd"
