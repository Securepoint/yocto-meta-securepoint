FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
# version 2021-05-31
SRCREV = "3568f962908cd9be9128de5cb732f4f58880a41f"
SRCREV = "4adb20b44c1aa98eff0f6c8311a9611b1c6db3a6"
PV = "20210531"

SRC_URI += "file://firmware-6.bin_WLAN.RM.4.4.1-00282-QCARMSWPZ-1"

require ${PN}_ath10k.inc

PACKAGES =+ " ${PN}-bnx2-rv2p "

FILES_${PN}-bnx2-mips = "\
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-06-6.2.1.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-06-6.2.3.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-09-6.2.1a.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-09-6.2.1b.fw \
"

FILES_${PN}-bnx2-rv2p = "\
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-06-6.0.15.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-09-6.0.17.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-09ax-6.0.17.fw \
"

LICENSE_${PN}-bnx2-rv2p = "WHENCE"
RDEPENDS_${PN}-bnx2-rv2p += "${PN}-whence-license"

do_install_append() {
	cp -f ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00282-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/firmware-6.bin
}
