DESCRIPTION = "The LibXDiff library implements basic and yet complete \
functionalities to create file differences/patches to both binary and \
text files."
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"
PR = "r0"

inherit autotools-brokensep

SRC_URI = "http://www.xmailserver.org/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "8970281543130411d8a1b1f004a8418b"
SRC_URI[sha256sum] = "e9af96174e83c02b13d452a4827bdf47cb579eafd580953a8cd2c98900309124"

DISABLE_STATIC = ""
