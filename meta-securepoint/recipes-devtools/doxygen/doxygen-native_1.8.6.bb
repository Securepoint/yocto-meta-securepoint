DESCRIPTION = "Utilities for generating documentation from source code"
HOMEPAGE = "http://www.stack.nl/~dimitri/doxygen/index.html"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b380c86cea229fa42b9e543fc491f5eb"
DEPENDS = "flex-native bison-native"
DEPENDS +=" virtual/libiconv-native"

# In case we want to -- for whatever strange reason -- to make doxygen available at the target system
# we could start with the following lines to provide target and native version
#BBCLASSEXTEND = "native"
#DEPENDS_append_class-native=" virtual/libiconv-native"

SRC_URI = "http://ftp.stack.nl/pub/users/dimitri/doxygen-${PV}.src.tar.gz"

SRC_URI[md5sum] = "9385dc52f0627875f8fa758e754ec674"
SRC_URI[sha256sum] = "6a718625f0c0c1eb3dee78ec1f83409b49e790f4c6c47fd44cd51cb92695535f"


EXTRA_OECONF = "--prefix ${prefix}"

inherit native

do_configure () {
    ./configure ${EXTRA_OECONF}
}

do_install() {
    oe_runmake install DESTDIR=${D} MAN1DIR=share/man/man1
}

