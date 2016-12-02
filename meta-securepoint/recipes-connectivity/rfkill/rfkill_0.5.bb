DESCRIPTION = "rfkill CLI utility"
HOMEPAGE = "http://linuxwireless.org/en/users/Documentation/rfkill"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c6036d0eb7edbfced28c4160e5d3fa94"
PR = "r1"

SRC_URI = "https://www.kernel.org/pub/software/network/${PN}/${PN}-${PV}.tar.bz2 \
           file://0001-rfkill-makefile-don-t-use-t-the-OE-install-wrapper-d.patch"

SRC_URI[md5sum] = "b957713a6cfbcd8ac0e94420aeddcf1a"
SRC_URI[sha256sum] = "3e160cca504a53679f2b3254f31c53a4fb38a021bc50fed8eb57a436d33dfa07"

do_compile() {
    echo "const char rfkill_version[] = \"${PV}\";" > version.c
    oe_runmake
}
do_install() {
    oe_runmake DESTDIR=${D} install
}

inherit update-alternatives

ALTERNATIVE_${PN} = "rfkill"
ALTERNATIVE_PRIORITY = "60"
ALTERNATIVE_LINK_NAME[rfkill] = "${sbindir}/rfkill"

