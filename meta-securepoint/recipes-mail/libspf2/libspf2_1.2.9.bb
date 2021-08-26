DESCRIPTION = "libspf2 is a complete and robust implementation of SPF which \
provides support for many MTAs."
SECTION = "libs/network"
LICENSE = "LGPLv2.1+ | BSD-2-Clause"
HOMEPAGE = "http://www.libspf2.org/index.html"
SRC_URI = "\
       http://build.intern.securepoint.de/downloads/${PN}/${PN}-${PV}.tar.gz \
       file://libspf2-__VA_ARGS__.patch \
"
S = "${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "3305df4d1b13ca964d80b23bb5e4e2b6"
SRC_URI[sha256sum] = "4837f6b063b1431673754cbf6bef8979de5ffc4d7f26f6b93abd42787ba04862"
LIC_FILES_CHKSUM = "file://LICENSES;md5=7b5c6637631b4d6d1f619a59008b0f24"

inherit autotools-brokensep

DISABLE_STATIC = ""

PACKAGES =+ "${PN}-bin-dbg ${PN}-bin"
FILES_${PN}-bin = "${bindir}"
FILES_${PN}-bin-dbg = "${bindir}/.debug"
FILES_${PN} = "${libdir}/lib*.so.*"
