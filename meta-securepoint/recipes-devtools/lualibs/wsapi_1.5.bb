DESCRIPTION = "Cosmo is a "safe templates" engine.  It allows you to fill nested \
templates, providing many of the advantages of Turing-complete \
template engines, without without the downside of allowing arbitrary \
code in the templates."
SECTION = "lua"
LICENSE = "CLOSED"
PR = "r0"


SRC_URI = "git://github.com/keplerproject/wsapi.git;protocol=http \
"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

DEPENDS = "lua5.1"

inherit autotools-brokensep
CLEANBROKEN = "1"

EXTRA_OEMAKE += " PREFIX=/usr "
CFLAGS += " -fPIC"

do_configure(){
cat <<-EOF > config
    LIB_OPTION= -shared
    BIN_DIR=${D}/${bindir}
    LUA_DIR=${D}/usr/share/lua/5.1
    LUA_LIBDIR=${D}/${libdir}/
EOF
}

do_install(){
     install -d ${D}/${bindir}
     oe_runmake install
     install -d ${D}/${libdir}/lua/5.1
     install -m 555 src/fastcgi/lfcgi.so ${D}/${libdir}/lua/5.1/
}
FILES_${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES_${PN} += "${datadir}/lua/5.1 ${libdir}/lua/5.1/lfcgi.so"
