require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://etc_sv_smtpd_run \
           file://aliasesdb \
           file://02_xforward_post_postfix_3_5.patch \
           "
SRC_URI[md5sum] = "4ab110024af1837f6c4098a7f8500b05"
SRC_URI[sha256sum] = "645c6a74959703f8dff5b696b2df2e8bc0c91ac530127a21c998e3defbb9528c"

