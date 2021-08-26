SUMMARY = "Secure Socket Layer"
DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "https://www.libressl.org/"
SECTION = "libs/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=01f9bb4d275f5eeea905377bef3de622"

SRC_URI = "\
	https://ftp.openbsd.org/pub/OpenBSD/LibreSSL/libressl-${PV}.tar.gz \
	file://c_rehash \
"
SRC_URI[md5sum] = "1a3c38e6f08464c4506c014f3fa570a6"
SRC_URI[sha256sum] = "bee5038d85ef0160a42b9096b35160e9f79ef5d29bf4cb5b38419c793a5bc040"

inherit autotools

PROVIDES += "openssl openssl10"
PROVIDES_class-native += "openssl-native"
PROVIDES_class-nativesdk += "nativesdk-openssl"

RPROVIDES_${PN} += "openssl openssl10"
RREPLACES_${PN} += "openssl openssl10"
RCONFLICTS_${PN} += "openssl openssl10"

PACKAGES =+ "libcrypto libssl libtls ${PN}-conf"

FILES_libcrypto  = "${libdir}/libcrypto.so.*"
FILES_libssl     = "${libdir}/libssl.so.*"
FILES_libtls     = "${libdir}/libtls.so.*"
FILES_${PN}-conf = "${sysconfdir}/ssl"
FILES_${PN}_append_class-native = " ${bindir}/c_rehash"

CONFFILES_${PN}-conf = "${sysconfdir}/ssl/openssl.cnf"
RRECOMMENDS_libcrypto += "${PN}-conf"

# cert.pem conflicts with cert.pem from ca-bundle
do_install_append () {
  rm -f ${D}/${sysconfdir}/ssl/cert.pem
}

# c_rehash is required by ca-certificates-native
do_install_append_class-native () {
  install -d ${D}/usr/bin
  install -m 0755 ${WORKDIR}/c_rehash ${D}${bindir}
}

BBCLASSEXTEND = "native nativesdk"
