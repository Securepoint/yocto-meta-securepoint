SUMMARY = "iptables extension netflow Linux kernel module"
LICENSE = "GPLv2"

PV = "1.0"
PR = "r0"

SRC_URI = "git://github.com/aabc/ipt-netflow.git;branch=master \
	   file://crosscompile.patch"
SRCREV = "5405927e302fb69e2bfd24e98a4dd834457ee5d0"

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
