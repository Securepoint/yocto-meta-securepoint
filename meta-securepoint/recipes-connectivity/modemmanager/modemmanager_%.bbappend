# modemmanager bbappend
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES = "modemmanager"
SRC_URI += "\
    file://etc_sv_modemmanager_run \
    file://77-mm-quectel.rules \
    file://80-mm-securepoint.rules \
"

PACKAGECONFIG = "mbim qmi"

do_install:append() {
    install -d ${D}/etc/udev/rules.d
    install -m 0600 ${WORKDIR}/77-mm-quectel.rules ${D}/etc/udev/rules.d
    install -m 0600 ${WORKDIR}/80-mm-securepoint.rules ${D}/etc/udev/rules.d
    # don't let dbus start this service, it is managed by runit
    rm -f ${D}/usr/share/dbus-1/system-services/org.freedesktop.ModemManager1.service
}

RDEPENDS:${PN} += "libqmi-proxy libmbim-proxy"
