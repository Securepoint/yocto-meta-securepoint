DEPENDS_append = " libgcc elfutils-native"

SRC_URI += " \
    file://defconfig \
    file://modules.cfg \
    file://squashfs.cfg \
    file://mm.cfg \
    file://nics.cfg \
    file://crypto.cfg \
    file://vm.cfg \
    file://cputemp.cfg \
    file://cong_algos.cfg \
    file://taskstats.cfg \
    file://cifs.cfg \
    file://${MACHINE}.cfg \
    file://i915.cfg \
    file://tomoyo.cfg \
    file://usbserial.cfg \
    file://security.cfg \
    file://netfilter.cfg \
    file://cgroups.cfg \
    file://video.cfg \
    file://storevsc_drv_32bit_tablesize.patch \
    file://0002-e1000e-Intel-Quadport-0x10A0-hack.patch \
    file://hv_vss-excludeloop-option.patch \
    file://09-hyperv \
    file://fanotify.cfg \
    file://iscsi.cfg \
    file://mmc.cfg \
    file://hv_get_dns_info \
    file://hv_get_dhcp_info \
"

SRC_URI_append_x86-64 = "file://efi.cfg"

PACKAGES_prepend += " hyperv-daemons hyperv-daemons-dbg "
FILES_hyperv-daemons = "/usr/bin/hv_* /etc/runit /usr/libexec/hypervkvpd"
FILES_hyperv-daemons-dbg ="/usr/bin/.debug/hv_* /usr/src/debug/linux-yocto-sp/*/linux/tools/hv"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install_append() {
    install -d ${D}${bindir} ${D}/etc/runit/1.d ${D}/usr/libexec/hypervkvpd
    install -m 0755 ${WORKDIR}/09-hyperv ${D}/etc/runit/1.d
    install -m 0700 ${WORKDIR}/hv_get_dns_info ${D}/usr/libexec/hypervkvpd/
    install -m 0700 ${WORKDIR}/hv_get_dhcp_info ${D}/usr/libexec/hypervkvpd/
    cd ${S}
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_vss_daemon.c -o ${D}${bindir}/hv_vss_daemon
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_kvp_daemon.c -o ${D}${bindir}/hv_kvp_daemon
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_fcopy_daemon.c -o ${D}${bindir}/hv_fcopy_daemon
}
