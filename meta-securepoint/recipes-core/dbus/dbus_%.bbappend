# busybox bbappend
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "dbus"

SRC_URI += "\
    file://etc_sv_dbus_run \
"

