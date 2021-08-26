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
PACKAGES_prepend += " ${PN}-bin "

do_configure_append() {
  # XXX maybe they will fix Makefile.am eventually?
  ln -s ${B}/${HOST_SYS}-libtool ${B}/libtool
}

do_compile_prepend(){
}

do_install_append() {
  # remove docs/manpages
  rm -rf ${D}${datadir}
}

FILES_${PN}-bin = "/usr/bin /usr/sbin"
