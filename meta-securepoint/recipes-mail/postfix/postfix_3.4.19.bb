require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://etc_sv_smtpd_run \
           file://aliasesdb \
           file://01_xforward_pre_postfix_3_5.patch \
           "
SRC_URI[md5sum] = "c0d582a83b0bbfbe98c2699f328f1904"
SRC_URI[sha256sum] = "8842ab34906207269b0c13195435d94b3444cc61fb889480aa62ee649944e764"
