DESCRIPTION = "Lua API for Tokyo Cabinet"
HOMEPAGE = "http://fallabs.com/tokyocabinet/luapkg/"
SECTION = "lualibs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"
PR = "r0"

SRC_URI = "http://fallabs.com/tokyocabinet/luapkg/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "e5f0b19ed2f559899edb804054552c40"
SRC_URI[sha256sum] = "0bfd631c5d44e373ea53eaa289db37a6a4b58b04f336f15c7e27e7f18cbd23af"

DEPENDS = "lua5.1 tokyocabinet"

do_configure(){
	:
}

do_compile(){
	${CC} ${CFLAGS} -c -DNDEBUG -D_GNU_SOURCE=1 -D_MYLUA -std=c99 -Wall -fPIC -fsigned-char -O2 ${S}/tokyocabinet.c
	${CC} ${CFLAGS} -Wl,--hash-style=gnu -std=c99 -Wall -fPIC -fsigned-char -O2 -shared -o tokyocabinet.so tokyocabinet.o -ltokyocabinet -lbz2 -lz -lpthread -lm -lc
}

do_install(){
	install -d ${D}${libdir}/lua/5.1/
	install -m 555 -o root tokyocabinet.so ${D}${libdir}/lua/5.1/
}

INSANE_SKIP:${PN} += " dev-so "
FILES:${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev = "/usr/src"
FILES:${PN} += "${libdir}/lua/5.1/"
