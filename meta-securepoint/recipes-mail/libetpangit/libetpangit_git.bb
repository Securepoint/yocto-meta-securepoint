DESCRIPTION = "libetpan is a library for communicating with mail and news servers. \
It supports the protocols SMTP, POP3, IMAP and NNTP."
HOMEPAGE = "http://www.etpan.org"
SECTION = "libs"
DEPENDS = "openssl liblockfile curl db cyrus-sasl"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=f18ebe7e452708c26f83954f81062ba7"

PR = "r1"

SRCREV = "1.7"
SRC_URI = "git://github.com/dinhviethoa/libetpan.git;protocol=http "
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig gettext binconfig

EXTRA_OECONF = "--with-openssl --without-gnutls"

PARALLEL_MAKE = ""

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev = "${bindir} ${includedir} ${libdir}/lib*.so ${libdir}/*.la ${libdir}/*.a ${libdir}/pkgconfig"

RCONFLICTS_${PN} = "libetpan"

#make autotools happy
do_configure_prepend(){
    cd ${S}
    touch NEWS README AUTHORS ChangeLog
}
