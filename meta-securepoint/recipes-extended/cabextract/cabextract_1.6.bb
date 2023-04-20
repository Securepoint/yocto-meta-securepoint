SUMMARY = "Utility for extracting cabinet (.cab) archives"
DESCRIPTION = "cabextract is a program which can extract files from cabinet (.cab) archives."
HOMEPAGE = "http://www.cabextract.org.uk/"
SECTION = "console/utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "http://www.cabextract.org.uk/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "ee3ded0b1f84e5c6e3309bb36d701916"
SRC_URI[sha256sum] = "cee661b56555350d26943c5e127fc75dd290b7f75689d5ebc1f04957c4af55fb"

inherit autotools

do_configure:append() {
    echo "#define HAVE_FNMATCH 1" >> config.h
    sed -i -e "s/.*rpl_fnmatch.*//" config.h
}
