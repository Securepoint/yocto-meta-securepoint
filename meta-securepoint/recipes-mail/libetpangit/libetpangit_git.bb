DESCRIPTION = "libetpan is a library for communicating with mail and news servers. \
It supports the protocols SMTP, POP3, IMAP and NNTP."
HOMEPAGE = "http://www.etpan.org"
SECTION = "libs"
DEPENDS = "openssl liblockfile curl cyrus-sasl"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=f18ebe7e452708c26f83954f81062ba7"

PR = "r1"

# those are the original commit/repo that have been used by
# sp_fetchmail/UMA/UTM for a long time; keep that the default for now
# original comment: use this commit. next commit segfaults on SSL connections
# see https://github.com/dinhviethoa/libetpan/commit/ee87746075c597385bc552ce4f0365476a919d32
LIBETPAN_SRCREV ?= "7edab149a61e5c246c52a5c71954c50d5ed94eb5"
LIBETPAN_REPO   ?= "Securepoint/libetpan.git"

SRC_URI = "git://github.com/${LIBETPAN_REPO};protocol=https;branch=master "

SRCREV = "${LIBETPAN_SRCREV}"
PV = "git${SRCPV}"


S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig gettext binconfig

PACKAGECONFIG ??= "lmdb"
PACKAGECONFIG[db] = "--enable-db,--disable-db,db"
PACKAGECONFIG[lmdb] = "--enable-lmdb,--disable-lmdb,lmdb"

EXTRA_OECONF += " --with-openssl --without-gnutls"

PARALLEL_MAKE = ""

FILES:${PN} = "${libdir}/lib*.so.*"
FILES:${PN}-dev = "${bindir} ${includedir} ${libdir}/lib*.so ${libdir}/*.la ${libdir}/*.a ${libdir}/pkgconfig"

RCONFLICTS_${PN} = "libetpan"

#make autotools happy
do_configure:prepend(){
    cd ${S}
    touch NEWS README AUTHORS ChangeLog
}
do_configure:append(){
    # Kill me if you can! :-)
    sed -i -e "s/-lstdc++//" ${S}/*libtool*
}
