FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://etc_protocols"

do_install:append () {
	install -m 0644 ${WORKDIR}/etc_protocols ${D}${sysconfdir}/protocols
}
