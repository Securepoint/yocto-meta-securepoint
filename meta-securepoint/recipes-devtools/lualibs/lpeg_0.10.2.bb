DESCRIPTION = "LPeg is a new pattern-matching library for Lua, based \
on Parsing Expression Grammars (PEGs)."
SUMMARY = "Parsing Expression Grammars For Lua"
SECTION = "lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://lpeg.html;beginline=1377;endline=1408;md5=8d10b429628704c1e04f0bd79c632b7b"
PR = "r0"

SRC_URI = "http://www.inf.puc-rio.br/~roberto/lpeg/lpeg-${PV}.tar.gz"
SRC_URI[md5sum] = "1402433f02e37ddadff04a3d4118b026"
SRC_URI[sha256sum] = "d1a7698e4bcd0ac305633774062d22b27300a41673c3d733ea9993244a64ea6f"

DEPENDS = "lua5.1"

inherit autotools-brokensep

EXTRA_OEMAKE += " PREFIX=/usr CC='${CC} ${CFLAGS} ${LDFLAGS} -Wl,--hash-style=gnu -fPIC' LD='${CC} -Wl,--hash-style=gnu'"

CLEANBROKEN = "1"
INSANE_SKIP:${PN} = "ldflags"

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -d ${D}/usr/share/lua/5.1/
    install -m 444 -o root lpeg.so ${D}${libdir}/lua/5.1/
    install -m 444 -o root re.lua ${D}/usr/share/lua/5.1/
}
FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN} += "${libdir}/lua/5.1/ /usr/share/lua/5.1/"
