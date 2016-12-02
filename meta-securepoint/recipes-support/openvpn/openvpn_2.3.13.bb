SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5aac200199fde47501876cba7263cb0c"
DEPENDS = "lzo openssl libpam"

inherit autotools

SRC_URI = "\
        http://swupdate.openvpn.org/community/releases/openvpn-${PV}.zip \
        file://openvpn-2.3.0-challenge.patch \
        file://openvpn-2.3.0-framedip.patch \
	file://openvpn-param-size.patch \
        file://openvpn"

SRC_URI[md5sum] = "10b7b1de84dfa45ed8ff8f39a1011371"
SRC_URI[sha256sum] = "05c1c5c5379a5338a188a65fba2a3c3ba23c1ce11f1dfefcafb4e6ec0db13dd5"

CFLAGS += "-fno-inline"

EXTRA_OECONF += "--enable-password-save --enable-iproute2"
PACKAGES_prepend = " ${PN}-sysvinit ${PN}-plugins-auth-pam ${PN}-plugins-down-root "

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/openvpn
    install -m 755 ${WORKDIR}/openvpn ${D}/${sysconfdir}/init.d
}

RRECOMMENDS_${PN} = "kernel-module-tun"

FILES_${PN}-plugins-auth-pam = "/usr/lib/openvpn/plugins/openvpn-plugin-auth-pam.so"
FILES_${PN}-plugins-down-root = "/usr/lib/openvpn/plugins/openvpn-plugin-down-root.so"
FILES_${PN}-sysvinit  = "${sysconfdir}/init.d"
FILES_${PN}-dev += "/usr/lib/openvpn/plugins/*.la"
FILES_${PN}-dbg += "/usr/lib/openvpn/plugins/.debug"

RDEPENDS_${PN}-plugins-auth-pam = "${PN}"
RDEPENDS_${PN}-plugins-down-root = "${PN}"
RDEPENDS_${PN}-sysvinit = "${PN}"
