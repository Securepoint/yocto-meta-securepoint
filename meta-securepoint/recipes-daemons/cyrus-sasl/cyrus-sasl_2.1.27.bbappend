FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
  file://cyrus-sasl-gssapi-nodebug.patch \
  file://etc_sv_saslauthd_run \
  file://saslauthd_pam_rhost.patch \
"
PACKAGECONFIG += " pam ntlm gssapi"

EXTRA_OECONF += " --enable-login=yes "

PACKAGES =+ "${PN}-bin-saslauthd"
PACKAGES_DYNAMIC += "^cyrus-sasl-plugin.*"

do_install_append () {
    install -d -m 0755 ${D}/etc/sv/saslauthd ${D}/var/service
    install -m 0700 ${WORKDIR}/etc_sv_saslauthd_run ${D}/etc/sv/saslauthd/run
    ln -sf /etc/sv/saslauthd ${D}/var/service
}

python populate_packages_prepend () {
    sasl_libdir = d.expand('${libdir}/sasl2')
    do_split_packages(d, sasl_libdir, '^lib(.*)\.so*', 'cyrus-sasl-plugin-%s', 'cyrus sasl2 plugin for %s', extra_depends='', allow_links=True, prepend=True)
}

FILES_${PN}-bin-saslauthd = "/usr/sbin/saslauthd /etc/sv/saslauthd/run /var/service/saslauthd"

INSANE_SKIP_${PN}-plugin-anonymous += "dev-so"
INSANE_SKIP_${PN}-plugin-crammd5 += "dev-so"
INSANE_SKIP_${PN}-plugin-digestmd5 += "dev-so"
INSANE_SKIP_${PN}-plugin-gs2 += "dev-so"
INSANE_SKIP_${PN}-plugin-gssapiv2 += "dev-so"
INSANE_SKIP_${PN}-plugin-login += "dev-so"
INSANE_SKIP_${PN}-plugin-otp += "dev-so"
INSANE_SKIP_${PN}-plugin-plain += "dev-so"
INSANE_SKIP_${PN}-plugin-sasldb += "dev-so"
INSANE_SKIP_${PN}-plugin-scram += "dev-so"
INSANE_SKIP_${PN}-plugin-ntlm += "dev-so"
