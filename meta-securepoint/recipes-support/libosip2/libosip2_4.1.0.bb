# Copyright (C) 2014 Gernot Tenchio <gernot@tenchio.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SUMMARY = "SIP implementation"
DESCRIPTION = "\
oSIP is an implementation of the Session Initiation Protocol as described\
by the rfc3261 (wich deprecates rfc2543). This library aims to provide\
multimedia and telecom software developers an easy and powerful interface\
to initiate and control SIP based sessions in their applications. SIP is\
an open standard replacement from IETF for H323."
HOMEPAGE = "http://www.gnu.org/software/osip/osip.html"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e639b5c15b4bd709b52c4e7d4e2b09a4"
PR = "r0"

SRC_URI = "http://ftp.gnu.org/gnu/osip/libosip2-${PV}.tar.gz"
SRC_URI[md5sum] = "756423628683c07f817432f046a26516"
SRC_URI[sha256sum] = "996aa0363316a871915b6f12562af53853a9962bb93f6abe1ae69f8de7008504"

inherit autotools
