LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"
SRC_URI = "https://dovecot.org/releases/2.3/${PN}-${PV}.tar.gz \
file://etc_sv_dovecot_run \
file://etc_sv_dovecot_down \
file://dovecot-lmtpnonascii.patch \
file://dovecot-doveadm-fetch-fname.patch"


SRC_URI[md5sum] = "9a7b00dc52e4fd4d0b8d25e240942f85"
SRC_URI[sha256sum] = "06e73f668c6c093c45bdeeeb7c20398ab8dc49317234f4b5781ac5e2cc5d6c33"

include dovecot.inc
