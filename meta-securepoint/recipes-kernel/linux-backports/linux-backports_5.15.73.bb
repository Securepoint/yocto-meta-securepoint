# vim:syntax=sh:
DESCRIPTION = "Linux Backports"
HOMEPAGE = "https://backports.wiki.kernel.org"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += " coreutils-native flex-native bison-native"

PR = "1"
REALPV = "5.15.58"

SRC_URI[md5sum] = "8612769869845ebf7a0da8807539ffc2"
SRC_URI[sha256sum] = "4c6b2af699e5e557dfc44bc7e30a10f1d6299a451ea50443084bdf7c850cbb24"
SRC_URI = "\
        https://mirrors.edge.kernel.org/pub/linux/kernel/projects/backports/stable/v${REALPV}/backports-${REALPV}-1.tar.gz \
	file://drivers-v5.15.58-v5.15.73.diff \
        file://backports_config \
	file://kconfig-defaults.patch \
        file://make.patch \
	file://linux-ath_regd_optional.patch \
	file://ath10k_improve_tx_rate.patch \
	file://linux-4.9.298-and-greater.patch \
	file://brcm80211-build.patch \
	file://iwlwifi-build.patch \
"

inherit module

do_configure () {
	set -e
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
  	make -C ${STAGING_KERNEL_DIR} M=${S} modules_prepare || true
        cp ${WORKDIR}/backports_config ${S}/.config
	oe_runmake -w oldconfig CC='${BUILD_CC}' V=1
	# sed -e 's/\(.*REQUIRE_SIGNED_REGDB\).*/# \1 is not set/g' ${S}/.config
}

S = "${WORKDIR}/backports-${REALPV}-1"

EXTRA_OEMAKE += "V=1 LEX=flex YACC=bison KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} KLIB=${STAGING_KERNEL_DIR}"

PACKAGES_DYNAMIC = " ^kernel-module-.*"
EXCLUDE_FROM_WORLD = "1"
