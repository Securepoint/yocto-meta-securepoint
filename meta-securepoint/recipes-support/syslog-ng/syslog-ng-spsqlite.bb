DESCRIPTION = "scripts to enable logging to sqlite db with syslog-ng"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"
DEPENDS = "syslog-ng"
RDEPENDS_${PN} = "libdbd-sqlite3 sqlite3"
PR = "r0"

SRC_URI += " \
            file://messages.sql \
            file://syslog.sh \
            file://09-syslog \
            file://syslog-ng-spsqlite.conf \
"

do_configure(){
    :
}
do_compile(){
    :
}

do_install() {
    install -d ${D}/etc/syslog-ng/conf.d/
    cp -f ${WORKDIR}/messages.sql ${D}/etc/syslog-ng/
    cp -f ${WORKDIR}/syslog-ng-spsqlite.conf ${D}/etc/syslog-ng/conf.d/00-syslog-ng-spsqlite.conf
    install -d ${D}/${sbindir}
    install -d ${D}/${sysconfdir}/runit/1.d
    install -m 0755 ${WORKDIR}/syslog.sh ${D}/${sbindir}/syslog
    install -m 0755 ${WORKDIR}/09-syslog ${D}/${sysconfdir}/runit/1.d/
}
