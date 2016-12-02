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
    ftp://ftp.freeradius.org/pub/radius/pam_radius-1.3.17.tar.gz \
    file://pam_radius-make.patch \
"
SRC_URI[md5sum] = "a5d27ccbaaad9d9fb254b01a3c12bd06"
SRC_URI[sha256sum] = "60ee863cbea797be46eff8b9d568af057c6e54335bdb19a6bd2cadde389d7dca"

S = "${WORKDIR}/pam_radius-1.3.17"

inherit autotools

FILES_${PN} = "/lib/security/pam_radius_auth.so"
FILES_${PN}-dbg = "/lib/security/.debug/pam_radius_auth.so"
