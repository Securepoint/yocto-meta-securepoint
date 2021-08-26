# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
  file://krb5-initialize-vars.patch \
  file://krb5-libressl.patch \
"

PACKAGECONFIG = ""
PACKAGES_prepend += " ${PN}-libs ${PN}-workstation "
DEPENDS_remove = "util-linux"

FILES_${PN}-libs = "/usr/lib/*.so.*"
FILES_${PN}-workstation = "\
    /etc/pam.d/ksu \
    /usr/bin/k5srvutil \
    /usr/bin/kadmin \
    /usr/bin/kdestroy \
    /usr/bin/kinit \
    /usr/bin/klist \
    /usr/bin/kpasswd \
    /usr/bin/ksu \
    /usr/bin/kswitch \
    /usr/bin/ktutil \
    /usr/bin/kvno \
    /usr/sbin/krb5-send-pr"
