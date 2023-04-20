# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://etc_sv_l2tpd_down file://etc_sv_l2tpd_run"
inherit runit

RUNIT_SERVICES = "l2tpd"
