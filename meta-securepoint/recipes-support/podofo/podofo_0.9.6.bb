ESCRIPTION = "PoDoFo is a library to work with the PDF file format. The name comes from the first letter of PDF (Portable Document Format)."
HOMEPAGE = "http://podofo.sourceforge.net/"
SECTION = "devel"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "http://prdownloads.sourceforge.net/${PN}/${PN}-${PV}.tar.gz \
      file://fix_memleak_invalid_xref.patch \
"

SRC_URI[md5sum] = "46336fc4c4ce4be814bb5fbb4d918334"
SRC_URI[sha256sum] = "e9163650955ab8e4b9532e7aa43b841bac45701f7b0f9b793a98c8ca3ef14072"

DEPENDS = "zlib openssl freetype fontconfig libpng tiff jpeg"

inherit cmake pkgconfig

EXTRA_OECMAKE += " \
  -DPODOFO_BUILD_SHARED=TRUE \
  -DPODOFO_BUILD_STATIC=FALSE \
  -DPODOFO_BUILD_LIB_ONLY=TRUE \
"
