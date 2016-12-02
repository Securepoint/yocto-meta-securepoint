DESCRIPTION = "XMLSec library provides C based implementation for major XML Security standards XML Signature Syntax and Processing and XML Encryption Syntax and Processing"
DEPENDS = "libxml2 libxslt openssl"
LICENSE = "MIT|BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=352791d62092ea8104f085042de7f4d0"

EXTRA_OECONF = "\
    --disable-crypto-dl \
    --without-nss --without-nspr \
    --without-gcrypt \
    --without-gnutls \
"

inherit autotools-brokensep pkgconfig

PARALLEL_MAKE = ""

SRC_URI = "\
    https://github.com/lsh123/xmlsec/archive/xmlsec-1_2_22.tar.gz \
    file://xmlsec1-1.2.22-xmlgetprop-memleak.patch \
"

SRC_URI[md5sum] = "b0bdaeee4cb39bfe56f2d28bae17faa8"
SRC_URI[sha256sum] = "f04da7bb6e20e9b9b7a39bb64cfad6836c665cfa6e14f6316fc24fcac6cbac06"

S = "${WORKDIR}/xmlsec-xmlsec-1_2_22"

FILES_${PN}-dev += "${libdir}/xmlsec1Conf.sh"
