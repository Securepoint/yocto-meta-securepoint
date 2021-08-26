SRC_URI[md5sum] = "172bc41f403b4a5904eb44d911af54d3"
SRC_URI[sha256sum] = "08170d6719bb33fe8e08da2296729c77899e48c201115bdb81e4d492a1b21399"

SUMMARY = "IXGBE Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

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

SRC_URI = "http://sourceforge.net/projects/e1000/files/ixgbe%20stable/${PV}/${PN}-${PV}.tar.gz"

#S = "${WORKDIR}"

EXTRA_OEMAKE='BUILD_KERNEL="${LINUX_VERSION}" KOBJ="${STAGING_KERNEL_BUILDDIR}" KSRC="${STAGING_KERNEL_DIR}" PREFIX="${D}" -C src'

do_install_append () {
	rm -rf ${D}/lib/modules/*/modules.*
}

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
# PKG_${PN} = "kernel-module-${PN}"

FILES_${PN}-doc = "/usr/man"
