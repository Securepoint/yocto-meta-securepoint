
PACKAGES_prepend = " atop-atop-bin atop-atopconvert-bin atop-daily atop-cron "

FILES_atop-atop-bin = "${bindir}/atop ${bindir}/atop-${PV}"
FILES_atop-atopconvert-bin = "${bindir}/atopconvert"
FILES_atop-daily = "/usr/share/atop/atop.daily /var/volatile/log/atop/"
FILES_atop-cron = "${sysconfdir}/cron.d"

RDEPENDS_atop-daily = "atop-atop-bin"
RDEPENDS_atop-cron = "atop-daily atop-atop-bin"
