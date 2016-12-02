HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://hostapd/README;md5=4d709ce0f9c84b87d148e16731f647e1"
DEPENDS = "libnl openssl"
DESCRIPTION = "User space daemon for extended IEEE 802.11 management"

inherit runit

PR = "r0"

S = "${WORKDIR}/hostapd-${PV}"

DEFAULT_PREFERENCE = "1"
RUNIT_SERVICES = "hostapd"

SRC_URI = " \
    http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
    file://etc_sv_hostapd_run \
    file://etc_sv_hostapd_down \
    file://hostapd.conf \
    file://0001-hostapd-Add-support-for-PAM-session-management.patch \
    file://0003-hostapd-add-LOG_PID-to-openlog-flags.patch \
"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/hostapd/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -g -I${STAGING_INCDIR}/libnl3"
    make -C hostapd
}

do_install() {
    install -d ${D}${sbindir} install -d ${D}/etc
    install -m 0644 ${S}/hostapd/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${S}/hostapd/hostapd ${D}${sbindir}
    install -m 0755 ${S}/hostapd/hostapd_cli ${D}${sbindir}
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"

SRC_URI[md5sum] = "236247a7bbd4f60d5fa3e99849d1ffc9"
SRC_URI[sha256sum] = "002e9dcb7e46cf82b5900a2fcf92b30fc8cdfd32a72d7fd4488588f1c013dfcc"
