# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "BIRD Internet Routing Daemon"
DESCRIPTION = "\
BIRD is dynamic routing daemon supporting IPv4 and IPv6 versions of routing \
protocols BGP, RIP and OSPF."
HOMEPAGE = "http://http://bird.network.cz"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;beginline=29;endline=42;md5=c477c3cc44c05c765d09483e3930e3a7"
SECTION = "console/network"
DEPENDS = "flex-native bison-native readline"
PR = "r0"

SRC_URI = "\
    ftp://bird.network.cz/pub/${PN}/${PN}-${PV}.tar.gz \
    file://etc_sv_bird_run \
    file://etc_sv_bird_down \
    file://bird-kif_proto.patch \
"
SRC_URI[sha256sum] = "6c61ab5d2ef59d2559a8735b8252b5a0238013b43e5fb8a96c5d9d06e7bc00b2"

inherit autotools runit

RUNIT_SERVICES = "bird"
PACKAGES:prepend = " bird-client "

EXTRA_OECONF  += "\
    --enable-pthreads \
"

do_configure() {
    oe_runconf ${EXTRA_OECONF}
}

do_install:append () {
    rm -rf ${D}/run ${D}/var/run
}

FILES:${PN}-client = "/usr/sbin/birdc*"
