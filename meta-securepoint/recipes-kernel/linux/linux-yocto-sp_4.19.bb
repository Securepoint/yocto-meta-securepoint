# vim:set syntax=config:
inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-4.19:${THISDIR}/files:"

SRC_URI = "\
	gitsm://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.19.y;bareclone=1;protocol=http \
"

LINUX_VERSION = "${PV}"
LINUX_VERSION_EXTENSION ?= "-sp"
LINUX_KERNEL_TYPE = "custom"

PV = "4.19.127"
SRCREV = "v${PV}"
PR = "r1"

# required to find libelf for 64Bit CONFIG_UNWINDER_ORC
EXTRA_OEMAKE = " HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" HOSTCPP="${BUILD_CPP}""

#exclude the kernel image from rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_${MACHINE} = "${MACHINE}"

include linux-yocto-sp.inc
