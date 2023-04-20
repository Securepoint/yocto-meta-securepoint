require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://etc_sv_smtpd_run \
           file://aliasesdb \
           file://02_xforward_post_postfix_3_5.patch \
           "
SRC_URI[md5sum] = "fcc071c864d3b152a14b30b399ffda7f"
SRC_URI[sha256sum] = "8de0619dcf2fa7c215a80cf84b82ab71631d4d4722cba0949725ce3e18031d4e"

