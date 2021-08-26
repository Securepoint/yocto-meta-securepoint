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
    https://github.com/lsh123/xmlsec/archive/xmlsec-1_2_25.tar.gz \
"

SRC_URI[md5sum] = "6c866b49c64b38290aa87f2886808660"
SRC_URI[sha256sum] = "5a2d400043ac5b2aa84b66b6b000704f0f147077afc6546d73181f5c71019985"

S = "${WORKDIR}/xmlsec-xmlsec-1_2_25"

FILES_${PN}-dev += "${libdir}/xmlsec1Conf.sh"
