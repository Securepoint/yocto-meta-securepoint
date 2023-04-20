# split ath10k firmware into smaller pieces
QCA6174_base = "https://github.com/kvalo/ath10k-firmware/raw/54ec6346a9bc8e615cc04e36c9346b09cfd17aaf/QCA6174/hw3.0/4.4.1"
SRC_URI:append = "\
	${QCA6174_base}/firmware-6.bin_WLAN.RM.4.4.1-00157-QCARMSWPZ-1;name=QCA6174-157 \
	${QCA6174_base}/firmware-6.bin_WLAN.RM.4.4.1-00241-QCARMSWPZ-1;name=QCA6174-241 \
	${QCA6174_base}/firmware-6.bin_WLAN.RM.4.4.1-00279-QCARMSWPZ-1;name=QCA6174-279 \
	${QCA6174_base}/firmware-6.bin_WLAN.RM.4.4.1-00282-QCARMSWPZ-1;name=QCA6174-282 \
	${QCA6174_base}/firmware-6.bin_WLAN.RM.4.4.1-00288-QCARMSWPZ-1;name=QCA6174-288 \
	file://qca6174.txt \
"
SRC_URI[QCA6174-157.sha256sum] = "7d1c695e420da5a09cd1c84b9d42e97dd7ab65fc44e94401a399701f4e487545"
SRC_URI[QCA6174-241.sha256sum] = "fa7dc30384f76786564c83569d8fb5d6b506d991286a1ba6d2214fd90c1f87e7"
SRC_URI[QCA6174-279.sha256sum] = "bb1fcd591d0e17d1e8191fa663d94a6bc272830384b3bd9b19d135210c849920"
SRC_URI[QCA6174-282.sha256sum] = "86eed6944a237da8687f39f050e08d5a952ca6c6cfe333bb12d6bdce7ee1bf83"
SRC_URI[QCA6174-288.sha256sum] = "05b5e488b75b1e21fbd38a8cfdd44abd2e5e0b4204f4a8f6cfbd70feef220603"

PACKAGES:prepend = "\
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
RDEPENDS:${PN}-ath10k =+ "\
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

FILES:${PN}-ath10k-QCA4019 = "${nonarch_base_libdir}/firmware/ath10k/QCA4019"
RDEPENDS:${PN}-ath10k-QCA4019 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA6174 = "${nonarch_base_libdir}/firmware/ath10k/QCA6174 /etc/firmware/ath10k/QCA6174 /etc/runit/1.d /usr/bin/qca6174"
RDEPENDS:${PN}-ath10k-QCA6174 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA9377 = "${nonarch_base_libdir}/firmware/ath10k/QCA9377"
RDEPENDS:${PN}-ath10k-QCA9377 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA9887 = "${nonarch_base_libdir}/firmware/ath10k/QCA9887"
RDEPENDS:${PN}-ath10k-QCA9887 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA9888 = "${nonarch_base_libdir}/firmware/ath10k/QCA9888"
RDEPENDS:${PN}-ath10k-QCA9888 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA988X = "${nonarch_base_libdir}/firmware/ath10k/QCA988X"
RDEPENDS:${PN}-ath10k-QCA988X = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA9984 = "${nonarch_base_libdir}/firmware/ath10k/QCA9984"
RDEPENDS:${PN}-ath10k-QCA9984 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-QCA99X0 = "${nonarch_base_libdir}/firmware/ath10k/QCA99X0"
RDEPENDS:${PN}-ath10k-QCA99X0 = "${PN}-ath10k-license"
FILES:${PN}-ath10k-WCN3990 = "${nonarch_base_libdir}/firmware/ath10k/WCN3990"
RDEPENDS:${PN}-ath10k-WCN3990 = "${PN}-ath10k-license"

do_install:append() {
        # see: https://forums.gentoo.org/viewtopic-p-8277412.html#8277412
        rm -f ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.0/firmware-6.bin
        rm -f ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.0/notice_ath10k_firmware-6.txt 

	install -d ${D}/etc/firmware/ath10k/QCA6174/hw3.0/
	cd ${D}/etc/firmware/ath10k/QCA6174/hw3.0/
	ln -sf /lib/firmware/ath10k/QCA6174/hw3.0/00241/firmware-6.bin .
	cd ${D}/lib/firmware/ath10k/QCA6174/hw3.0/
	rm -f firmware-6.bin
	ln -sf /etc/firmware/ath10k/QCA6174/hw3.0/firmware-6.bin .
	install -m 755 -D ${WORKDIR}/qca6174.txt ${D}/usr/bin/qca6174
	install -d ${D}/etc/runit/1.d
	cd ${D}/etc/runit/1.d
	ln -sf /usr/bin/qca6174 03-qca6174

	install -D ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00157-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/00157/firmware-6.bin
	install -D ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00241-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/00241/firmware-6.bin
	install -D ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00279-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/00279/firmware-6.bin
	install -D ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00282-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/00282/firmware-6.bin
	install -D ${WORKDIR}/firmware-6.bin_WLAN.RM.4.4.1-00288-QCARMSWPZ-1 ${D}/lib/firmware/ath10k/QCA6174/hw3.0/00288/firmware-6.bin
}
