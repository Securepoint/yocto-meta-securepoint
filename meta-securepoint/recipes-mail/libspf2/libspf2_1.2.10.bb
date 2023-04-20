DESCRIPTION = "libspf2 is a complete and robust implementation of SPF which \
provides support for many MTAs."
SECTION = "libs/network"
LICENSE = "LGPLv2.1+ | BSD-2-Clause"
HOMEPAGE = "http://www.libspf2.org/index.html"
SRC_URI = "\
       http://build.intern.securepoint.de/downloads/${PN}/${PN}-${PV}.tar.gz \
       file://libspf2-__VA_ARGS__.patch \
       file://libspf2-resolv.patch \
       file://001-update_to_1_2_11.patch \
"
S = "${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "7bb9937d0705649eaa8646de66dc3562"
SRC_URI[sha256sum] = "d91e3de81ae287a2976c44f60283bd3000d720e6a112dc7142eedf1831b821c9"
LIC_FILES_CHKSUM = "file://LICENSES;md5=7b5c6637631b4d6d1f619a59008b0f24"

inherit autotools-brokensep

DISABLE_STATIC = ""

PACKAGES =+ "${PN}-bin-dbg ${PN}-bin"
FILES:${PN}-bin = "${bindir}"
FILES:${PN}-bin-dbg = "${bindir}/.debug"
FILES:${PN} = "${libdir}/lib*.so.*"
