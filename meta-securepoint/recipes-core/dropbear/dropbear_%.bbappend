FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "\
    file://etc_sv_sshd_run \
    file://etc_sv_sshd_log_run \
    file://etc_sv_sshd_down \
"

inherit runit

RUNIT_SERVICES = "sshd"
