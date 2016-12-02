# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The eXtensible ARchiver"
HOMEPAGE = "https://code.google.com/p/xar/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=345ea25975d7cdb5019e445bd366448a"
SECTION = "console/utils"
DEPENDS = "acl libxml2 zlib bzip2 openssl"
PR = "r0"

SRC_URI = "\
    https://xar.googlecode.com/files/xar-${PV}.tar.gz \
    file://xar-no-rpath.patch \
"
SRC_URI[md5sum] = "8eabb055d3387b8edc30ecfb08d2e80d"
SRC_URI[sha256sum] = "4c5d5682803cdfab16d72365cf51fc4075d597c5eeaa8c7d1990fea98cdae3e6"

inherit autotools

