SUMMARY = "seedrng"
DESCRIPTION = "SeedRNG is a simple program made for seeding the Linux kernel random number generator from seed files. The program takes no arguments, must be run as root, and always attempts to do something useful."
HOMEPAGE = "https://git.zx2c4.com/seedrng/about/"
SECTION = "System Environment/Kernel"

SRC_URI = "gitsm://git.zx2c4.com/seedrng;protocol=https;branch=master"

S = "${WORKDIR}/git"
SRCREV = "f68fee47892fc528847682afc003097dcee9a765"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4d96a2a432d91a4aeeeba3ea840174e6"

DEPENDS = ""

EXTRA_OEMAKE = "LOCALSTATEDIR='/data'"

do_compile () {
    oe_runmake 'CC=${CC}'
}

do_install() {
    oe_runmake PREFIX=/usr DESTDIR=${D} install
}
