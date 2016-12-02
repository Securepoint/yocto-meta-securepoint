# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "FreeRDP: A Remote Desktop Protocol client."
HOMEPAGE = "https://github.com/downloads/tenchman/FreeRDP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"
SECTION = "extra/network"
DEPENDS = "openssl"
PR = "r0"

SRC_URI = "\
    https://github.com/downloads/tenchman/FreeRDP/freerdp-${PV}-sp.tar.bz2 \
    file://freerdp-rdpset.patch \
    file://freerdp-hexdump.patch \
    file://freerdp-unicode.patch \
"

SRC_URI[md5sum] = "599885347ac3a3d5a6be4039af3bac8a"
SRC_URI[sha256sum] = "6342d5a646d7de1c901466c80f948def9616b188f11786a006dd1bd1af90653f"
S = "${WORKDIR}/${PN}-${PV}-sp"

inherit autotools-brokensep

EXTRA_OECONF += "\
    --with-printer=no \
    --without-xkbfile \
    --without-debug \
    --enable-silent-rules \
    --without-alsa \
    --without-x \
    --without-xvideo \
    --with-crypto=openssl \
    --enable-tls"

do_configure () {
    sh autogen.sh
    oe_runconf ${EXTRA_OECONF}
}

PACKAGES_prepend += " ${PN}-keymaps "
FILES_${PN}-keymaps = "/usr/share/freerdp/keymaps"
