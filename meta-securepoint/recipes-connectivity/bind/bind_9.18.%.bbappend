FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://rndc.key \
    file://dhcp.key \
    file://etc_sv_named_down \
    file://etc_sv_named_run \
    file://0001-nobuiltwith.patch \
"
inherit runit

RUNIT_SERVICES = "named"

PACKAGES:prepend = "${PN}-lwresd ${PN}-utils-host ${PN}-utils-dig ${PN}-utils-rndc "

PACKAGECONFIG = "dns-over-http"
EXTRA_OECONF += " --disable-full-report --disable-static --enable-shared --with-tuning=small "

RDEPENDS:${PN} = ""

do_install:append() {
    install -m 0644 ${WORKDIR}/*.key "${D}${sysconfdir}/bind/"
}

FILES:${PN} += " rndc.key dhcp.key"

FILES:${PN}-utils-rndc += "\
    ${sbindir}/rndc* \
    ${sbindir}/generate-rndc-key.sh \
    "

FILES:${PN}-utils += "\
    ${bindir}/nsupdate \
    ${bindir}/delv \
    ${bindir}/arpaname \
    ${bindir}/named-rrchecker \
    ${sbindir}/ddns-confgen \
    ${sbindir}/tsig-keygen \
    ${bindir}/genrandom \
    ${bindir}/nsec3hash \
    ${bindir}/dnssec* \
    ${bindir}/isc-hmac-fixup \
    ${bindir}/named-checkconf \
    ${bindir}/named-checkzone \
    ${bindir}/named-compilezone \
    ${bindir}/named-journalprint \
    "

FILES:${PN}-lwresd      = " ${sbindir}/lwresd"
FILES:${PN}-utils-host  = " ${bindir}/host"
FILES:${PN}-utils-dig   = " ${bindir}/dig"

FILES:${PN}       += " /etc/sv/named /var/service/named"
FILES:${PN}-dev   += " ${bindir}/isc-config.sh ${bindir}/bind9-config"

RPROVIDES:${PN}-lwresd      += " lwresd"
RPROVIDES:${PN}-utils-host  += " host"
RPROVIDES:${PN}-utils-dig   += " dig"

