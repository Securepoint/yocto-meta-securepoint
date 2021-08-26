# split ath10k firmware into smaller pieces
PACKAGES_prepend += "\
        ${PN}-ath10k-QCA4019 \
        ${PN}-ath10k-QCA6174 \
        ${PN}-ath10k-QCA9377 \
        ${PN}-ath10k-QCA988X \
        ${PN}-ath10k-QCA9887 \
        ${PN}-ath10k-QCA9888 \
        ${PN}-ath10k-QCA9984 \
        ${PN}-ath10k-QCA99X0 \
        ${PN}-ath10k-WCN3990 \
"
RDEPENDS_${PN}-ath10k =+ "\
        ${PN}-ath10k-QCA4019 \
        ${PN}-ath10k-QCA6174 \
        ${PN}-ath10k-QCA9377 \
        ${PN}-ath10k-QCA988X \
        ${PN}-ath10k-QCA9887 \
        ${PN}-ath10k-QCA9888 \
        ${PN}-ath10k-QCA9984 \
        ${PN}-ath10k-QCA99X0 \
        ${PN}-ath10k-WCN3990 \
"

FILES_${PN}-ath10k-QCA4019 = "${nonarch_base_libdir}/firmware/ath10k/QCA4019"
RDEPENDS_${PN}-ath10k-QCA4019 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA6174 = "${nonarch_base_libdir}/firmware/ath10k/QCA6174"
RDEPENDS_${PN}-ath10k-QCA6174 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA9377 = "${nonarch_base_libdir}/firmware/ath10k/QCA9377"
RDEPENDS_${PN}-ath10k-QCA9377 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA9887 = "${nonarch_base_libdir}/firmware/ath10k/QCA9887"
RDEPENDS_${PN}-ath10k-QCA9887 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA9888 = "${nonarch_base_libdir}/firmware/ath10k/QCA9888"
RDEPENDS_${PN}-ath10k-QCA9888 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA988X = "${nonarch_base_libdir}/firmware/ath10k/QCA988X"
RDEPENDS_${PN}-ath10k-QCA988X = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA9984 = "${nonarch_base_libdir}/firmware/ath10k/QCA9984"
RDEPENDS_${PN}-ath10k-QCA9984 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-QCA99X0 = "${nonarch_base_libdir}/firmware/ath10k/QCA99X0"
RDEPENDS_${PN}-ath10k-QCA99X0 = "${PN}-ath10k-license"
FILES_${PN}-ath10k-WCN3990 = "${nonarch_base_libdir}/firmware/ath10k/WCN3990"
RDEPENDS_${PN}-ath10k-WCN3990 = "${PN}-ath10k-license"

do_install_append() {
        # see: https://forums.gentoo.org/viewtopic-p-8277412.html#8277412
        rm -f ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.0/firmware-6.bin
        rm -f ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.0/notice_ath10k_firmware-6.txt 
}
