FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
inherit runit

# fix-external-bind.patch is included by the main recipe!
SRC_URI += "\
    file://dhclient-script \
    file://enable_gentle_shutdown.patch \
    file://etc_sv_dhcpd4_run \
    file://etc_sv_dhcpd6_run \
    file://etc_sv_dhcpd4_down \
    file://etc_sv_dhcpd6_down \
"

RUNIT_SERVICES = "dhcpd4 dhcpd6"

FILES_${PN}-server += " /etc/sv /var/service"

do_install_append () {
    cp -f ${WORKDIR}/dhclient-script ${D}${base_sbindir}
}
