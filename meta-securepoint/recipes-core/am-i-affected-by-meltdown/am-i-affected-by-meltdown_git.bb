SUMMARY = "Meltdown (CVE-2017-5754) checker"
HOMEPAGE = "https://meltdownattack.com/"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/raphaelsc/Am-I-affected-by-Meltdown.git"

S = "${WORKDIR}/git"
PR = "r1"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"

inherit autotools-brokensep

do_configure () {
	sed -i -e "s/^CC=/# CC=/" ${S}/Makefile
}

do_compile () {
	make -C ${S} CC='${CXX} -Wl,--hash-style=gnu'
}

do_install () {
	install -d ${D}/usr/bin
	install ${S}/meltdown-checker ${D}/usr/bin
}
