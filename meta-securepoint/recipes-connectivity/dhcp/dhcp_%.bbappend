FILESEXTRAPATHS:prepend := "${THISDIR}/files:${THISDIR}/dhcp:"
inherit runit

SRC_URI += "\
    file://dhclient-script \
    file://dhclient.conf \
    file://etc_sv_dhcpd4_run \
    file://etc_sv_dhcpd6_run \
    file://etc_sv_dhcpd4_down \
    file://etc_sv_dhcpd6_down \
    file://etc_sv_dhcprelay6_down \
    file://etc_sv_dhcprelay6_run \
    file://dhclient-support-raw-ip.patch \
    file://work_on_pppoe_interface_make_iaids_configurable.patch \
"

RUNIT_SERVICES = "dhcpd4 dhcpd6 dhcprelay6"

FILES:${PN}-server += " /etc/sv /var/service"
EXTRA_OECONF += "\
  --disable-static \
"

do_install:append () {
    install -d ${D}/etc/dhcp
    rm -f ${D}${base_sbindir}/dhclient-script
    install ${WORKDIR}/dhclient-script ${D}${base_sbindir}
    install ${WORKDIR}/dhclient.conf ${D}/etc/dhcp
}

RDEPENDS:${PN}-client = ""
