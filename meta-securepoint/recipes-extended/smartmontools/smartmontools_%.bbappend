FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "smartd"

SRC_URI += "\
    file://etc_sv_smartd_run \
"

RDEPENDS:${PN}:remove = "mailx"
