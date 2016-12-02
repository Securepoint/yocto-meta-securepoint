SUMMARY = "PDF generating library"
DESCRIPTION = "\
Haru is a free, cross platform, open-sourced software library for generating \
PDF. It supports the following features. \
\
   1. Generating PDF files with lines, text, images. \
   2. Outline, text annotation, link annotation. \
   3. Compressing document with deflate-decode. \
   4. Embedding PNG, Jpeg images. \
   5. Embedding Type1 font and TrueType font. \
   6. Creating encrypted PDF files. \
   7. Using various character set (ISO8859-1~16, MSCP1250~8, KOI8-R). \
   8. Supporting CJK fonts and encodings. \
"
HOMEPAGE = "http://libharu.org"
LICENSE = "Zlib"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://README;beginline=102;endline=121;md5=ee60a116ec6846aad5318080062bdc20"
PR = "r0"

SRC_URI = "\
    https://github.com/libharu/libharu/archive/RELEASE_2_3_0.tar.gz \
    file://libharu-install.patch;patch=1;pnum=1 \
    file://libharu-soversion.patch;patch=1;pnum=1 "
SRC_URI[md5sum] = "4f916aa49c3069b3a10850013c507460"
SRC_URI[sha256sum] = "8f9e68cc5d5f7d53d1bc61a1ed876add1faf4f91070dbc360d8b259f46d9a4d2"
S = "${WORKDIR}/libharu-RELEASE_2_3_0"

inherit cmake

