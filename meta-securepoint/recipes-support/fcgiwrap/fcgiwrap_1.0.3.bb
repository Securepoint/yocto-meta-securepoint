# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A simple FastCGI wrapper for CGI scripts"
HOMEPAGE = "http://nginx.localdomain.pl/wiki/FcgiWrap"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://fcgiwrap.c;beginline=1;endline=24;md5=a285ce4762664ed6b64db11a89eb4089"
SECTION = "network"
PR = "r0"

DEPENDS = "fcgi"

SRC_URI = "http://github.com/gnosek/fcgiwrap/archive/1.0.3.tar.gz"
SRC_URI[md5sum] = "da8e78520a7a764fe37b1bfe4e697f81"
SRC_URI[sha256sum] = "fd0523006a2589aad415eecc871b8f082e8a3dedd2b45f0f03a2389cd6108cda"

inherit autotools-brokensep

CC_append = " ${LDFLAGS}"

EXTRA_OECONF = "\
    --prefix= \
    --sbindir=${sbindir} \
    --mandir=${mandir} \
"
