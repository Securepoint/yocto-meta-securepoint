# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Sieve and ManageSieve support for Dovecot v2.0"
HOMEPAGE = "http://pigeonhole.dovecot.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"
SECTION = "mail"
DEPENDS = "dovecot"
PR = "r0"

SRC_URI = "http://pigeonhole.dovecot.org/releases/2.2/dovecot-2.2-pigeonhole-${PV}.tar.gz \
"

SRC_URI[md5sum] = "7e6f55da44500b59b832b3d44dd0cbcb"
SRC_URI[sha256sum] = "dd871bb57fad22795460f613f3c9484a8bf229272ac00956d837a34444f1c3a9"


#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
S = "${WORKDIR}/dovecot-2.2-pigeonhole-${PV}"

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

