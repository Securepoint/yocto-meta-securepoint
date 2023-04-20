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

SRC_URI[sha256sum] = "206e7c799b678572c2e3d12030238784bc4a9f82323b0156b4c9466f1498915d"

INSANE_SKIP:${PN} += " compile-host-path "
