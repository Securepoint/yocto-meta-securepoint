# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "various firmware blobs"
LICENSE = "CLOSED"
PR = "r0"

SRC_URI = "http://ftp.de.debian.org/debian/pool/non-free/f/firmware-nonfree/firmware-nonfree_${PV}.orig.tar.xz"
SRC_URI[md5sum] = "73adbd7e95beed7f46966337e05cca65"
SRC_URI[sha256sum] = "3a6d58b518d30f18ade4914b66e8788fd32e19ec4c86a6eddbbe58d349a81c12"


S = "${WORKDIR}"
PACKAGES = "${PN}-r816x ${PN}-bnx2 ${PN}-bnx2x ${PN}-ath6k ${PN}-ath10k"

do_install() {
    install -d ${D}/lib/firmware
    cp -a ${WORKDIR}/firmware-nonfree-${PV}/rtl_nic   ${D}/lib/firmware
    cp -a ${WORKDIR}/firmware-nonfree-${PV}/bnx2      ${D}/lib/firmware
    cp -a ${WORKDIR}/firmware-nonfree-${PV}/bnx2x     ${D}/lib/firmware
    cp -a ${WORKDIR}/firmware-nonfree-${PV}/ath6k     ${D}/lib/firmware
    cp -a ${WORKDIR}/firmware-nonfree-${PV}/ath10k    ${D}/lib/firmware
}

DESCRIPTION_${PN}-r816x = "Binary firmware for Realtek Ethernet adapters supported by the r8169 driver."
DESCRIPTION_${PN}-bnx2  = "Binary firmware for Broadcom NetXtremeII network adapters supported by the bnx2 driver"
DESCRIPTION_${PN}-bnx2x = "Binary firmware for Broadcom NetXtreme II 10Gb network adapters supported by the bnx2x driver"
DESCRIPTION_${PN}-ath6k = "Binary firmware for Atheros network adapters supported by the ath6k driver"
DESCRIPTION_${PN}-ath10k = "Binary firmware for Atheros network adapters supported by the ath10k driver"

FILES_${PN}-r816x = "/lib/firmware/rtl_nic"
FILES_${PN}-bnx2  = "/lib/firmware/bnx2"
FILES_${PN}-bnx2x = "/lib/firmware/bnx2x"
FILES_${PN}-ath6k = "/lib/firmware/ath6k"
FILES_${PN}-ath10k = "/lib/firmware/ath10k"
