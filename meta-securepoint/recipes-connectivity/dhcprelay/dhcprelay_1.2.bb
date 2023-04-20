# vim:set syntax=sh:
SUMMARY = "A light-weight DHCP Relay Agent"
HOMEPAGE = "http://www.mavetju.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.${PN}-${PV};md5=650b869bd8ff2aed59c62bad2a22a821"

PR = "r0"
SRC_URI = "\
    http://www.mavetju.org/download/${PN}-${PV}.tar.gz \
    http://www.mavetju.org/download/LICENSE;name=license;subdir=${PN}-${PV};downloadfilename=LICENSE.${PN}-${PV} \
    file://dhcprelay-1.2-foreground.patch;pnum=1 \
    file://dhcprelay-1.2-logname.patch;pnum=1 \
    file://dhcprelay-1.2-make.patch;pnum=1 \
    file://dhcprelay-1.2-broadcast_reply.patch;pnum=1 \
    file://dhcprelay-noblock.patch \
    file://etc_sv_dhcprelay4_down \
    file://etc_sv_dhcprelay4_run \
    file://etc_sv_dhcprelay4_log_run \
"

SRC_URI[md5sum] = "876b29f47e733aa8ba393600a05ddac0"
SRC_URI[sha256sum] = "031485e4e426de163dd377ee65ca0665441a821b72a635f8a6a52bdb88886d24"
SRC_URI[license.md5sum] = "d5a32e86849111c17d2065b9444d9a9c"
SRC_URI[license.sha256sum] = "beeae9e9f80e8b90fb66c99c095af4e89d7d9e7e13477e5143a0ac7fcb0bdd8c"

DEPENDS += " libpcap libdnet"
EXTRA_OEMAKE += " prefix=${prefix}"

inherit autotools-brokensep runit

RUNIT_SERVICES = "dhcprelay4"
