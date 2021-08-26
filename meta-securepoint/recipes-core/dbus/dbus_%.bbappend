# busybox bbappend
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "dbus"

SRC_URI += "\
    file://etc_sv_dbus_run \
"

PACKAGES_prepend += " ${PN}-tools "
PACKAGES_prepend += " ${PN}-dbussend "

FILES_${PN}-tools = "\
	${bindir}/dbus-send \
	${bindir}/dbus-monitor \
	${bindir}/dbus-test-tool \
	${bindir}/dbus-cleanup-sockets \
"

FILES_${PN}-dbussend = "\
	${bindir}/dbus-send \
"
