# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A FCGI spawner for lighttpd and cherokee and other webservers"
HOMEPAGE = "http://redmine.lighttpd.net/projects/spawn-fcgi/wiki"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=e4dac5c6ab169aa212feb5028853a579"
SECTION = "network"
PR = "r0"

SRC_URI = "\
    http://download.lighttpd.net/spawn-fcgi/releases-1.6.x/spawn-fcgi-1.6.3.tar.bz2 \
    file://etc_sv_spawn-fcgi_run \
"
SRC_URI[md5sum] = "787ed2f88d2204bf1fe4fbd6e509d1d7"
SRC_URI[sha256sum] = "e6721dc0fa59bb00e05f160406815d53c95e81ac28f7e52fbd36497584f846cd"

inherit autotools runit

RUNIT_SERVICES = "${PN}"

PACKAGES += "${PN}-runit"

FILES_${PN}-runit = "/etc/sv"

RPROVIDES_${PN} += "${PN}-runit"
