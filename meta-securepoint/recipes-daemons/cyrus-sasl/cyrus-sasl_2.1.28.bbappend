FILESEXTRAPATHS:prepend := "${THISDIR}/files:${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
  file://etc_sv_saslauthd_run \
  file://saslauthd_pam_rhost.patch \
"
PACKAGECONFIG += " pam ntlm gssapi"

EXTRA_OECONF += " --enable-login=yes "

PACKAGES =+ "${PN}-bin-saslauthd"
PACKAGES_DYNAMIC += "^cyrus-sasl-plugin.*"

inherit runit
RUNIT_SERVICES = "saslauthd"

python populate_packages:prepend () {
    sasl_libdir = d.expand('${libdir}/sasl2')
    do_split_packages(d, sasl_libdir, '^lib(.*)\.so*', 'cyrus-sasl-plugin-%s', 'cyrus sasl2 plugin for %s', extra_depends='', allow_links=True, prepend=True)
}

FILES:${PN}-bin-saslauthd = "/usr/sbin/saslauthd /etc/sv/saslauthd/run /var/service/saslauthd"

INSANE_SKIP:${PN}-plugin-anonymous += "dev-so"
INSANE_SKIP:${PN}-plugin-crammd5 += "dev-so"
INSANE_SKIP:${PN}-plugin-digestmd5 += "dev-so"
INSANE_SKIP:${PN}-plugin-gs2 += "dev-so"
INSANE_SKIP:${PN}-plugin-gssapiv2 += "dev-so"
INSANE_SKIP:${PN}-plugin-login += "dev-so"
INSANE_SKIP:${PN}-plugin-otp += "dev-so"
INSANE_SKIP:${PN}-plugin-plain += "dev-so"
INSANE_SKIP:${PN}-plugin-sasldb += "dev-so"
INSANE_SKIP:${PN}-plugin-scram += "dev-so"
INSANE_SKIP:${PN}-plugin-ntlm += "dev-so"
