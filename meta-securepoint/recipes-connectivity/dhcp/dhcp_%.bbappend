FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/dhcp:"
inherit runit

LICENSE = "MPL-2.0"
PV = "4.4.2"
SRC_URI[md5sum] = "2afdaf8498dc1edaf3012efdd589b3e1"
SRC_URI[sha256sum] = "1a7ccd64a16e5e68f7b5e0f527fd07240a2892ea53fe245620f4f5f607004521"

SRC_URI_remove = "\
    file://0004-Fix-out-of-tree-builds.patch \
    file://0001-master-Added-includes-of-new-BIND9-compatibility-hea.patch \
"

SRC_URI += "\
    file://dhclient-script \
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

FILES_${PN}-server += " /etc/sv /var/service"
EXTRA_OECONF += "\
  --disable-static \
"

do_install_append () {
    cp -f ${WORKDIR}/dhclient-script ${D}${base_sbindir}
}

RDEPENDS_${PN}-client = ""
