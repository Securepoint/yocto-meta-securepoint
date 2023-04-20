require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://aliasesdb \
           file://02_xforward_post_postfix_3_5.patch \
           file://03_fix_xforward_ident.patch \
           file://0001-postfix-fix-building-on-linux-6-hosts.patch \
           "
SRC_URI[md5sum] = "bbb2168f85530fe4eaa65408a48e731b"
SRC_URI[sha256sum] = "d22f3d37ef75613d5d573b56fc51ef097f2c0d0b0e407923711f71c1fb72911b"

