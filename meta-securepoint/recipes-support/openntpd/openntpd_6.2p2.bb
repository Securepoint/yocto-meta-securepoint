SUMMARY = "port of OpenBSD's native ntpd"
DESCRIPTION = "\
This is a port of OpenBSD's native ntpd to other Unix flavours adding \
autoconf support and the necessary compatibility layer. It is strongly \
influenced by, and based in part on, the OpenSSH Portable project."
HOMEPAGE = "http://www.openntpd.org/"
LICENSE = "ISC"
SECTION = "console/network"
LIC_FILES_CHKSUM = "file://COPYING;md5=fe922aad2b6ad1c359cf2adfdaaab1b6"
DEPENDS = "openssl byacc-native"

PR = "r0"
SRC_URI = "\
    https://ftp.spline.de/pub/OpenBSD/OpenNTPD/openntpd-${PV}.tar.gz \
    file://etc_sv_ntpd_run \
    file://etc_sv_ntpd_log_run \
"
#file://set_and_quit.patch 

SRC_URI[md5sum] = "e504f2f662413cbaa5d65ee3634c2f43"
SRC_URI[sha256sum] = "5808a4137b008a4db20907e1a482f474734b120f254e3c5feb90db15e0820fb2"

inherit autotools-brokensep runit useradd

RUNIT_SERVICES = "ntpd"

USERADD_PACKAGES += "${PN}"
USERADD_PARAM_${PN} = "\
-r -d /run/ntp -s /bin/false _ntp;"

do_install_append() {
	#avoid rpm conflicts
	rm -rf ${D}/var/run
}

FILES_${PN} += "/run "
