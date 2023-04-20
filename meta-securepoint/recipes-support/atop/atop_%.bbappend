
PACKAGES:prepend = " atop-atop-bin atop-atopconvert-bin atop-daily atop-cron "

FILES:atop-atop-bin = "${bindir}/atop ${bindir}/atop-${PV}"
FILES:atop-atopconvert-bin = "${bindir}/atopconvert"
FILES:atop-daily = "/usr/share/atop/atop.daily /var/volatile/log/atop/"
FILES:atop-cron = "${sysconfdir}/cron.d"

RDEPENDS:atop-daily = "atop-atop-bin"
RDEPENDS:atop-cron = "atop-daily atop-atop-bin"
