SUMMARY = "iptables extension netflow Linux kernel module"
LICENSE = "GPLv2"
PR = "r0"
SRC_URI = "gitsm://github.com/aabc/ipt-netflow.git;protocol=https;branch=master \
	   file://crosscompile.patch"
SRCREV = "40fefb20d0d2d11a39a5cc69d08db25c95b20a16"

inherit module
DEPENDS += "iptables"
S = "${WORKDIR}/git"

do_compile() {
	./configure --kdir="${STAGING_KERNEL_BUILDDIR}"
	make ipt_NETFLOW.ko
}

do_install() {
	install -d 644 ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/netfilter
	install -m 644 ${S}/ipt_NETFLOW.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/netfilter
	/sbin/depmod -b ${D} -a -n ${KERNEL_CC} > /dev/null || true
}
