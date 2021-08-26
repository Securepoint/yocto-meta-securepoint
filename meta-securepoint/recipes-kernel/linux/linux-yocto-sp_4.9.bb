inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-4.9:${THISDIR}/files:"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.9.y;bareclone=1"
SRC_URI += " file://hv_kvp_daemon.patch "

LINUX_VERSION = "${PV}"
LINUX_VERSION_EXTENSION ?= "-sp"
LINUX_KERNEL_TYPE = "custom"

PV = "4.9.270"
SRCREV = "v${PV}"
PR = "r1"

#exclude the kernel image from rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_${MACHINE} = "${MACHINE}"

include linux-yocto-sp.inc
