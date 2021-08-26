# Copyright (C) 2020 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_append := "${THISDIR}/files:"
SRC_URI += "file://c_rehash"

do_install_append() {
        install -d ${D}/usr/bin
        install -m 0755 ${WORKDIR}/c_rehash ${D}/usr/bin
}
