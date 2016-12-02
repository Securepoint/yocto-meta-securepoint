FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG += " dbi"

SRC_URI += "file://libdbi_no_host_path.patch \
            file://syslog-ng-silence.patch \
            file://syslog-ng.conf \
            file://messages.sql \
            file://etc_sv_syslog_run \
"

inherit runit

RUNIT_SERVICES = "syslog"

do_install_append () {
    cp -f ${WORKDIR}/syslog-ng.conf ${D}/etc/syslog-ng/
    cp -f ${WORKDIR}/messages.sql ${D}/etc/syslog-ng/
}
