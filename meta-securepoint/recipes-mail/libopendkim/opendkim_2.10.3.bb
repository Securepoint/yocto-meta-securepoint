DL_TAG = "${@d.getVar('PV',d,1).replace('.', '-')}"

SRC_URI = "https://github.com/trusteddomainproject/${PN}/archive/rel-${PN}-${DL_TAG}.tar.gz \
"

SRC_URI[md5sum] = "edc7ab77bc06da0c373ac4cb0ae9d8fa"
SRC_URI[sha256sum] = "af5da17b6339b2ed5a8340cd9190919121b7c20d0f17657832732d242bee34b9"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea9056dd2f14906bec57fe79c021a52e"

PR = "r0"
S = "${WORKDIR}/OpenDKIM-rel-${PN}-${DL_TAG}"

include opendkim.inc
