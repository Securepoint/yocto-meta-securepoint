require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://etc_sv_smtpd_run \
           file://aliasesdb \
           file://01_xforward_pre_postfix_3_5.patch \
           "
SRC_URI[md5sum] = "a52452a313fc69e15185864cceb7ab5b"
SRC_URI[sha256sum] = "8167cdbf38f04e94f9a767add9134b270a318c7f3b2ae1cfc2ef0c266bffc7ed"
