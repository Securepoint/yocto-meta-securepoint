SRC_URI[md5sum] = "27ab74640f3fbab64b88f2c8d5dccaa9"
SRC_URI[sha256sum] = "5c5837ed99fb1fab7648f6bc7cb38a83c5edf3b20cc145e7e726d8860b368ab8"
SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d181af11d575d88127d52226700b0888"

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

#S = "${WORKDIR}"

EXTRA_OEMAKE='BUILD_KERNEL="${LINUX_VERSION}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KSRC="${STAGING_KERNEL_DIR}" PREFIX="${D}" -C src'

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
# PKG_${PN} = "kernel-module-${PN}"

FILES:${PN}-doc = "/usr/man"
