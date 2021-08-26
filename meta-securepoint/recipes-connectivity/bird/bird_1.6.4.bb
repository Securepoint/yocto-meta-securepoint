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
"
SRC_URI[md5sum] = "d62ec2547338e8d3dfb934b4c7b2faa4"
SRC_URI[sha256sum] = "c26b8caae988dba81a9dbbee93502463d4326d1b749d728d62aa5529c605afc0"

inherit autotools runit

RUNIT_SERVICES = "bird"
PACKAGES_prepend += " bird-client "

EXTRA_OECONF  += "\
    --enable-pthreads \
"

do_configure() {
    oe_runconf ${EXTRA_OECONF}
}

do_install_append () {
    rm -rf ${D}/run ${D}/var/run
}

FILES_${PN}-client = "/usr/sbin/birdc*"
