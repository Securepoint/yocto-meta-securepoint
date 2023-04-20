DESCRIPTION = "lib and tools to parse outLook pst files, this recipe only create the shared libpst"
SECTION = "mail"
LICENSE = "GPLv2+"
#SRC_URI = "hg://hg.five-ten-sg.com/;protocol=http;module=libpst"
SRC_URI = "http://www.five-ten-sg.com/libpst/packages/${PN}-${PV}.tar.gz \
           file://0000-confugure-no-libgsf.patch \
"
SRC_URI[md5sum] = "cf047faf8c671a0a074a65767e7d2bd7"
SRC_URI[sha256sum] = "5f522606fb7b97d6e31bc2490dcce77b89ec77e12ade4af4551290f953483062"
LIC_FILES_CHKSUM = "file://COPYING;md5=4641e94ec96f98fabc56ff9cc48be14b"
PR = "r0"
inherit autotools pkgconfig 

EXTRA_OECONF = "\
	--disable-dii \
	--disable-python \
	--enable-libpst-shared \
"
EXTRA_OEMAKE = "\
    libpst.la \
"
do_compile:prepend(){
cd ${S}/src
}

do_install(){
cd ${S}/src 
oe_runmake 'DESTDIR=${D}' install-libpstincludeHEADERS install-libLTLIBRARIES 
}
