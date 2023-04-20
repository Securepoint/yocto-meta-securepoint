# Copyright (C) 2015 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The serializing counterpart to json-parser."
HOMEPAGE = "https://github.com/udp/json-builder"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5f3944d43ae0e4f5ef4f9b20ac96cb9a"
PR = "r0"

SRC_URI = "git://github.com/udp/json-builder.git;protocol=https;branch=master \
           file://0001-escape-all-control-characters.patch \
           file://0002-Replace-json_integer-magic-with-printf-calls.patch \
           file://json-builder-incpath.patch"

SRC_URI[md5sum] = "628fbcde55b1f330330259fddfe3d819"
SRC_URI[sha256sum] = "9945755591ec1825a8db75bf6d029f037904c0dfd60a8e2516f32a8309c6154b"
SRCREV = "19c739f64d1da157789c35a06911b865486e6c2e"
S = "${WORKDIR}/git"

DEPENDS = "json-parser"

inherit cmake

OECMAKE_C_FLAGS += " -fPIC "
RDEPENDS:${PN}-dev = ""
