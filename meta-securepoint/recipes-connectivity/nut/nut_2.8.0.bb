# Copyright (C) 2014 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The primary goal of the Network UPS Tools (NUT) project is to provide support for Power Devices, such as Uninterruptible Power Supplies, Power Distribution Units and Solar Controllers."
HOMEPAGE = "http://www.networkupstools.org/"
SECTION = "monitoring"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=53e0b94f340d008730893290b5b42ee4"
PR = "r0"

#SRCREV = "v${PV}"
SRCREV = "ff16dabca191e5fd8ddc20137317bdebee554d8d"
SRC_URI = "git://github.com/networkupstools/nut.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

DEPENDS = "net-snmp openssl libusb libtool"

inherit autotools-brokensep pkgconfig useradd runit


PARALLEL_MAKE = ""
USERADD_PACKAGES += "${PN}"
GROUPADD_PARAM:${PN} = "-r -o -g 400 nut;"
USERADD_PARAM:${PN} = "-d /var/run/nut -o -u 400 -g nut -s /bin/false ups;"


EXTRA_OEMAKE += " PREFIX=/usr "
EXTRA_OECONF += '--with-doc=no --with-usb --with-snmp \
--with-user=ups --with-group=nut \
--with-pidpath=/var/run/nut --with-altpidpath=/var/run/nut --with-statepath=/var/run/nut --sysconfdir=/etc/nut'


do_configure:prepend(){
    ./autogen.sh
}

do_configure:append(){
    sed -i -e "s#install: install-am#install: #g" docs/man/Makefile 
}

FILES:${PN}-dev:append = " /usr/share/"
