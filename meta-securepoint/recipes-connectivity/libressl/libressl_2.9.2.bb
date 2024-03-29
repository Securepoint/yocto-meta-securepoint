SUMMARY = "Secure Socket Layer"
DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "https://www.libressl.org/"
SECTION = "libs/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=01f9bb4d275f5eeea905377bef3de622"

SRC_URI = "\
	https://ftp.openbsd.org/pub/OpenBSD/LibreSSL/libressl-${PV}.tar.gz \
	file://libressl-notests.patch \
	file://c_rehash \
"
SRC_URI[md5sum] = "b3fa8935701af31c894c4d78f9a21f1c"
SRC_URI[sha256sum] = "c4c78167fae325b47aebd8beb54b6041d6f6a56b3743f4bd5d79b15642f9d5d4"

inherit autotools

PROVIDES += "openssl openssl10"
PROVIDES:class-native += "openssl-native"
PROVIDES:class-nativesdk += "nativesdk-openssl"

RPROVIDES:${PN} += "openssl openssl10"
RREPLACES_${PN} += "openssl openssl10"
RCONFLICTS_${PN} += "openssl openssl10"

RPROVIDES:${PN}-bin += "openssl-bin"
RREPLACES_${PN}-bin += "openssl-bin"
RCONFLICTS_${PN}-bin += "openssl-bin"

PACKAGES =+ "libcrypto libssl libtls ${PN}-conf ${PN}-bin"

FILES:${PN}-bin  = "${bindir}/*"
FILES:libcrypto  = "${libdir}/libcrypto.so.*"
FILES:libssl     = "${libdir}/libssl.so.*"
FILES:libtls     = "${libdir}/libtls.so.*"
FILES:${PN}-conf = "${sysconfdir}/ssl"

CONFFILES:${PN}-conf = "${sysconfdir}/ssl/openssl.cnf"
RRECOMMENDS_libcrypto += "${PN}-conf"

# cert.pem conflicts with cert.pem from ca-bundle
do_install:append () {
  rm -f ${D}/${sysconfdir}/ssl/cert.pem
}

# c_rehash is required by ca-certificates-native
do_install:append () {
  install -d ${D}/usr/bin
  install -m 0755 ${WORKDIR}/c_rehash ${D}${bindir}
}

BBCLASSEXTEND = "native nativesdk"
