SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://wireless.kernel.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "libnl"

inherit pkgconfig

SRC_URI = "http://www.kernel.org/pub/software/network/iw/${BP}.tar.gz"
SRC_URI[md5sum] = "a0a17ab1b20132c716bba9a4f9974ba6"
SRC_URI[sha256sum] = "36fc7592dde7bec934df83cd53ef1f2c08ceec5cd58d07eb8f71cc6e8464013c"

EXTRA_OEMAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}
