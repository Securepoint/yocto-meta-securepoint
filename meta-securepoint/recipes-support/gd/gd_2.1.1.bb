# gd
SECTION = "libs"
LICENSE = "GD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c97638cafd3581eb87abd37332137669"
DESCRIPTION = "gd is a library used to create PNG, JPEG, or WBMP images."
DEPENDS = "freetype libpng jpeg zlib bzip2"
PR = "r0"

SRC_URI = "https://bitbucket.org/libgd/gd-libgd/downloads/libgd-${PV}.tar.gz"
SRC_URI[md5sum] = "914f27f31ceb46827a322a1bc5165aff"
SRC_URI[sha256sum] = "cf47bce5a4c4c6dc77ba8d0349d1eec9ceff77ed86f14b249a0780b7f18554c5"

MIRRORS += "http://www.libgd.org/releases/ http://fossies.org/unix/www/ \n"

S = "${WORKDIR}/libgd-${PV}"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF += " --with-zlib \
                  --with-png \
                  --with-freetype \
                  --without-fontconfig \
                  --without-xpm \
                  --without-x"

EXTRA_OEMAKE = 'LDFLAGS="${LDFLAGS}"'

PACKAGES += "${PN}-programms"
FILES_${PN} = "/usr/lib"
FILES_${PN}-programms = "/usr/bin"
