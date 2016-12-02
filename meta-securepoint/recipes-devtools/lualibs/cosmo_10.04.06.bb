DESCRIPTION = "Cosmo is a "safe templates" engine.  It allows you to fill nested \
templates, providing many of the advantages of Turing-complete \
template engines, without without the downside of allowing arbitrary \
code in the templates."
SECTION = "lua"
LICENSE = "CLOSED"
PR = "r0"


SRC_URI = "git://github.com/mascarenhas/cosmo.git;protocol=http \
"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

DEPENDS = "lua5.1"
RDEPENDS_${PN} += "lpeg"

inherit autotools-brokensep

EXTRA_OEMAKE += " PREFIX=/usr all"
EXTRA_OECONF += " lua "

do_configure(){
    chmod +x configure
    ./configure lua
}

do_install(){
    local luadir=${D}/usr/share/lua/5.1
    install -d ${luadir}/cosmo
    install -m 444 src/cosmo.lua ${luadir}
    install -m 444 src/cosmo/*.lua ${luadir}/cosmo
}
FILES_${PN} += "/usr/share/lua/5.1"
