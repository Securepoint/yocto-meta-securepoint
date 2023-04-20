SUMMARY = "port of OpenBSD's native ntpd"
DESCRIPTION = "\
This is a port of OpenBSD's native ntpd to other Unix flavours adding \
autoconf support and the necessary compatibility layer. It is strongly \
influenced by, and based in part on, the OpenSSH Portable project."
HOMEPAGE = "http://www.openntpd.org/"
LICENSE = "ISC"
SECTION = "console/network"
LIC_FILES_CHKSUM = "file://COPYING;md5=fe922aad2b6ad1c359cf2adfdaaab1b6"
DEPENDS = "git-native openssl byacc-native"

PR = "r0"
SRC_URI = "\
    gitsm://github.com/openntpd-portable/openntpd-portable;protocol=https;branch=master \
    file://etc_sv_ntpd_run \
    file://etc_sv_ntpd_log_run \
"

SRCREV = "ca88d7bf4d2d6d3311c6ee5e2662148502b96156"
S = "${WORKDIR}/git"

inherit autotools-brokensep runit useradd

RUNIT_SERVICES = "ntpd"

USERADD_PACKAGES += "${PN}"
USERADD_PARAM:${PN} = "\
-r -d /run/ntp -s /bin/false _ntp;"

do_configure:prepend() {
    export GIT_SSL_NO_VERIFY=true
    ./autogen.sh
}

do_install:append() {
	# avoid rpm conflicts
	rm -rf ${D}/var/run
}

FILES:${PN} += "/run "
