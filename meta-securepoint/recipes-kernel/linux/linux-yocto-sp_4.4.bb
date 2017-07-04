inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-${PV}.y;bareclone=1"
SRC_URI += " \
    file://defconfig \
    file://squashfs.cfg \
    file://mm.cfg \
    file://nics.cfg \
    file://crypto.cfg \
    file://vm.cfg \
    file://cputemp.cfg \
    file://cong_algos.cfg \
    file://taskstats.cfg \
    file://cifs.cfg \
    file://spx86.cfg \
    file://tomoyo.cfg \
    file://usbserial.cfg \
    file://linux-${PV}-ath_regd_optional.patch \
    file://storevsc_drv_32bit_tablesize.patch \
    file://0002-e1000e-Intel-Quadport-0x10A0-hack.patch \
    file://09-hyperv \
    file://fanotify.cfg \
"

LINUX_VERSION = "${PV}"
LINUX_VERSION_EXTENSION ?= "-sp"
LINUX_KERNEL_TYPE = "custom"

PV = "4.4.52"
SRCREV = "v${PV}"
PR = "r1"

#exclude the kernel image from rootfs
RDEPENDS_kernel-base = ""

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_${MACHINE} = "${MACHINE}"

PACKAGES_prepend += " hyperv-daemons hyperv-daemons-dbg "
FILES_hyperv-daemons = "/usr/bin/hv_* /etc/runit"
FILES_hyperv-daemons-dbg ="/usr/bin/.debug/hv_* /usr/src/debug/linux-yocto-sp/*/linux/tools/hv"

do_install_append() {
    install -d ${D}${bindir} ${D}/etc/runit/1.d
    install -m 0755 ${WORKDIR}/09-hyperv ${D}/etc/runit/1.d
    cd ${S}
    mkdir -p tools/hv/linux
    cp include/uapi/linux/hyperv.h tools/hv/linux
    ${CC} ${CFLAGS} -Itools/hv tools/hv/hv_vss_daemon.c -o ${D}${bindir}/hv_vss_daemon
    ${CC} ${CFLAGS} -Itools/hv tools/hv/hv_kvp_daemon.c -o ${D}${bindir}/hv_kvp_daemon
    ${CC} ${CFLAGS} -Itools/hv tools/hv/hv_fcopy_daemon.c -o ${D}${bindir}/hv_fcopy_daemon
}

