# Copyright (C) 2015 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Active Directory Keytab Management"
HOMEPAGE = "http://sourceforge.net/projects/msktutil/"
LICENSE = "GPLv2"
SECTION = "base"
DEPENDS = "krb5 openldap cyrus-sasl"
PR = "r0"

SRC_URI = "git://github.com/3van/msktutil.git;protocol=http"
SRCREV = "a77f5c2b2e4dbab1bef7014fcf215f194f289578"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OECONF = "--cache-file=config.cache"

do_configure_prepend(){
    echo "ac_cv_header_et_com_err_h=no" >> config.cache
}
