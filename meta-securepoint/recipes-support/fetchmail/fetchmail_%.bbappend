# fetchmail bbappend
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit runit

RUNIT_SERVICES += "fetchmail"

SRC_URI += "\
    file://etc_sv_fetchmail_run \
    file://etc_sv_fetchmail_down \
    file://fetchmail-oauth2-passwordfile-br64-v6.patch \
    file://0001-increase_password_len.patch \
    file://0002-pop3_and_reread_token.patch \
"

