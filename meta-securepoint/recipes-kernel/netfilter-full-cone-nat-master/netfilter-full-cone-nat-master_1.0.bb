SUMMARY = "iptables extension fullconenat Linux kernel module"
LICENSE = "GPLv2"

PV = "1.0"
PR = "r0"

SRC_URI = "git://github.com/Chion82/netfilter-full-cone-nat.git;branch=master"
SRCREV = "0cf3b48fd7d2fa81d0297d1fff12bbd0580fc435"

RPROVIDES_${PN} += "kernel-module-xt-fullconenat"

inherit module

S = "${WORKDIR}/git"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
  make -C ${STAGING_KERNEL_DIR} M=${S} modules_prepare || true
	make -C ${STAGING_KERNEL_DIR} M=${S} modules
}

do_install() {
	install -d 644 ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/netfilter
	install -m 644 ${S}/xt_FULLCONENAT.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/netfilter
	/sbin/depmod -b ${D} -a -n ${KERNEL_CC} > /dev/null || true
}

module_do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
	           CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
	           O=${STAGING_KERNEL_BUILDDIR} \
	           install
}
EXPORT_FUNCTIONS module_do_install

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
# PKG_${PN} = "kernel-module-${PN}"

FILES_${PN}-doc = "/usr/man"
