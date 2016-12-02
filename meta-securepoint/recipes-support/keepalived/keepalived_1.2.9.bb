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
    http://www.keepalived.org/software/keepalived-1.2.9.tar.gz \
"

SRC_URI[md5sum] = "adfad98a2cc34230867d794ebc633492"
SRC_URI[sha256sum] = "fb711dacce95b60eee18f2b89938a9fbebc5096022f17850fd2284f207e41d9d"

inherit autotools-brokensep gettext

# Pass STRIP=/bin/true to "make" in order to get a useful debuginfo package."
EXTRA_OEMAKE += " STRIP=/bin/true"

do_install_append() {
    install -d ${D}${datadir}/doc/${PN}-${PV}
    mv ${D}/etc/keepalived/samples ${D}${datadir}/doc/${PN}-${PV}
}
