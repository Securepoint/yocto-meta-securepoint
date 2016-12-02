HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://hostapd/README;md5=0854a4da34ac3990770794d771fac7fd"
DEPENDS = "libnl openssl"
DESCRIPTION = "User space daemon for extended IEEE 802.11 management"
DEPENDS = "libnl"

inherit runit

PR = "r0"

S = "${WORKDIR}/hostapd-${PV}"

DEFAULT_PREFERENCE = "1"
RUNIT_SERVICES = "hostapd"

SRC_URI = " \
    http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
    file://0001-hostapd-Add-support-for-PAM-session-management.patch \
    file://0003-hostapd-add-LOG_PID-to-openlog-flags.patch \
    file://drv_cflags.patch \
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

SRC_URI[md5sum] = "40b89c61036add0c2dd1fc10767d3b5f"
SRC_URI[sha256sum] = "c94c2b76876fad4c80a1063a06f958a2189ba5003475016fa7658a1ca49bb4df"

INSANE_SKIP_${PN} += " compile-host-path "
