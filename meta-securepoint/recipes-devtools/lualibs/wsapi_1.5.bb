DESCRIPTION = "WSAPI is an API that abstracts the web server from Lua web \
applications. By coding against WSAPI your application can run on any of \
the supported servers and interfaces (currently CGI, FastCGI and Xavante, \
on Windows and UNIX-based systems)."
SUMMARY = "WSAPI is an API that abstracts the web server from Lua web applications."
SECTION = "lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/us/license.html;md5=7c303fa69c61ad25e6a04ef328d650f0"
PR = "r1"


SRC_URI = "git://github.com/keplerproject/wsapi.git;protocol=http \
"
#SRCREV = "v${PV}"
SRCREV = "ee910fb6428631cea430216138d0e34ef93022ad"
S = "${WORKDIR}/git"

DEPENDS = "lua5.1 fcgi"

inherit autotools-brokensep
CLEANBROKEN = "1"
INSANE_SKIP:${PN} = "ldflags"

EXTRA_OEMAKE += " PREFIX=/usr LD='${LD} -Wl,--hash-style=gnu' CC='${CC} -fPIC -Wl,--hash-style=gnu'"

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
FILES:${PN}-dbg += "${libdir}/lua/5.1/.debug"
FILES:${PN} += "${datadir}/lua/5.1 ${libdir}/lua/5.1/lfcgi.so"
