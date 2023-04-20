# Copyright (C) 2018 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "espipe program is AES encrypting or decrypting pipe. It reads from standard \
input and writes to standard output. It can be used to create and restore \
encrypted tar or cpio archives. It can be used to encrypt and decrypt \
loop-AES compatible encrypted disk images."
HOMEPAGE = "http://loop-aes.sourceforge.net"
LICENSE = "GPLv2"
SECTION = "encryption"
PR = "r0"

SRC_URI = "http://loop-aes.sourceforge.net/${BPN}/${BPN}-v${PV}.tar.bz2"
SRC_URI[md5sum] = "ed05c62c1954110f009bedda4bc1562f"
SRC_URI[sha256sum] = "b135e1659f58dc9be5e3c88923cd03d2a936096ab8cd7f2b3af4cb7a844cef96"

S = "${WORKDIR}/${BPN}-v${PV}"

inherit autotools

