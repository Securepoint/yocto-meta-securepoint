SUMMARY = "igb Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=ef1e0d1bc1b04ad5062d834c1de70394"
HOMEPAGE = "https://sourceforge.net/projects/e1000/"

SRC_URI = "\
	https://sourceforge.net/projects/e1000/files/igb%20stable/${PV}/${PN}-${PV}.tar.gz \
	file://igb-install.patch \
"
SRC_URI[sha256sum] = "3793d43cb099f165d76f6f120eeb30c1f542800bbda336ed1a21c9f46ba3ce1d"

inherit module

PR = "r0"
S = "${WORKDIR}/${BP}/src"
EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'
FILES:${PN}-doc = "/usr/man"
