DESCRIPTION = "ulogd is a logging daemon that reads event messages coming from the Netfilter connection tracking and the Netfilter packet logging subsystem."
HOMEPAGE = "http://www.netfilter.org/projects/ulogd/"
SUMMARY = "Userspace logging daemon for netfilter"
LICENSE = "GPLv2"
SECTION = "base"

SRC_URI = "\
	https://netfilter.org/projects/ulogd/files/${PN}-${PV}.tar.bz2 \
	file://etc_sv_ulog_run \
"
SRC_URI[md5sum] = "2bb2868cf51acbb90c35763c9f995f31"
SRC_URI[sha256sum] = "990a05494d9c16029ba0a83f3b7294fc05c756546b8d60d1c1572dc25249a92b"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

DEPENDS = "libnfnetlink libnetfilter-log libnetfilter-conntrack libnetfilter-acct"

FILES:${PN} += "${libdir}/*"

inherit autotools pkgconfig runit

RUNIT_SERVICES = "ulog"
