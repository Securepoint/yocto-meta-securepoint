# vim:ft=sh:
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://60-persistent-storage.rules \
"

PR = "r1"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-r kvm"

do_install:append() {
	install -m 644 ${WORKDIR}/60-persistent-storage.rules ${D}/lib/udev/rules.d/
}
