SUMMARY = "Fetchmail retrieves mail from remote mail servers and forwards it via SMTP"
HOMEPAGE = "http://www.fetchmail.info/"
DESCRIPTION = "Fetchmail is a full-featured, robust, well-documented remote-mail retrieval \
and forwarding utility intended to be used over on-demand TCP/IP links (such as SLIP or PPP \
connections). It supports every remote-mail protocol now in use on the Internet: POP2, POP3, \
RPOP, APOP, KPOP, all flavors of IMAP, ETRN, and ODMR. It can even support IPv6 and IPSEC."
SECTION = "mail"
LICENSE = "GPLv2.0 & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=c3a05d9b9d3784c824c9b92a648e1353"

DEPENDS = "openssl flex-native bison-native"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.xz \
           "
SRC_URI[sha256sum] = "82954ebd26c77906463ce20adca45cbcf8068957441e17941bd3052a5c15432e"

inherit autotools gettext pkgconfig python3-dir python3native

# for some unfathomable reason, within configure.ac, --with_ssl is set to
# "/usr/local/ssl" if specified to "yes", causing a hard failure
# BEFORE an actually working pkg-config mechanism even tried; use some random
# string not containing any slashes here (which cause the hard fail)
# XXX this should be fixed upstream, but configure.ac is a mess and it does
# not even try to support cross compilation
EXTRA_OECONF = "--with-ssl=pkgcfg"

PACKAGES =+ "${PN}-python"
FILES:${PN}-python = "${libdir}/${PYTHON_DIR}/*"
