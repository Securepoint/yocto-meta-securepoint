# Copyright (C) 2017 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Active Directory enrollment"
DESCRIPTION = "adcli is a tool for joining an Active Directory domain using\
standard LDAP and Kerberos calls."
HOMEPAGE = "http://cgit.freedesktop.org/realmd/adcli"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=23c2a5e0106b99d75238986559bb5fc6"
SECTION = "console/network"
DEPENDS = "openldap krb5 cyrus-sasl"
RDEPENDS:${PN} = "cyrus-sasl-plugin-gssapiv2"
PR = "r0"

SRC_URI = "\
	http://www.freedesktop.org/software/realmd/releases/adcli-${PV}.tar.gz \
	file://adcli-opt-fqdn.patch \
	file://adcli-set-fqdn.patch \
"
SRC_URI[md5sum] = "921848209eeb8ca7ec9685243806af26"
SRC_URI[sha256sum] = "72f6db406e35d96de2bdc413a5ed69f28a4a735c08670c6556713c3f83921aa4"

inherit autotools pkgconfig

PARALLEL_MAKE = ""
EXTRA_OECONF += " --enable-doc=no"
LDFLAGS = '-Wl,--copy-dt-needed-entries'
