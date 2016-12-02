DESCRIPTION = "A library for mapping JSON data to QVariant objects."

HOMEPAGE = "http://qjson.sourceforge.net/"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://COPYING.lib;md5=3511d726d09144c8590aba4623ca2e9f"

SECTION = "qt"

SRC_URI = "git://github.com/flavio/qjson.git;protocol=http"
SRCREV = "0.8.1"


S = "${WORKDIR}/git"
inherit cmake qt4e 

FILES_${PN}-dev += "${libdir}/cmake/qjson"

#EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_QMAKEVARS += "INSTALL_ROOT=${D}"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

EXTRA_OECMAKE = "-DQT_MKSPECS_DIR=${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs"

do_configure() {
	# Ensure we get the cmake configure and not qmake
	cmake_do_configure
}


do_install() {
	oe_runmake PREFIX=/usr DESTDIR=${D} install
	#install -d ${D}/usr/lib/
	#install -m 0755 ${S}/lib/libqjson.so* ${D}/usr/lib/
}

