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
DEPENDS = "libosip2"
PR = "r0"

SRC_URI = "\
    http://downloads.sourceforge.net/${PN}/${PN}-${PV}.tar.gz \
    file://siproxd-includes.patch \
    file://etc_sv_siproxd_log_run \
    file://etc_sv_siproxd_run \ 
    file://etc_sv_siproxd_down \
"
SRC_URI[md5sum] = "1a6f9d13aeb2d650375c9a346ac6cbaf"
SRC_URI[sha256sum] = "df2df04faf5bdb4980cbdfd5516a47898fc47ca1ebc2c628aa48305b20a09dad"

EXTRA_OECONF = "--disable-doc"

inherit autotools runit

RUNIT_SERVICES = "siproxd"
