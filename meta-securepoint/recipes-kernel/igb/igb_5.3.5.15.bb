SRC_URI[md5sum] = "573c98073eb4f70d915a5914889b634f"
SRC_URI[sha256sum] = "e14dff562d9c56dbea38f9d8c6dcfd9275fe10c3f2c71a09fb2bb480f47867fc"

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
