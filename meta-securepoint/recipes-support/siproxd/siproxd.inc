# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "proxy/masquerading daemon for the SIP protocol"
DESCRIPTION = "\
Siproxd is a proxy/masquerading daemon for the SIP protocol. It handles\
registrations of SIP clients on a private IP network and performs\
rewriting of the SIP message bodies to make SIP connections work via an\
masquerading firewall (NAT). It allows SIP software clients (like kphone,\
linphone) or SIP hardware clients (Voice over IP phones which are SIP-\
compatible, such as those from Cisco, Grandstream or Snom) to work behind\
an IP masquerading firewall or NAT router.\
"
HOMEPAGE = "http://siproxd.sourceforge.net/"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
LICENSE = "GPLv2"
SECTION = "net"
DEPENDS = "libtool libosip2 sqlite3"
PR = "r0"

SRC_URI = "\
    https://downloads.sourceforge.net/${PN}/${PN}-${PV}.tar.gz \
    file://etc_sv_siproxd_run \
    file://etc_sv_siproxd_down \
"

EXTRA_OECONF = "--disable-doc"

inherit autotools runit useradd

USERADD_PACKAGES += "${PN}"
USERADD_PARAM:${PN} = "\
-r -d /var/run/siproxd -g siproxd -s /bin/false siproxd;\
"

GROUPADD_PARAM:${PN} = "\
-r -f siproxd;\
"

RUNIT_SERVICES = "siproxd"

REMOVE_LIBTOOL_LA = "0"

do_install:append() {
  rm -rf ${D}/${sysconfdir}/siproxd_passwd.cfg ${D}/${sysconfdir}/siproxd.conf.example
}
