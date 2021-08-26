SRC_URI[md5sum] = "b9c27bccb28432807955b8ff116ab029"
SRC_URI[sha256sum] = "8feccf37b10cde1d68caa883a153d7584ae03312ad93101fe873c51e43e8b517"

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
