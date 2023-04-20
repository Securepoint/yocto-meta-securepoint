DESCRIPTION = "Open source implementation of the DMARC proposal, and co-ordination of and participation in an interoperability event."
SECTION = "mail"
LICENSE = "BSD"

DL_TAG = "${@d.getVar('PV',d,1).replace('.', '-')}"

SRC_URI = "https://github.com/trusteddomainproject/${PN}/archive/rel-${PN}-${DL_TAG}.tar.gz \
"

SRC_URI[md5sum] = "18fe4c7bedbc6f893e96b0b47dfcc280"
SRC_URI[sha256sum] = "5299adbf620c5b02961df6321f82799202842f3d47840d237e484c101407f85a"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea9056dd2f14906bec57fe79c021a52e"

PR = "r0"
inherit autotools pkgconfig

S = "${WORKDIR}/OpenDMARC-rel-${PN}-${DL_TAG}"

EXTRA_OECONF = "\
	--disable-filter \
"

EXTRA_OEMAKE = "\
"

DEPENDS += "libtool"
PACKAGES:prepend = " ${PN}-bin "

do_install:append() {
  # remove docs/manpages
  rm -rf ${D}${datadir}
}

FILES:${PN}-bin = "/usr/bin /usr/sbin"
