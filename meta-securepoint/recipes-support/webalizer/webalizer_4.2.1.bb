# Copyright (C) 2014 Andreas Weigel <andreas.weigel@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

PVDASH = "cead822a9a2bf98453b48dbf2b491c628dc3ce77"
SRC_URI = "\
  git://github.com/StoneStepsInc/StoneStepsWebalizer.git;protocol=https;branch=master;rev=${PVDASH} \
  file://webalizer_localtime.patch \
  file://webalizer_replace_berkeley_db_by_lmdb.patch \
  file://webalizer_gnu++11.patch \
  file://webalizer_in_range.patch \
"

DESCRIPTION = "Fast free web server log file analysis program"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "\
  file://COPYING;md5=5574c6965ae5f583e55880e397fbb018 \
"

S = "${WORKDIR}/git"
SECTION = "console"
PR = "r0"

DEPENDS += " \
  gd \
  zlib \
  libpng \
  lmdb \
  libmaxminddb \
"

EXTRA_OECONF = "\
"

inherit autotools-brokensep

CXX:append = " -Wl,--hash-style=gnu"

do_install() {
  install -d ${D}/usr/bin
  install ${B}/build/webalizer ${D}/usr/bin
}

FILES:${PN} += "/usr/bin/webalizer"
