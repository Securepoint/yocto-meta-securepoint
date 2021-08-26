# Copyright (C) 2017 Andreas Weigel <andreas.weigel@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

# XXX this is just a really dirty temporary fix for the missing symlinks from openssl package
# midterm, figure out, why the links are not created there!
LICENSE = "CLOSED"
PR = "r0"

RDEPENDS_${PN} += "fwserver"

S = "${WORKDIR}"

SRC_URI += "\
"

inherit allarch

do_configure() {
  :
}

do_compile() {
  :
}

do_install() {
	install -d ${D}/${libdir}/ssl-1.1/
	ln -sf ${@oe.path.relative('${libdir}/ssl-1.1', '${sysconfdir}/ssl/certs')} ${D}${libdir}/ssl-1.1/certs
	ln -sf ${@oe.path.relative('${libdir}/ssl-1.1', '${sysconfdir}/ssl/private')} ${D}${libdir}/ssl-1.1/private
	ln -sf ${@oe.path.relative('${libdir}/ssl-1.1', '${sysconfdir}/ssl/openssl.cnf')} ${D}${libdir}/ssl-1.1/openssl.cnf
}

FILES_${PN} += "${libdir}/ssl-1.1/*"
