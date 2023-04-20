FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

require ${PN}_ath10k.inc

PACKAGES =+ "\
	${PN}-bnx2-rv2p \
	${PN}-rtw88 \
	${PN}-rtw88-8723d \
	${PN}-rtw88-8821c \
	${PN}-rtw88-8822b \
	${PN}-rtw88-8822c \
	${PN}-iwlwifi-ac9560 \
	${PN}-iwlwifi-ax210 \
	${PN}-iwlwifi-ax211 \
	${PN}-iwlwifi-ax411 \
"

FILES:${PN}-bnx2-mips = "\
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-06-6.2.1.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-06-6.2.3.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-09-6.2.1a.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-mips-09-6.2.1b.fw \
"

FILES:${PN}-bnx2-rv2p = "\
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-06-6.0.15.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-09-6.0.17.fw \
	${nonarch_base_libdir}/firmware/bnx2/bnx2-rv2p-09ax-6.0.17.fw \
"
FILES:${PN}-rtw88 = "${nonarch_base_libdir}/firmware/rtw88/README"

FILES:${PN}-rtw88-8723d = "${nonarch_base_libdir}/firmware/rtw88/rtw8723d_fw.bin"
RDEPENDS:${PN}-rtw88-8723d += "${PN}-rtw88"

FILES:${PN}-rtw88-8821c = "${nonarch_base_libdir}/firmware/rtw88/rtw8821c_fw.bin"
RDEPENDS:${PN}-rtw88-8821c += "${PN}-rtw88"

FILES:${PN}-rtw88-8822b = "${nonarch_base_libdir}/firmware/rtw88/rtw8822b_fw.bin"
RDEPENDS:${PN}-rtw88-8822b += "${PN}-rtw88"

FILES:${PN}-rtw88-8822c = "\
	${nonarch_base_libdir}/firmware/rtw88/rtw8822c_fw.bin \
	${nonarch_base_libdir}/firmware/rtw88/rtw8822c_wow_fw.bin \
"
RDEPENDS:${PN}-rtw88-8822c += "${PN}-rtw88"

IWLPRE = "${nonarch_base_libdir}/firmware/iwlwifi"

FILES:${PN}-iwlwifi-ac9560 = "${IWLPRE}-so-a0-jf-b0-*.ucode"
FILES:${PN}-iwlwifi-ax210 = "${IWLPRE}-so-a0-hr-b0-*.ucode ${IWLPRE}-ty-a0-gf-a0-*.ucode"
FILES:${PN}-iwlwifi-ax211 = "${IWLPRE}-so-a0-gf-a0-*.ucode ${IWLPRE}-SoSnj-a0-gf-a0-*.ucode"
FILES:${PN}-iwlwifi-ax411 = "${IWLPRE}-so-a0-gf4-a0-*.ucode ${IWLPRE}-SoSnj-a0-gf4-a0-*.ucode"
RDEPENDS:${PN}-iwlwifi-ac9560 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-ax210 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-ax211 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-ax411 = "${PN}-iwlwifi-license"

LICENSE_${PN}-bnx2-rv2p = "WHENCE"
RDEPENDS:${PN}-bnx2-rv2p += "${PN}-whence-license"

RDEPENDS:${PN}-dev = ""
