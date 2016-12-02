SUMMARY = "GNU Freefont Fonts"
HOMEPAGE = "http://ftp.gnu.org/gnu/freefont"
SECTION = "x11/fonts"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
RDEPENDS_${PN} = "fontconfig-utils"

inherit fontcache

FONT_PACKAGES = "${PN}-sans ${PN}-mono ${PN}-serif"

SRC_URI = "http://ftp.gnu.org/gnu/freefont/freefont-ttf-${PV}.zip \
           file://69-gnu-free-mono.conf \
           file://69-gnu-free-sans.conf \
           file://69-gnu-free-serif.conf \
"

SRC_URI[md5sum] = "879b76d2e3c8003d567b555743f39154"
SRC_URI[sha256sum] = "7c85baf1bf82a1a1845d1322112bc6ca982221b484e3b3925022e25b5cae89af"

S = "${WORKDIR}/freefont-${PV}"

do_install () {
	install -d ${D}${datadir}/fonts/gnu-free/
	for i in *.ttf; do
		install -m 0644 $i ${D}${datadir}/fonts/gnu-free/${i}
	done

	install -d ${D}${sysconfdir}/fonts/conf.d/
	install -m 0644 ${WORKDIR}/69-*.conf ${D}${sysconfdir}/fonts/conf.d/

	install -d ${D}${datadir}/doc/${BPN}/
	install -m 0644 INSTALL README USAGE ChangeLog COPYING CREDITS TROUBLESHOOTING AUTHORS ${D}${datadir}/doc/${BPN}/
}

PACKAGES = "gnu-free-sans-fonts gnu-free-mono-fonts gnu-free-serif-fonts gnu-free-fonts-doc"
FILES_gnu-free-sans-fonts  = "${sysconfdir}/fonts/conf.d/69-gnu-free-sans.conf ${datadir}/fonts/gnu-free/FreeSans*.ttf"
FILES_gnu-free-mono-fonts  = "${sysconfdir}/fonts/conf.d/69-gnu-free-mono.conf ${datadir}/fonts/gnu-free/FreeMono*.ttf"
FILES_gnu-free-serif-fonts = "${sysconfdir}/fonts/conf.d/69-gnu-free-serif.conf ${datadir}/fonts/gnu-free/FreeSerif*.ttf"
FILES_gnu-free-fonts-doc   = "${datadir}/doc/${BPN}"

