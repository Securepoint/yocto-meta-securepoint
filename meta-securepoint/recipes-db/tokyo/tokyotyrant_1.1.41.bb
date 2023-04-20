DESCRIPTION = "Tokyo Tyrant is a package of network interface to the DBM called Tokyo Cabinet. Though the DBM has high performance, you might bother in case that multiple processes share the same database, or remote processes access the database. Thus, Tokyo Tyrant is provided for concurrent and remote connections to Tokyo Cabinet. It is composed of the server process managing a database and its access library for client applications."
SECTION = "database"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "http://fallabs.com/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "a47e58897bd1cbbac173d5a66cc32ae3"
SRC_URI[sha256sum] = "42af70fb9f2795d4e05c3e37941ce392a9eaafc991e230c48115370f6d64b88f"


DEPENDS = "tokyocabinet"

inherit gettext autotools-brokensep

EXTRA_OEMAKE += " PREFIX=/usr "

do_configure:prepend(){
    sed -i 's/^PATH=.*//g' configure.in
    sed -i 's/^CPATH=.*//g' configure.in
    sed -i 's/^LIBRARY_PATH=.*//g' configure.in
    sed -i 's/^LD_LIBRARY_PATH=.*//g' configure.in
    sed -i 's/^PKG_CONFIG_PATH=.*//g' configure.in
    sed -i -e "s:^MYCPPFLAGS=\"-I.*:MYCPPFLAGS=\"-I. -I${STAGING_INCDIR}\":" configure.in
    sed -i 's/^MYLDFLAGS=.*/MYLDFLAGS=\"-L\.\"/g' configure.in
    sed -i -e "s:pkg-config --variable=includedir tokyocabinet::" configure.in
    sed -i -e "s:pkg-config --variable=libdir tokyocabinet::" configure.in
    rm configure
    autoconf
}

FILES:${PN} += " /usr/lib/tts*"
FILES:${PN}-dev += " /usr/share/tokyotyrant/doc"
