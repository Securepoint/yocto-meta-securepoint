FILES:${PN}-dev += " /usr/bin/libvncserver-config"
INSANE_SKIP:${PN} += " rpaths"
# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SUMMARY = "Library to make writing a vnc server easy"
DESCRIPTION = "\
LibVNCServer makes writing a VNC server (or more correctly, a program\
exporting a framebuffer via the Remote Frame Buffer protocol) easy."
HOMEPAGE = "https://github.com/LibVNC/libvncserver"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"
SECTION = "system/libraries"
DEPENDS = "openssl libpng jpeg libgcrypt"
PR = "r0"

inherit cmake pkgconfig

SRC_URI = " \
    https://github.com/LibVNC/libvncserver/archive/LibVNCServer-${PV}.tar.gz \
    file://libvncserver-nodh.patch \
    file://0001-BUGFIX-Assuming-the-header-is-always-a-minimum-of-14.patch \
"

SRC_URI[md5sum] = "dc2ae6433d2ce45b9f60034c7fb9c10a"
SRC_URI[sha256sum] = "33cbbb4e15bb390f723c311b323cef4a43bcf781984f92d92adda3243a116136"
S = "${WORKDIR}/libvncserver-LibVNCServer-${PV}"


