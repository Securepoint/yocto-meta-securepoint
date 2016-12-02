# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Development drivers for Linux Integration Services"
SECTION = "HyperV"
LICENSE = "GPLv2"
PR = "r0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7d5ac042ab7c273d638ecd046d47c025"

SRCREV = "HEAD"
SRC_URI = "git://github.com/LIS/LIS${PV}.git;protocol=http \
           file://nodaemonize.patch \
"
S = "${WORKDIR}/git"

DEPENDS = ""


do_configure(){
    :
}

do_compile(){
    cd hv-rhel6.x/hv/tools
    ${CC} -DMYLICVERSION=\"3.5\" hv_vss_daemon.c -o hv_vss_daemon
    ${CC} -DMYLICVERSION=\"3.5\" hv_kvp_daemon.c -o hv_kvp_daemon
}

do_install(){
    install -d ${D}${bindir}
    install -m 755 hv-rhel6.x/hv/tools/hv_vss_daemon ${D}${bindir}
    install -m 755 hv-rhel6.x/hv/tools/hv_kvp_daemon ${D}${bindir}
}

