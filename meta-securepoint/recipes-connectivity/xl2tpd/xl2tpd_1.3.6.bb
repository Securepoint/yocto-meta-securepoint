# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Layer 2 Tunneling Protocol (L2TP) daemon"
DESCRIPTION = "Layer 2 Tunneling Protocol (L2TP) daemon"
HOMEPAGE = "https://www.xelerance.com/services/software/xl2tpd/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0636e73ff0215e8d672dc4c32c317bb3"
SECTION = "console/network"
DEPENDS = "libpcap"
PR = "r0"

SRC_URI = "https://github.com/xelerance/xl2tpd/archive/v${PV}.tar.gz \
           file://etc_sv_l2tpd_down \ 
           file://etc_sv_l2tpd_run \
"
SRC_URI[md5sum] = "2f526cc0c36cf6d8a74f1fb2e08c18ec"
SRC_URI[sha256sum] = "49b069aa8d873e1d8f615ccc4212351e427bf681ba453fdd211256a8345bb7fb"

inherit autotools-brokensep runit

RUNIT_SERVICES = "l2tpd"
EXTRA_OEMAKE += " PREFIX=/usr "
