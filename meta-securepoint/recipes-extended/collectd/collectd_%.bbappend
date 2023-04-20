# Copyright (C) 2019 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS:prepend := "${THISDIR}/collectd:"
DEPENDS += " dbus"

SRC_URI:append = "\
           file://collectd_no_log_cons.patch \
           file://0001-dbus-plugin-Started-working-on-a-dbus-plugin-for-col.patch \
           file://etc_sv_${PN}_run \
           file://etc_sv_${PN}_down \
"

inherit runit

RUNIT_SERVICES = "${PN}"
EXTRA_OECONF += " --enable-dbus "

PACKAGES:prepend = " ${PN}-utils "
FILES:${PN}-utils = "/usr/sbin/collectdmon /usr/bin/collect*"
