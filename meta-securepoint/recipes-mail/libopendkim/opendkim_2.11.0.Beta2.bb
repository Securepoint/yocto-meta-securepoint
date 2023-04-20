DL_TAG = "${@d.getVar('PV',d,1).replace('.', '-')}"

SRC_URI = "https://github.com/trusteddomainproject/${PN}/archive/rel-${PN}-${DL_TAG}.tar.gz \
           file://01_libressl.patch \
           file://02_nxdomain2nokey.patch \
           file://03_dns_read_rsa_pubkey.patch \
           file://04_fix_canon_assert.patch \
           file://05_signature_algorithm_unsupported.patch \
           file://06_fix_ed_keybits.patch \
"

SRC_URI[md5sum] = "0be899116b3246fc8a1ed42671ba005b"
SRC_URI[sha256sum] = "b3052047279fe6f114cc36b0080bd3db185ed0cc98363327ac5c53d511850016"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea9056dd2f14906bec57fe79c021a52e"

PR = "r0"
S = "${WORKDIR}/OpenDKIM-rel-${PN}-${DL_TAG}"

include opendkim.inc
