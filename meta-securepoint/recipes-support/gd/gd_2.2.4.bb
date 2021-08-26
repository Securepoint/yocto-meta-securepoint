# gd
SECTION = "libs"
LICENSE = "GD"
LIC_FILES_CHKSUM = "file://COPYING;md5=07384b3aa2e0d39afca0d6c40286f545"
DESCRIPTION = "gd is a library used to create PNG, JPEG, or WBMP images."
DEPENDS = "freetype libpng jpeg zlib bzip2"
PR = "r0"

SRC_URI = "https://github.com/libgd/libgd/releases/download/gd-2.2.4/libgd-${PV}.tar.gz"
SRC_URI[md5sum] = "0a3c307b5075edbe1883543dd1153c02"
SRC_URI[sha256sum] = "487a650aa614217ed08ab1bd1aa5d282f9d379cfd95c756aed0b43406381be65"

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
