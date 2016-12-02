# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Library to make writing a vnc server easy"
DESCRIPTION = "\
LibVNCServer makes writing a VNC server (or more correctly, a program\
exporting a framebuffer via the Remote Frame Buffer protocol) easy."
HOMEPAGE = "http://libvncserver.sourceforge.net/"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"
SECTION = "system/libraries"
DEPENDS = "openssl libpng jpeg"
PR = "r0"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/libvncserver/libvncserver/${PV}/LibVNCServer-${PV}.tar.gz \
    file://libvncserver-hixie.patch \
    file://libvncserver-nodh.patch \
    file://libvncserver-openssl.patch \
    file://libvncserver-no-x11vnc.patch \
"
SRC_URI[md5sum] = "70422169b122765693d2a294d13e3714"
SRC_URI[sha256sum] = "cf060d3525c2fb92be2fa18bbc697fb355592b52484e60745f3bcac5c3f803f9"
S = "${WORKDIR}/LibVNCServer-${PV}"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF += "\
    --without-x11vnc \
    --with-jpeg=${STAGING_LIBDIR}/.. \
    --with-png=${STAGING_LIBDIR}/.. \
    --without-x \
    --without-gnutls --without-gcrypt --without-crypt"

do_configure_prepend () {
    cd  ${S}
    sed -i -e "s/^SUBDIRS.*/SUBDIRS=libvncserver/" Makefile.am
    sed -i -e "s/AC_PROG_LIBTOOL/LT_INIT/" configure.ac
}

FILES_${PN}-dev += " /usr/bin/libvncserver-config"
INSANE_SKIP_${PN} += " rpaths"
