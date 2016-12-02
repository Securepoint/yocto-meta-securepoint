# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Hardware lister"
DESCRIPTION = "\
lshw is a small tool to provide detailed informaton on the hardware\
configuration of the machine. It can report exact memory configuration,\
firmware version, mainboard configuration, CPU version and speed, cache\
configuration, bus speed, etc. on DMI-capable x86 systems and on some\
PowerPC machines (PowerMac G4 is known to work)."
HOMEPAGE = "http://ezix.org/project/wiki/HardwareLiSter"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SECTION = "console/utils"
DEPENDS = ""
PR = "r0"

SRC_URI = "\
    http://ezix.org/software/files/lshw-B.02.17.tar.gz \
    file://lshw-cross.patch \
"
SRC_URI[md5sum] = "a5feb796cb302850eaf5b4530888e3ed"
SRC_URI[sha256sum] = "eb9cc053fa0f1e78685cb695596e73931bfb55d2377e3bc3b8b94aff4c5a489c"

inherit autotools-brokensep

do_compile () {
    oe_runmake CXX="$CXX"
}
