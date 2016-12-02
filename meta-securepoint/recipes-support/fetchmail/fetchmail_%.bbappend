# fetchmail bbappend
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES += "fetchmail"

SRC_URI += "\
    file://etc_sv_fetchmail_run \
    file://etc_sv_fetchmail_down \
"

