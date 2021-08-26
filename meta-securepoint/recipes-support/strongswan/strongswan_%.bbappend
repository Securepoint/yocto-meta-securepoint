FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

require strongswan-version.inc

inherit runit pkgconfig

RUNIT_SERVICES = "ipsec"
DEPENDS += " libpam"

PACKAGECONFIG = "charon openssl stroke swanctl"

EXTRA_OECONF = "\
    --enable-af-alg \
    --enable-openssl \
    --enable-eap-gtc \
    --enable-xauth-pam \
"

FILES_${PN}-dbg += " ${sbindir}/.debug"

SRC_URI += "\
    file://etc_sv_ipsec_down \
    file://etc_sv_ipsec_run \
    file://libressl_5.6.1.patch \
    file://charon_timeout_5.6.2.patch \
"
