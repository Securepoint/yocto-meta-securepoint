require postfix.inc

PV = "3.3.1"

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://etc_sv_smtpd_run \
           file://01-xforward.patch \
           file://aliasesdb \
           file://0001-postfix-fix-building-on-linux-5-hosts.patch \
           "
SRC_URI[md5sum] = "4381c6492f415e4a69cf5099d4acea76"
SRC_URI[sha256sum] = "54f514dae42b5275cb4bc9c69283f16c06200b71813d0bb696568c4ba7ae7e3b"
