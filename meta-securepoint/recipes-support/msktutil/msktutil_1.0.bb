# Copyright (C) 2016 Gernot Tenchio <gernot@tenchio.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Program for interoperability with Active Directory"
DESCRIPTION = "Msktutil is a program for interoperability with Active Directory that can\
create a computer account in Active Directory, create a system Kerberos keytab,\
add and remove principals to and from that keytab, and change the computer\
account's password."
HOMEPAGE = "https://sourceforge.net/projects/msktutil/"
LICENSE = "GPLv2"
SECTION = "net"
DEPENDS = "krb5 openldap"
PR = "r0"

SRC_URI = "http://downloads.sourceforge.net/${PN}/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "ead421d223ba5f76bb42570e23818974"
SRC_URI[sha256sum] = "6e59d4bf41b8c75d573037c19ed29567a55f67ae5fe8c81e037b4f8c7327b642"

LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit autotools
