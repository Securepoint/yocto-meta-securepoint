SRC_URI[md5sum] = "6cee50d03ee01c300ca701d2ac4c9c0b"
SRC_URI[sha256sum] = "0c3fc7ace88c2f76bb41696f5f57c6d724dbad2067db6c92625b6725930807d5"

SUMMARY = "IXGBE Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ef1e0d1bc1b04ad5062d834c1de70394"

inherit module

module_do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
	           CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
	           O=${STAGING_KERNEL_BUILDDIR} \
	           install
}
EXPORT_FUNCTIONS  module_do_install

PR = "r0"
SRC_URI = "\
	http://sourceforge.net/projects/e1000/files/igb%20stable/${PV}/${PN}-${PV}.tar.gz \
	file://no_depmod.patch \
"

EXTRA_OEMAKE='BUILD_KERNEL="${LINUX_VERSION}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KSRC="${STAGING_KERNEL_DIR}" PREFIX="${D}" -C src'

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
# PKG_${PN} = "kernel-module-${PN}"

FILES_${PN}-doc = "/usr/man"
