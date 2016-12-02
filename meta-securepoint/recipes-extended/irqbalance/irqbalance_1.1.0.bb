# Copyright (C) 2016 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
# vim:set ft=sh:

SRC_URI[md5sum] = "b15d975336080bcac4be0c1752d43cf3"
SRC_URI[sha256sum] = "91506e638b03bf27cf5da7dc250d58a753ce8a0288a20265fc7ff0266040706b"
SRC_URI = "https://github.com/Irqbalance/irqbalance/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz \
           file://fix-configure-libcap-ng.patch \
           file://etc_sv_irqbalance_run \
          "
SUMMARY = "IRQ allocation daemon"
DESCRIPTION = "A daemon to balance interrupts across multiple CPUs, \
which can lead to better performance and IO balance on SMP systems."

HOMEPAGE = "http://code.google.com/p/irqbalance/"
BUGTRACKER = "http://code.google.com/p/irqbalance/issues/list"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
		    file://irqbalance.c;beginline=6;endline=8;md5=b94e153694672307b503b1bc87dc9e24 \
		   "
DEPENDS = "glib-2.0"

inherit autotools pkgconfig runit

EXTRA_OECONF = "--program-transform-name= "
RUNIT_SERVICES = "${PN}"
PACKAGECONFIG ?= ""
PACKAGECONFIG[numa] = "--enable-numa,--disable-numa,numactl,"
PACKAGECONFIG[libcap-ng] = "--with-libcap-ng,--without-libcap-ng,libcap-ng,"
