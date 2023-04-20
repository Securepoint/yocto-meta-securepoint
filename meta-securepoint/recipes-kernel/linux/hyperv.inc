PACKAGES:prepend = " hyperv-daemons hyperv-daemons-dbg "
DEPENDS:append = " libgcc"
FILES:hyperv-daemons = "/usr/bin/hv_* /etc/runit /etc/udev/rules.d /usr/libexec/hypervkvpd"
FILES:hyperv-daemons-dbg ="/usr/bin/.debug/hv_* /usr/src/debug/linux-yocto-sp/*/linux/tools/hv"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install:append () {
    install -d ${D}${bindir} ${D}/etc/runit/1.d ${D}/etc/udev/rules.d ${D}/usr/libexec/hypervkvpd
    install -m 0755 ${WORKDIR}/09-hyperv ${D}/etc/runit/1.d
    install -m 0700 ${WORKDIR}/hv_get_dns_info ${D}/usr/libexec/hypervkvpd/
    install -m 0700 ${WORKDIR}/hv_get_dhcp_info ${D}/usr/libexec/hypervkvpd/
    install -m 0644 ${WORKDIR}/100-balloon.rules ${D}/etc/udev/rules.d
    cd ${S}
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_vss_daemon.c -o ${D}${bindir}/hv_vss_daemon
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_kvp_daemon.c -o ${D}${bindir}/hv_kvp_daemon
    ${CC} -O2 -D__EXPORTED_HEADERS__ -Iinclude/uapi -Iinclude tools/hv/hv_fcopy_daemon.c -o ${D}${bindir}/hv_fcopy_daemon
}
