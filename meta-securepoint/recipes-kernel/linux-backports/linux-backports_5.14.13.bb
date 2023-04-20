# vim:syntax=sh:
DESCRIPTION = "Linux Backports"
HOMEPAGE = "https://backports.wiki.kernel.org"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += " coreutils-native flex-native bison-native"

PR = "1"

SRC_URI[md5sum] = "cb2f737ba598e8eb998cc93bf6411aca"
SRC_URI[sha256sum] = "74b3233e15c51dd81138de9a6acdc5d08ea0129d3599e1ea275046eddf3310ef"
SRC_URI = "\
        https://mirrors.edge.kernel.org/pub/linux/kernel/projects/backports/stable/v${PV}/backports-${PV}-1.tar.gz \
        file://backports_config \
	file://kconfig-defaults.patch \
        file://make.patch \
	file://linux-ath_regd_optional.patch \
	file://ath10k_improve_tx_rate.patch \
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

S = "${WORKDIR}/backports-${PV}-1"

EXTRA_OEMAKE += "V=1 LEX=flex YACC=bison KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} KLIB=${STAGING_KERNEL_DIR}"

PACKAGES_DYNAMIC = " ^kernel-module-.*"
EXCLUDE_FROM_WORLD = "1"
