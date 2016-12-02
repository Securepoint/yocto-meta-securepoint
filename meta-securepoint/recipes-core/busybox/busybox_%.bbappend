# busybox bbappend
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
  file://mount.cfg \
  file://basic.cfg \
  file://crond.cfg \
  file://etc_sv_crond_run \
  file://swap.cfg \
  file://console.cfg"
#TODO:  file://interfaces-support-source.patch 

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'pam', 'file://pam.cfg', '', d)}"
SRC_URI += '${@base_conditional("PACKAGECONFIG", "runit", "", "file://runit.cfg", d)}'
#enable syslog in busybox if syslog-ng is not installed
SRC_URI += '${@base_conditional("PACKAGECONFIG", "syslog-ng", "", "file://log.cfg", d)}'

SRC_URI += "\
    file://etc_sv_crond_run \
"


# add a "busybox-crond" package

inherit useradd runit

PACKAGES_prepend += " ${PN}-crond"

USERADD_PACKAGES += "${PN}-crond"
RUNIT_SERVICES += "crond"

USERADD_PARAM_${PN}-crond = "\
-r -d /var/spool/cron -g crontab -s /bin/false cron;\
"
GROUPADD_PARAM_${PN}-crond = "\
-r -g 202 -f crontab;\
"

FILES_${PN}-crond = "/etc/sv/crond"
