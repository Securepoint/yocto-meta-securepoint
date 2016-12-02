# tiny init for SP-Base
# overwrite Vars from poky-tiny

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "file://init \
	  "

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	install -d ${D}/sbin
	install -m 0755 ${WORKDIR}/init ${D}/sbin/
}

FILES_${PN} = "/sbin/init"
