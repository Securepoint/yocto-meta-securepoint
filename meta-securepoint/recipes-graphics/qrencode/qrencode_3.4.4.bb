# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SUMMARY = "Programm and C library for encoding data in a QR Code symbol"
DESCRIPTION = "\
Libqrencode is a C library for encoding data in a QR Code symbol, \
a kind of 2D symbology that can be scanned by handy terminals \
such as a mobile phone with CCD. The capacity of QR Code is \
up to 7000 digits or 4000 characters, and is highly robust."
HOMEPAGE = "http://fukuchi.org/works/qrencode/"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "extra/graphic"
DEPENDS = "libpng"
PR = "r0"

SRC_URI = "http://fukuchi.org/works/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "be545f3ce36ea8fbb58612d72c4222de"
SRC_URI[sha256sum] = "e794e26a96019013c0e3665cb06b18992668f352c5553d0a553f5d144f7f2a72"

inherit autotools pkgconfig

do_configure () {
    oe_runconf --with-tests=no --enable-rpath=no
    sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' libtool
    sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' libtool
}

PACKAGES:prepend  = " libqrencode "
FILES:libqrencode = "/usr/lib/libqrencode.so.*"

