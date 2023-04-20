SUMMARY = "igb Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=ef1e0d1bc1b04ad5062d834c1de70394"
HOMEPAGE = "https://sourceforge.net/projects/e1000/"

SRC_URI = "\
	http://sourceforge.net/projects/e1000/files/igb%20stable/${PV}/${PN}-${PV}.tar.gz \
	file://igb-install.patch \
"
SRC_URI[sha256sum] = "12ea3ff7b3813ed3e5e126e25926418ad3532785fc4d9fa9611e3fe3617cdb15"

inherit module

PR = "r0"
S = "${WORKDIR}/${BP}/src"
EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'
FILES:${PN}-doc = "/usr/man"
