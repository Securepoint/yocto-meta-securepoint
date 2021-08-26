# Copyright (C) 2019 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "modern asynchronous DNS API"
DESCRIPTION = "getdns is an implementation of a modern asynchronous DNS API; \
the specification was originally edited by Paul Hoffman. It is intended to \
make all types of DNS information easily available to application developers \
and non-DNS experts."
HOMEPAGE = "https://getdnsapi.net/"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "openssl libyaml"

inherit autotools

SRC_URI = "https://getdnsapi.net/releases/getdns-1-5-1/getdns-${PV}.tar.gz \
	   file://getdns_libressl.patch"
SRC_URI[md5sum] = "a3f3e16a48f521875f051cd64e5d33ec"
SRC_URI[sha256sum] = "5686e61100599c309ce03535f9899a5a3d94a82cc08d10718e2cd73ad3dc28af"

EXTRA_OECONF += "--without-libidn --without-libidn2 --enable-stub-only --with-ssl=${STAGING_LIBDIR}/.."
EXTRA_OEMAKE += "LIBTOOL='${WORKDIR}/build/${HOST_SYS}-libtool'"

PACKAGES_prepend += " ${PN}-libs "

FILES_${PN}-libs = "/usr/lib/libgetdns.so.*"
