DESCRIPTION="luautf8 is a lua utf8 library"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "lualibs"
DEPENDS = "lua5.1"
PR = "r0"

SRC_URI="git://github.com/starwing/luautf8.git;protocol=https;branch=master"
#SRCREV="${PV}"
SRCREV="c462d19620ca4c1ec29ce02f7b322ed7d74f4ab3"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "CC='${CC} ${CFLAGS} ${LDFLAGS}' LD='${CC}' DESTDIR=${D}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -fPIC -shared lutf8lib.c -o lua-utf8.so
}

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -m 555 -o root lua-utf8.so ${D}${libdir}/lua/5.1/
}

#INSANE_SKIP_${PN} += " dev-so "
FILES:${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES:${PN}-dev = "/usr/src"
FILES:${PN} += "${libdir}/lua/5.1/"





