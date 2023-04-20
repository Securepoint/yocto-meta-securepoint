HOMEPAGE = "http://w1.fi/hostapd/"
SECTION = "kernel/userland"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://hostapd/README;md5=8aa4e8c78b59b12016c4cb2d0a8db350"
DEPENDS = "libnl openssl"
DESCRIPTION = "User space daemon for extended IEEE 802.11 management"
DEPENDS = "libnl libpam openssl"

inherit runit pkgconfig

PR = "r0"

S = "${WORKDIR}/hostapd-${PV}"

DEFAULT_PREFERENCE = "1"
RUNIT_SERVICES = "hostapd"

SRC_URI = " \
    http://w1.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
    file://0001-hostapd-Add-support-for-PAM-session-management.patch \
    file://0003-hostapd-add-LOG_PID-to-openlog-flags.patch \
    file://rtlxdrv.patch \
    file://etc_sv_hostapd_run \
    file://etc_sv_hostapd_down \
"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/hostapd/.config
}

do_compile() {
    cd hostapd && make V=1 SYSROOT=${STAGING_DIR_TARGET}
}

do_install() {
    install -d ${D}${sbindir} 
    install -m 0755 ${S}/hostapd/hostapd ${D}${sbindir}
    install -m 0755 ${S}/hostapd/hostapd_cli ${D}${sbindir}
}

SRC_URI[md5sum] = "ed2c254e5f400838cb9d8e7b6e43b86c"
SRC_URI[sha256sum] = "929f522be6eeec38c53147e7bc084df028f65f148a3f7e4fa6c4c3f955cee4b0"

INSANE_SKIP:${PN} += " compile-host-path "
