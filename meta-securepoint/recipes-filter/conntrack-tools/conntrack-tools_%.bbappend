# conntrack-tools bbappend
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES += "conntrackd"

SRC_URI += "\
    file://etc_sv_conntrackd_run \
    file://etc_sv_conntrackd_down \
    file://etc_sv_conntrackd_finish \
    file://conntrack-tools-1.4.0-config.patch \
"
