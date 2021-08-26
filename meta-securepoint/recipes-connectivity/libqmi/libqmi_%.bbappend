PACKAGES_prepend += " ${BPN}-utils ${BPN}-proxy "

FILES_${BPN}-utils = "\
       ${bindir}/qmi-network \
       ${bindir}/qmicli \
       ${bindir}/qmi-firmware-update"

FILES_${BPN}-proxy = "\
       ${libexecdir}/qmi-proxy"

