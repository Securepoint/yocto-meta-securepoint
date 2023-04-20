# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Sieve and ManageSieve support for Dovecot v2.0"
HOMEPAGE = "http://pigeonhole.dovecot.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"
SECTION = "mail"
DEPENDS = "dovecot"
PR = "r0"

# file://dovecot-2.3-pigeonhole-${PV}.tar.gz
SRC_URI = "\
    https://pigeonhole.dovecot.org/releases/2.3/dovecot-2.3-pigeonhole-${PV}.tar.gz \
"

SRC_URI[md5sum] = "d1ef692e41dbe51f42fa73cad7fea629"
SRC_URI[sha256sum] = "0b972a441f680545ddfacd2f41fb2a705fb03249d46ed5ce7e01fe68b6cfb5f0"


#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
S = "${WORKDIR}/dovecot-2.3-pigeonhole-${PV}"

inherit autotools-brokensep

EXTRA_OECONF = "--with-dovecot=${S} --disable-static --without-managesieve"

do_configure:prepend(){
    #copy over file from dovecot and modify paths in place
    cp ${STAGING_DIR_HOST}/${libdir}/dovecot/dovecot-config ${S}
    sed -i "s:LIBDOVECOT='-L.*':LIBDOVECOT='-L${STAGING_DIR_HOST}${libdir}/dovecot -ldovecot':g" dovecot-config
    sed -i "s:LIBDOVECOT_INCLUDE=-I.*:LIBDOVECOT_INCLUDE=-I${STAGING_DIR_HOST}${includedir}/dovecot:g" dovecot-config

}

# plugins dont have versioning for .so
INSANE_SKIP:${PN} += " dev-so "
FILES:${PN} = " ${bindir} \
                ${libdir}/dovecot/lib* \
                ${libdir}/dovecot/*/lib* \
                ${prefix}/libexec/dovecot/*"
FILES:${PN}-dev = "${includedir} \
                /usr/src \
                ${libdir}/dovecot/*.la \
                ${libdir}/dovecot/*/*.la \
                /usr/share/aclocal \
                ${libdir}/dovecot/dovecot/managesieve*"
FILES:${PN}-dbg = "${bindir}/.debug \
                ${libdir}/dovecot/.debug \
                ${libdir}/dovecot/*/.debug \
                ${prefix}/libexec/dovecot/.debug"

