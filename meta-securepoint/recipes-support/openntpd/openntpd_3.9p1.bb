SUMMARY = "port of OpenBSD's native ntpd"
DESCRIPTION = "\
This is a port of OpenBSD's native ntpd to other Unix flavours adding \
autoconf support and the necessary compatibility layer. It is strongly \
influenced by, and based in part on, the OpenSSH Portable project."
HOMEPAGE = "http://www.openntpd.org/"
LICENSE = "ISC"
SECTION = "console/network"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4b4f5158007cc97e6b0e2325bb99854a"
DEPENDS = "openssl"

PR = "r0"
SRC_URI = "\
    http://openbsd.cs.fau.de/pub/OpenBSD/OpenNTPD/openntpd-3.9p1.tar.gz \
    file://etc_sv_ntpd_run \
    file://etc_sv_ntpd_log_run \
    file://set_and_quit.patch \
"

SRC_URI[md5sum] = "afc34175f38d08867c1403d9008600b3"
SRC_URI[sha256sum] = "83dd7c1e8ec8b4567afe49af539271b5a73562fb7a3ca51df73eccba89ec8c49"

inherit autotools runit useradd

EXTRA_OECONF += " --disable-strip"
RUNIT_SERVICES = "ntpd"

USERADD_PACKAGES += "${PN}"
USERADD_PARAM_${PN} = "\
-r -d /run/ntp -s /bin/false _ntp;"

