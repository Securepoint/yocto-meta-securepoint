SRC_URI[md5sum] = "233cd3492a145b39043b7842934248d9"
SRC_URI[sha256sum] = "966b879b985fd5c5387f091ac9b5c293ba905e87c7ebe39fa61191821414911b"
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
