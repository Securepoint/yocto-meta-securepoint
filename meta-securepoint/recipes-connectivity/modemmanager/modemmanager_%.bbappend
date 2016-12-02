# modemmanager bbappend
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "modemmanager"
SRC_URI += "\
    file://etc_sv_modemmanager_run \
"

PACKAGES_prepend += " ${PN}-systemd "

FILES_${PN}-systemd = "/usr/share/dbus-1/system-services/*"
