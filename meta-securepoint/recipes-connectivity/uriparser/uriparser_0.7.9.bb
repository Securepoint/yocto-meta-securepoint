DESCRIPTION = "uriparser library"
SECTION = "net"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=72b0f9c74ae96eeab8cf1bf3efe08da2"

#SRC_URI = "${SOURCEFORGE_MIRROR}/${PN}/${PN}-${PV}.tar.bz2"
#SRC_URI[md5sum] = "1ff590e25e8d9f5eaaa409c741fc387b"
#SRC_URI[sha256sum] = "2017629da6fc736eeca370b4987fdc57eead6425a2f85f72bcd094f12b21af34"
SRC_URI = "git://git.code.sf.net/p/uriparser/git;protocol=http"
SRCREV = "uriparser-${PV}"
S = "${WORKDIR}/git"

DEPENDS = ""

inherit autotools

EXTRA_OECONF = "--disable-test --disable-doc"
EXTRA_OEMAKE += " PREFIX=/usr "
