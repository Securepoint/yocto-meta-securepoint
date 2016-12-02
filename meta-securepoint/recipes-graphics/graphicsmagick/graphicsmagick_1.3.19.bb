DESCRIPTION = "GraphicsMagick provides a set of commandline tools and programming APIs for manipulating, editing, and converting raster and vector images. It is derived from ImageMagick, with the objective of providing better stability and performance than ImageMagick."
SECTION = "graphics"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=cfb6da3e87445b79f462b34a75782b6d"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${PV}/GraphicsMagick-${PV}.tar.xz"
SRC_URI[md5sum] = "e2795d7bdc2f3917804e40c8cae1993e"
SRC_URI[sha256sum] = "de64372bb7bbff18cf14afd35c8239d9588592f053fd51a82f7239ad42174a54"

# some people like CamelCase package names
S = "${WORKDIR}/GraphicsMagick-${PV}"

DEPENDS = "ghostscript jpeg libpng"

inherit autotools-brokensep

EXTRA_OECONF += " --enable-shared"
EXTRA_OEMAKE += " PREFIX=/usr "


do_configure_prepend(){
    sed -i 's|freetype_prefix=`${freetype_config} --prefix`|freetype_prefix=${STAGING_DIR_HOST}/usr|g' configure.ac
    sed -i 's|freetype_exec_prefix=`${freetype_config} --exec-prefix`|freetype_exec_prefix=${STAGING_DIR_HOST}/usr|g' configure.ac
    sed -i 's|xml2_prefix=`xml2-config --prefix`|xml2_prefix=${STAGING_DIR_HOST}/usr|g' configure.ac
}

FILES_${PN} += "/usr/lib/GraphicsMagick-*/config" 
FILES_${PN}-dev += "/usr/share/GraphicsMagick-*" 
