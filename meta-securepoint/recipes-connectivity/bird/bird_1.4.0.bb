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
DEPENDS = "flex bison"
PR = "r0"

SRC_URI = "\
    ftp://bird.network.cz/pub/${PN}/${PN}-${PV}.tar.gz \
    file://bird-1.4.0-readline.patch \
    file://etc_sv_bird_run \
    file://etc_sv_bird_down \
"
SRC_URI[md5sum] = "4e5a47308335b1b0bf4691cac6c4174f"
SRC_URI[sha256sum] = "da1b41cab26388b01d861c30afe41678df22dc9ea0110a14c1cc7b7bffc693c5"

inherit autotools runit

RUNIT_SERVICES = "bird"
EXTRA_OECONF  += "\
    --enable-pthreads \
"

do_configure() {
    oe_runconf ${EXTRA_OECONF}
}

do_install_append() {
    rm -rf ${D}/run
}

PACKAGES += " bird-client"

FILES_${PN}-client = "/usr/sbin/birdc*"
