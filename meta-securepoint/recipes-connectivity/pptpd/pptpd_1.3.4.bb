# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "PPTP Server"
DESCRIPTION = "\
PoPToP is the PPTP Server solution for Linux. PoPToP allows Linux servers\
to function seamlessly in the PPTP VPN environment. This enables\
administrators to leverage the considerable benefits of both Microsoft and\
Linux. The current pre-release version supports Windows 95/98/NT/2000 PPTP\
clients and PPTP Linux clients.\
"
HOMEPAGE = "http://poptop.sourceforge.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
SECTION = "extra/network"
PR = "r0"

SRC_URI = "\
    http://dl.sourceforge.net/sourceforge/poptop/pptpd-1.3.4.tar.gz \
    file://pptpd-install.patch \
    file://etc_sv_pptpd_run \ 
    file://etc_sv_pptpd_down \ 
"
SRC_URI[md5sum] = "b38df9c431041922c997c1148bedf591"
SRC_URI[sha256sum] = "c23a9bdd219a0a36ae7ca20e7c9749f2efdcdbf108aabeeeb5066ba442ec88b6"

inherit autotools-brokensep runit

RUNIT_SERVICES = "pptpd"

do_configure_append () {
    # remove hardcoded gcc dependency to fix cross copiling issue
    sed -i -e "s/^CC.*gcc//" ${S}/plugins/Makefile
}

do_install_prepend () {
    mkdir -p ${D}/usr/lib/pptpd
}
