FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "radvd"

SRC_URI += "\
    file://etc_sv_radvd_run \
    file://etc_sv_radvd_down \
"

