SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e9b64491ec98eb6c6493ac5e4118f107"
DEPENDS = "lzo openssl libpam"

inherit autotools

SRC_URI = "\
        http://swupdate.openvpn.org/community/releases/openvpn-${PV}.zip \
        file://openvpn-2.3.0-challenge.patch \
        file://openvpn-2.3.0-framedip.patch \
	file://openvpn-param-size.patch \
        file://openvpn"

SRC_URI[md5sum] = "d86a69d9e887f139790c079e71edd43a"
SRC_URI[sha256sum] = "42ac5b9ffb6ac1b83e6ac37605a581a5b6d0f5e62bf4831b3050fbbb3a18138b"

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
