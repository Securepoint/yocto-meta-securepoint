SUMMARY = "ipset tool and libipset library for ipset-based netfilter usage"
DESCRIPTION = "IP sets are a framework inside the Linux kernel, which can be administered by the ipset utility. Depending on the type, an IP set may store IP addresses, networks, (TCP/UDP) port numbers, MAC addresses, interface names or combinations of them in a way, which ensures lightning speed when matching an entry against a set."
HOMEPAGE = "http://ipset.netfilter.org/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv2"


LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "http://ipset.netfilter.org/ipset-6.29.tar.bz2;name=tar"
SRC_URI[tar.md5sum] = "fd8ea35997115c5c630eee22f0beecec"
SRC_URI[tar.sha256sum] = "6af58b21c8b475b1058e02529ea9f15b4b727dbc13dc9cbddf89941b0103880e"

EXTRA_OECONF = "--with-kmod=no"

inherit autotools pkgconfig
