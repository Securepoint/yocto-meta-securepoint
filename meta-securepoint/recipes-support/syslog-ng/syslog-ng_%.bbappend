FILESEXTRAPATHS:prepend := "${THISDIR}/files:${THISDIR}/${PN}-${PV}:"

PACKAGECONFIG = "ipv6 dbi http json"

SRC_URI += "\
            file://libdbi_no_host_path.patch \
            file://etc_sv_syslog_run \
            file://syslog-ng.conf \
"

inherit runit useradd

USERADD_PACKAGES += "${PN}"
GROUPADD_PARAM:${PN} = "\
-r -f syslog;\
"

RUNIT_SERVICES = "syslog"

FILES:${PN} += "${sbindir}/sl /usr/share/zoneinfo/localtime"

do_install:append(){
    install -d ${D}/etc/syslog-ng/conf.d
    install -d ${D}/usr/share/zoneinfo
    ln -s /etc/localtime ${D}/usr/share/zoneinfo/localtime
    install ${WORKDIR}/syslog-ng.conf ${D}${sysconfdir}/${BPN}/${BPN}.conf
}
