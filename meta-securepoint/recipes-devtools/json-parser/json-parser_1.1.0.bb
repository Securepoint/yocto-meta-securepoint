# Copyright (C) 2015 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Very low footprint JSON parser written in portable ANSI C"
HOMEPAGE = "https://github.com/udp/json-parser"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3d6b2e8209f1a367db01715bcee036c7"
PR = "r0"

SRC_URI = "git://github.com/udp/json-parser.git;protocol=https \
           file://0001-Makefile-changes-compile-with-fPIC-and-lm-flags.patch"
SRC_URI[md5sum] = "89eed040e3b0b0ca7eab030e4de562b3"
SRC_URI[sha256sum] = "5c278793269dbbf98d5f1592c797234581df69088d2838a14154b4af52ebd133"
SRCREV = "c8f0af9b2204ac75aa28bf0fe9270f65108f0385"

S = "${WORKDIR}/git"

inherit autotools-brokensep

PARALLEL_MAKE = ""
