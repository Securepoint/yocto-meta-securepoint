SUMMARY = "HA monitor built upon LVS, VRRP and services poller"
DESCRIPTION = "\
The main goal of the keepalived project is to add a strong & robust keepalive \
facility to the Linux Virtual Server project. This project is written in C with \
multilayer TCP/IP stack checks. Keepalived implements a framework based on \
three family checks : Layer3, Layer4 & Layer5/7. This framework gives the \
daemon the ability to check the state of an LVS server pool. When one of the \
servers of the LVS server pool is down, keepalived informs the linux kernel via \
a setsockopt call to remove this server entry from the LVS topology. In \
addition keepalived implements an independent VRRPv2 stack to handle director \
failover. So in short keepalived is a userspace daemon for LVS cluster nodes \
healthchecks and LVS directors failover."

HOMEPAGE = "http://www.keepalived.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r0"
SRC_URI = "\
    http://www.keepalived.org/software/keepalived-${PV}.tar.gz \
    file://nosystemd.patch \
    file://nosnmp.patch \
"

SRC_URI[md5sum] = "e878312095b7dcab91ad06e257822247"
SRC_URI[sha256sum] = "3071804478077e606197a2348b5733d7d53af2843906af5e0d544945565c36ef"

inherit autotools-brokensep gettext

CPPFLAGS_append = " -I=/usr/include/libnl3"

# Pass STRIP=/bin/true to "make" in order to get a useful debuginfo package."
EXTRA_OEMAKE += " STRIP=/bin/true"
EXTRA_OECONF += " --disable-snmp --disable-snmp-keepalived --disable-snmp-checker --disable-snmp-rfc -disable-snmp-rfcv2 --disable-snmp-rfcv3 --disable-snmp-reply-v3-for-v2"

#DEPENDS += "libnl"

do_install_append() {
    install -d ${D}${datadir}/doc/${PN}-${PV}
    mv ${D}/etc/keepalived/samples ${D}${datadir}/doc/${PN}-${PV}
}
