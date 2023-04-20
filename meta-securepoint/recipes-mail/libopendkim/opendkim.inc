DESCRIPTION = "The Trusted Domain Project is a community effort to develop and maintain an open source library for producing DKIM-aware applications, and a milter-based filter for providing DKIM service."
SECTION = "mail"
LICENSE = "BSD"

inherit autotools pkgconfig

EXTRA_OECONF = "\
	--disable-filter\
	--disable-live-testing\
"

EXTRA_OEMAKE = "\
"

DEPENDS += "openssl libbsd libtool"
PACKAGES:prepend = " ${PN}-bin "

do_install:append() {
  # remove docs/manpages
  rm -rf ${D}${datadir}
}

FILES:${PN}-bin = "/usr/bin /usr/sbin"
