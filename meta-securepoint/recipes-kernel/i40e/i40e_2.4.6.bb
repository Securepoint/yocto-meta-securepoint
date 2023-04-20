SRC_URI[md5sum] = "830e0b47c405d2dc0d2bbe46c3451ad0"
SRC_URI[sha256sum] = "306ca211de1d86b4b557fdecae8c63fb9d4e848fbf271961278563c0851c8872"

SUMMARY = "Intel Ethernet kernel drivers"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d181af11d575d88127d52226700b0888"

RPROVIDES:${PN} += "kernel-module-i40e"

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

SRC_URI = "http://sourceforge.net/projects/e1000/files/i40e%20stable/${PV}/${PN}-${PV}.tar.gz \
           file://nodepmod.patch \
"

EXTRA_OEMAKE='BUILD_KERNEL="${LINUX_VERSION}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KSRC="${STAGING_KERNEL_DIR}" PREFIX="${D}" -C src'

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
# PKG_${PN} = "kernel-module-${PN}"

FILES:${PN}-doc = "/usr/man"
