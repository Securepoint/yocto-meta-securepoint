SUMMARY = "ipset tool and libipset library for ipset-based netfilter usage"
DESCRIPTION = "IP sets are a framework inside the Linux kernel, which can be administered by the ipset utility. Depending on the type, an IP set may store IP addresses, networks, (TCP/UDP) port numbers, MAC addresses, interface names or combinations of them in a way, which ensures lightning speed when matching an entry against a set."
HOMEPAGE = "http://ipset.netfilter.org/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv2"

DEPENDS = "libtool libmnl"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://ipset.netfilter.org/ipset-${PV}.tar.bz2;name=tar"
SRC_URI[tar.md5sum] = "e107b679c3256af795261cece864d6d9"
SRC_URI[tar.sha256sum] = "0e7d44caa9c153d96a9b5f12644fbe35a632537a5a7f653792b72e53d9d5c2db"

EXTRA_OECONF = "--with-kmod=no"

inherit autotools pkgconfig
