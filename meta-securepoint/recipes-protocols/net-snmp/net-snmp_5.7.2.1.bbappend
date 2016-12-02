FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
  file://etc_sv_snmpd_run \
  file://etc_sv_snmpd_down \
  file://net-snmp-5.7.2_utmp.patch \
"

EXTRA_OECONF += " --enable-ipv6"

inherit runit

RUNIT_SERVICES = "snmpd"
PACKAGES_prepend = " ${PN}-clients-perl ${PN}-mib2c "

FILES_${PN}-server-snmpd += " /etc/sv/snmpd /var/service/snmpd"
FILES_${PN}-mib2c = "/usr/share/snmp/mib2c-data /usr/bin/mib2c*"
FILES_${PN}-clients-perl = "\
    /usr/bin/fixproc \
    /usr/bin/ipf-mod.pl \
    /usr/bin/net-snmp-cert \
    /usr/bin/snmp-bridge-mib \
    /usr/bin/snmpcheck \
    /usr/bin/snmpconf \
    /usr/bin/tkmib \
    /usr/bin/traptoemail"
