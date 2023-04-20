SUMMARY = "Intel Ethernet kernel drivers"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d181af11d575d88127d52226700b0888"
SRC_URI = "https://sourceforge.net/projects/e1000/files/i40e%20stable/${PV}/${PN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "8dfe1c2afe1f2a5610ccb3b24b40f84571c3c829c5c291510dc7477172c69130"
PR = "r0"
S = "${WORKDIR}/${BP}/src"

RPROVIDES:${PN} += "kernel-module-i40e"

inherit module

EXTRA_OEMAKE='KSRC="${STAGING_KERNEL_BUILDDIR}" KVER="${KERNEL_VERSION}" INSTALL_MOD_PATH="${D}"'
