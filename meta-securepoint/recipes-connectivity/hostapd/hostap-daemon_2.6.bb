HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://hostapd/README;md5=8aa4e8c78b59b12016c4cb2d0a8db350"
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

SRC_URI[md5sum] = "eaa56dce9bd8f1d195eb62596eab34c7"
SRC_URI[sha256sum] = "01526b90c1d23bec4b0f052039cc4456c2fd19347b4d830d1d58a0a6aea7117d"

INSANE_SKIP_${PN} += " compile-host-path "
