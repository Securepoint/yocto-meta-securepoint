# Copyright (C) 2016 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SRC_URI = "http://cr.yp.to/${PN}/${PN}-${PV}.tar.gz"
DJB_SRC_URI = "http://cr.yp.to/"
HOMEPAGE = "http://cr.yp.to/"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/PD;md5=b3597d12946881e13cb3b548d1173851"
SECTION = "djb"

do_configure(){
    # http://cr.yp.to/docs/unixport.html#errno
    echo "${CC} -fPIC -include ${STAGING_INCDIR}/errno.h" > "${S}/conf-cc"
    echo "${CC}" > "${S}/conf-ld"
    [ -e "${S}/conf-home" ] && echo "/usr" > "${S}/conf-home"
}

