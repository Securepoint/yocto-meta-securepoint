# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A Pluggable Authentication Module for RADIUS authentication and accounting"
DESCRIPTION = "\
This is the PAM to RADIUS authentication module. It allows any \
PAM-capable machine to become a RADIUS client for authentication and \
accounting requests. You will need a RADIUS server to perform the \
actual authentication."
HOMEPAGE = "http://freeradius.org/pam_radius_auth/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbbd794e2a0a289b9dfcc9f513d1996e"
SECTION = "network"
PR = "r0"

SRC_URI = "\
    ftp://ftp.freeradius.org/pub/radius/pam_radius-${PV}.tar.gz \
    file://pam_radius-make.patch \
    file://pam_radius_framedip.patch \
"
SRC_URI[md5sum] = "80960fdc9b720677dbb51d17311664a0"
SRC_URI[sha256sum] = "742d79fc39824726c098e746bd3dc3484f983f5ee082c621c1e848b2c3725305"
DEPENDS = "libpam"

S = "${WORKDIR}/pam_radius-${PV}"

inherit autotools-brokensep

FILES_${PN} = "/lib/security/pam_radius_auth.so"
FILES_${PN}-dbg = "/lib/security/.debug/pam_radius_auth.so /usr/src/debug"
