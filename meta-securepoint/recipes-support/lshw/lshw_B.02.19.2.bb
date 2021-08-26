# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Hardware lister"
DESCRIPTION = "\
lshw is a small tool to provide detailed informaton on the hardware\
configuration of the machine. It can report exact memory configuration,\
firmware version, mainboard configuration, CPU version and speed, cache\
configuration, bus speed, etc. on DMI-capable x86 systems and on some\
PowerPC machines (PowerMac G4 is known to work)."
HOMEPAGE = "https://ezix.org/project/wiki/HardwareLiSter"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SECTION = "console/utils"
DEPENDS = ""
PR = "r0"

SRC_URI = "\
    https://ezix.org/software/files/lshw-${PV}.tar.gz \
    file://pci.ids \
"
SRC_URI[md5sum] = "8c70d46e906688309095c73ecb9396e3"
SRC_URI[sha256sum] = "9bb347ac87142339a366a1759ac845e3dbb337ec000aa1b99b50ac6758a80f80"

inherit autotools-brokensep

CXX_append = " ${LDFLAGS}"

do_compile () {
    oe_runmake CXX="$CXX"
}

do_install_append() {
    cp -f ${WORKDIR}/pci.ids ${D}/usr/share/lshw/pci.ids
}
