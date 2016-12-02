SUMMARY = "Simplified, portable interface to low-level networking routines"
DESCRIPTION = "\
libdnet provides a simplified, portable interface to several low-level \
networking routines, including network address manipulation, kernel    \
arp(4) cache and route(4) table lookup and manipulation, network       \
firewalling, network interface lookup and manipulation, IP tunnelling, \
and raw IP packet and Ethernet frame transmission."
HOMEPAGE = "https://code.google.com/p/libdnet/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0036c1b155f4e999f3e0a373490b5db9"

PR = "r0"
SRC_URI = "http://libdnet.googlecode.com/files/libdnet-1.12.tgz"

SRC_URI[md5sum] = "9253ef6de1b5e28e9c9a62b882e44cc9"
SRC_URI[sha256sum] = "83b33039787cf99990e977cef7f18a5d5e7aaffc4505548a83d31bd3515eb026"

inherit autotools

do_configure() {
    oe_runconf
}
