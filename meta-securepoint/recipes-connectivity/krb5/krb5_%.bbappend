# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

#PACKAGECONFIG = ""
PACKAGES:prepend = " ${PN}-libs ${PN}-workstation "
DEPENDS:remove = "util-linux"

FILES:${PN}-libs = "/usr/lib/*.so.*"
FILES:${PN}-workstation = "\
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
