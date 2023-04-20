PACKAGES:prepend = " ${BPN}-utils ${BPN}-proxy "

FILES:${BPN}-utils = "\
       ${bindir}/qmi-network \
       ${bindir}/qmicli \
       ${bindir}/qmi-firmware-update"

FILES:${BPN}-proxy = "\
       ${libexecdir}/qmi-proxy"

