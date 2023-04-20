DESCRIPTION = "GraphicsMagick provides a set of commandline tools and programming APIs for manipulating, editing, and converting raster and vector images. It is derived from ImageMagick, with the objective of providing better stability and performance than ImageMagick."
SECTION = "graphics"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=cfb6da3e87445b79f462b34a75782b6d"

SRC_URI = "https://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/GraphicsMagick-${PV}.tar.xz"
SRC_URI[md5sum] = "045d5355aeb70cbb67d898120405a6d0"
SRC_URI[sha256sum] = "df009d5173ed0d6a0c6457234256c5a8aeaace782afa1cbab015d5a12bd4f7a4"

# some people like CamelCase package names
S = "${WORKDIR}/GraphicsMagick-${PV}"

DEPENDS = "jpeg libpng tiff zlib bzip2 xz libwebp"
# DEPENDS += libwmf" needs libwmflite https://sourceforge.net/projects/wvware/files/libwmf/
RDEPENDS:${PN} = "ghostscript"

inherit autotools-brokensep

EXTRA_OECONF += " --enable-shared --with-tiff=yes --with-lzma=yes --with-webp=yes"

FILES:${PN} += "/usr/lib/GraphicsMagick-*/config" 
FILES:${PN}-dev += "/usr/share/GraphicsMagick-*" 
