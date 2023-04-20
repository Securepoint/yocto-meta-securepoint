SUMMARY = "Xelerance version of the Layer 2 Tunneling Protocol (L2TP) daemon"
HOMEPAGE = "http://www.xelerance.com/software/xl2tpd/"
SECTION = "net"
DEPENDS = "ppp virtual/kernel"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

#SRC_URI = "git://github.com/xelerance/xl2tpd.git;branch=master;protocol=https"
#SRCREV = "1ef2a025981223c1e16fc833bef226c86ff8c295"

SRC_URI = "https://github.com/xelerance/${PN}/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[sha256sum] = "47f52b9c56cabe77766533bca3736a8198581ff38443eb4b4c8971c885ac9329"

UPSTREAM_CHECK_URI = "https://github.com/xelerance/xl2tpd/releases"

S = "${WORKDIR}/${PN}-${PV}"

inherit update-rc.d

# XXX better way to make sure that these CFLAGS are set? if not given
# the Makefile does not fallback to its defaults, garbling the build
do_compile () {
    oe_runmake CFLAGS="${CFLAGS} -DSANITY -DDEBUG_PPPD -DTRUST_PPPD_TO_DIE -DLINUX -DUSE_KERNEL -DIP_ALLOCATION" LDFLAGS="${LDFLAGS}" PREFIX="${prefix}" KERNELSRC=${STAGING_KERNEL_DIR} all
}

do_install () {
    oe_runmake PREFIX="${D}${prefix}" install

    install -d ${D}${sysconfdir}/init.d
    touch ${D}${sysconfdir}/xl2tpd.conf
    install -m 0755 debian/xl2tpd.init ${D}${sysconfdir}/init.d/xl2tpd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/xl2tpd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/xl2tpd
    sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/xl2tpd
    sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${sysconfdir}/init.d/xl2tpd

    install -d ${D}${sysconfdir}/default
    install -m 0644 debian/xl2tpd.default ${D}${sysconfdir}/default/xl2tpd
}

CONFFILES:${PN} += "${sysconfdir}/xl2tpd.conf ${sysconfdir}/default/xl2tpd"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "xl2tpd"
