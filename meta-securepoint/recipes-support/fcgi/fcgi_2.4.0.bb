LICENSE = "OML"
DESCRIPTION = "Fast CGI backend (web server to CGI handler) library"
PR = "r0"
LIC_FILES_CHKSUM = "file://LICENSE.TERMS;md5=e3aacac3a647af6e7e31f181cda0a06a"

SRC_URI = "https://github.com/FastCGI-Archives/fcgi2/archive/${PV}.tar.gz \
        file://link-against-math.patch;patch=1 \
        file://cstdio.patch;patch=1 "
SRC_URI[md5sum] = "4d1de355988b3a137802f731520381f5"
SRC_URI[sha256sum] = "b152df7f301847b2bcf8b65672bfb212ed0cee640a5917aecaa36baa05f6f283"

inherit autotools-brokensep pkgconfig

S = "${WORKDIR}/fcgi2-${PV}"

PARALLEL_MAKE=""

PACKAGES += "${PN}-libfcgi ${PN}-libfcgi++"

FILES:${PN} = "/usr/bin/cgi-fcgi"
FILES:${PN}-libfcgi = "/usr/lib/libfcgi.so.*"
FILES:${PN}-libfcgi++ = "/usr/lib/libfcgi++.so.*"


do_configure:prepend(){
    cd ${S}
    touch NEWS README AUTHORS ChangeLog
}

BBCLASSEXTEND = "native"
