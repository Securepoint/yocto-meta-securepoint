# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Sieve and ManageSieve support for Dovecot v2.0"
HOMEPAGE = "http://pigeonhole.dovecot.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=bbb461211a33b134d42ed5ee802b37ff"
SECTION = "mail"
DEPENDS = "dovecot"
PR = "r0"

SRC_URI = "http://pigeonhole.dovecot.org/releases/2.2/dovecot-2.2-pigeonhole-${PV}.tar.gz \
           file://pigeonhole-defaultfolder.patch"
SRC_URI[md5sum] = "e8cb4976db9811d37e9d870f2f75ffef"
SRC_URI[sha256sum] = "0499f07037b86489fdc1d48fb19e298d5360ec41273bccaec230eb1bcf5a3e13"

#FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
S = "${WORKDIR}/dovecot-2.2-pigeonhole-${PV}"

inherit autotools-brokensep

EXTRA_OECONF = "--with-dovecot=${S} --disable-static"

do_configure_prepend(){
    #copy over file from dovecot and modify paths in place
    cp ${STAGING_DIR_HOST}/${libdir}/dovecot/dovecot-config ${S}
    sed -i "s:LIBDOVECOT='-L.*':LIBDOVECOT='-L${STAGING_DIR_HOST}${libdir}/dovecot -ldovecot':g" dovecot-config
    sed -i "s:LIBDOVECOT_INCLUDE=-I.*:LIBDOVECOT_INCLUDE=-I${STAGING_DIR_HOST}${includedir}/dovecot:g" dovecot-config

}

FILES_${PN} = " ${bindir} \
                ${libdir}/dovecot/libdovecot-sieve.so.* \
                ${prefix}/libexec/dovecot/*"
FILES_${PN}-dev = "${includedir} \
                /usr/src \
                ${libdir}/dovecot/*.so \ 
                ${libdir}/dovecot/*/*.so \ 
                ${libdir}/dovecot/*.la \
                ${libdir}/dovecot/*/*.la \
                ${libdir}/dovecot/dovecot/managesieve*"
FILES_${PN}-dbg = "${bindir}/.debug \
                ${libdir}/dovecot/.debug \
                ${libdir}/dovecot/*/.debug \
                ${prefix}/libexec/dovecot/.debug"

