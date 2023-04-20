SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e9b64491ec98eb6c6493ac5e4118f107"
DEPENDS = "lzo openssl libpam iproute2"

inherit autotools

SRC_URI = "\
        http://swupdate.openvpn.org/community/releases/openvpn-${PV}.zip \
        file://openvpn-2.4-framed_ip_and_challenge.patch \
        file://openvpn-param-size.patch"

CFLAGS += "-fno-inline"

EXTRA_OECONF += "--enable-iproute2"
PACKAGES:prepend = " ${PN}-sysvinit ${PN}-plugins-auth-pam ${PN}-plugins-down-root "

RRECOMMENDS:${PN} = "kernel-module-tun"

FILES:${PN}-plugins-auth-pam = "/usr/lib/openvpn/plugins/openvpn-plugin-auth-pam.so"
FILES:${PN}-plugins-down-root = "/usr/lib/openvpn/plugins/openvpn-plugin-down-root.so"
FILES:${PN}-sysvinit  = "${sysconfdir}/init.d"
FILES:${PN}-dev += "/usr/lib/openvpn/plugins/*.la"
FILES:${PN}-dbg += "/usr/lib/openvpn/plugins/.debug"

RDEPENDS:${PN}-plugins-auth-pam = "${PN}"
RDEPENDS:${PN}-plugins-down-root = "${PN}"
RDEPENDS:${PN}-sysvinit = "${PN}"
