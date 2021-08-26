# vim:set syntax=config:
inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-5.10:${THISDIR}/files:"

SRC_URI = "\
	gitsm://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-5.10.y;bareclone=1;protocol=http \
	file://i915.cfg \
"

LINUX_VERSION = "${PV}"
LINUX_VERSION_EXTENSION ?= "-sp"
LINUX_KERNEL_TYPE = "custom"

PV = "5.10.41"
SRCREV = "v${PV}"
PR = "r1"

# required to find libelf for 64Bit CONFIG_UNWINDER_ORC
EXTRA_OEMAKE = " HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" HOSTCPP="${BUILD_CPP}" INSTALL_MOD_PATH="${D}""

#exclude the kernel image from rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_${MACHINE} = "${MACHINE}"

include linux-yocto-sp.inc

do_compile_append() {
	touch ${B}/Module.symvers
}

do_install_append() {
	mkdir -p ${STAGING_KERNEL_BUILDDIR}/scripts/
	cp ${B}/scripts/module.lds ${STAGING_KERNEL_BUILDDIR}/scripts/
}
