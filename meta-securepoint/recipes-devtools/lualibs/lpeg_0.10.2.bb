DESCRIPTION = "Cosmo is a "safe templates" engine.  It allows you to fill nested \
templates, providing many of the advantages of Turing-complete \
template engines, without without the downside of allowing arbitrary \
code in the templates."
SECTION = "lua"
LICENSE = "CLOSED"
PR = "r0"

SRC_URI = "http://www.inf.puc-rio.br/~roberto/lpeg/lpeg-${PV}.tar.gz"
SRC_URI[md5sum] = "1402433f02e37ddadff04a3d4118b026"
SRC_URI[sha256sum] = "d1a7698e4bcd0ac305633774062d22b27300a41673c3d733ea9993244a64ea6f"

DEPENDS = "lua5.1"

inherit autotools-brokensep

EXTRA_OEMAKE += " PREFIX=/usr LD='${LD}' CC='${CC} -fPIC'"

CLEANBROKEN = "1"

do_install(){
    install -d ${D}${libdir}/lua/5.1/
    install -d ${D}/usr/share/lua/5.1/
    install -m 444 -o root lpeg.so ${D}${libdir}/lua/5.1/
    install -m 444 -o root re.lua ${D}/usr/share/lua/5.1/
}
FILES_${PN}-dbg = "${libdir}/lua/5.1/.debug"
FILES_${PN} += "${libdir}/lua/5.1/ /usr/share/lua/5.1/"
