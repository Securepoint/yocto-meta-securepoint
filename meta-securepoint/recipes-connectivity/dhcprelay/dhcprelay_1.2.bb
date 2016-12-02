SUMMARY = "A light-weight DHCP Relay Agent"
HOMEPAGE = "http://www.mavetju.org/"
LICENSE = "CLOSED"
# LICENSE = "BSD"
# LIC_FILES_CHKSUM = ""

PR = "r0"
SRC_URI = "\
    http://www.mavetju.org/download/dhcprelay-1.2.tar.gz \
    file://dhcprelay-1.2-foreground.patch;pnum=1 \
    file://dhcprelay-1.2-logname.patch;pnum=1 \
    file://dhcprelay-1.2-make.patch;pnum=1 \
"

SRC_URI[md5sum] = "876b29f47e733aa8ba393600a05ddac0"
SRC_URI[sha256sum] = "031485e4e426de163dd377ee65ca0665441a821b72a635f8a6a52bdb88886d24"

DEPENDS += " libpcap libdnet"
EXTRA_OEMAKE += " prefix=${prefix}"

inherit autotools-brokensep
