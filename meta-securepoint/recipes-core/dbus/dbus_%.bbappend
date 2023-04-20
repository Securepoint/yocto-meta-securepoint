# busybox bbappend
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "dbus"

SRC_URI += "\
    file://etc_sv_dbus_run \
"

PACKAGES:prepend = " ${PN}-dbussend "

RDEPENDS:${PN}:remove = "${PN}-tools"
FILES:${PN}-dbussend = "\
	${bindir}/dbus-send \
"
