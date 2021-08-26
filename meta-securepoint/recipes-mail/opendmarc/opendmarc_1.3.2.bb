DESCRIPTION = "Open source implementation of the DMARC proposal, and co-ordination of and participation in an interoperability event."
SECTION = "mail"
LICENSE = "BSD"

DL_TAG = "${@d.getVar('PV',d,1).replace('.', '-')}"

SRC_URI = "https://github.com/trusteddomainproject/${PN}/archive/rel-${PN}-${DL_TAG}.tar.gz \
           file://01_fetch_from_domain.patch \
           file://02_fix_store_dkim.patch \
"

SRC_URI[md5sum] = "f35738631b2268e626a202cb1c2a4521"
SRC_URI[sha256sum] = "a9e73f36a9b05ca58ebe2a7b1fc314af8fd292882be371abc513abfe21b90eb3"
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
PACKAGES_prepend += " ${PN}-bin "

do_configure_append() {
  # XXX maybe they will fix Makefile.am eventually?
  ln -sf ${B}/${HOST_SYS}-libtool ${B}/libtool
}

do_compile_prepend(){
}

do_install_append() {
  # remove docs/manpages
  rm -rf ${D}${datadir}
}

FILES_${PN}-bin = "/usr/bin /usr/sbin"
