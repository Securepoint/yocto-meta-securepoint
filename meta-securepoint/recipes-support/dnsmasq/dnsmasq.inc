SUMMARY = "Lightweight, easy to configure DNS forwarder and DHCP server"
HOMEPAGE = "http://www.thekelleys.org.uk/dnsmasq/doc.html"
SECTION = "console/network"
# GPLv3 was added in version 2.41 as license option
LICENSE = "GPLv2 | GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://COPYING-v3;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://www.thekelleys.org.uk/dnsmasq/dnsmasq-${PV}.tar.xz \
           file://dnsmasq.conf \
"
DEPENDS = "libnetfilter-conntrack libidn"

EXTRA_OEMAKE = "COPTS='-DHAVE_CONNTRACK -DHAVE_IDN'"

do_install () {
    oe_runmake "PREFIX=${D}${prefix}" \
               "BINDIR=${D}${bindir}" \
               "MANDIR=${D}${mandir}" \
               install
    install -d ${D}${sysconfdir}/ ${D}${sysconfdir}/dnsmasq.d
    install -m 644 ${WORKDIR}/dnsmasq.conf ${D}${sysconfdir}/
}

CONFFILES:${PN} = "${sysconfdir}/dnsmasq.conf"
