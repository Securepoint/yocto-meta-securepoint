FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "\
    file://rndc.key \
    file://dhcp.key \
    file://etc_sv_named_down \
    file://etc_sv_named_run \
"

inherit runit

RUNIT_SERVICES = "named"

PACKAGES_prepend += " ${PN}-lwresd ${PN}-utils-host ${PN}-utils-dig ${PN}-libs-export ${PN}-libs ${PN}-utils-rdnc ${PN}-utils-python "

PACKAGECONFIG = ""
EXTRA_OECONF += " --disable-full-report --enable-shared --disable-static --with-libtool "

RDEPENDS_${PN} = ""
RDEPENDS_${PN}-utils-python = "python-core"

do_install_append() {
    install -m 0644 ${WORKDIR}/*.key "${D}${sysconfdir}/bind/"
}

FILES_${PN} += " rndc.key dhcp.key"

FILES_${PN}-utils-rdnc += "\
    ${sbindir}/rndc* \
    "
FILES_${PN}-utils-python += "\
    ${sbindir}/dnssec-checkds \
    ${sbindir}/dnssec-coverage \
    "
FILES_${PN}-utils += "\
    ${bindir}/nsupdate \
    ${bindir}/delv \
    ${sbindir}/arpaname \
    ${sbindir}/ddns-confgen \
    ${sbindir}/tsig-keygen \
    ${sbindir}/generate-rndc-key.sh \
    ${sbindir}/genrandom \
    ${sbindir}/nsec3hash \
    ${sbindir}/dnssec* \
    ${sbindir}/isc-hmac-fixup \
    ${sbindir}/named-checkconf \
    ${sbindir}/named-checkzone \
    ${sbindir}/named-compilezone \
    ${sbindir}/named-journalprint \
    ${sbindir}/named-rrchecker \
    "

FILES_${PN}-lwresd      = " ${sbindir}/lwresd"
FILES_${PN}-utils-host  = " ${bindir}/host"
FILES_${PN}-utils-dig   = " ${bindir}/dig"
FILES_${PN}-libs-export = " ${libdir}/lib*-export.so.*"
FILES_${PN}-libs        = " ${libdir}/lib*.so.*"

FILES_${PN}       += " /etc/sv/named /var/service/named"
FILES_${PN}-dev   += " ${bindir}/isc-config.sh ${bindir}/bind9-config"

RPROVIDES_${PN}-lwresd      += " lwresd"
RPROVIDES_${PN}-utils-host  += " host"
RPROVIDES_${PN}-utils-dig   += " dig"
RPROVIDES_${PN}-libs        += " bind-libs"

