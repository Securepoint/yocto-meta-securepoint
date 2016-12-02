DESCRIPTION = "A client-side C library implementing the SSH2 protocol"
HOMEPAGE = "http://www.libssh2.org/"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c5cf34fc0acb44b082ef50ef5e4354ca"
SECTION = "libs"

SRC_URI = "http://www.libssh2.org/download/${BPN}-${PV}.tar.gz"

DEPENDS = "openssl"

SRC_URI[md5sum] = "b01662a210e94cccf2f76094db7dac5c"
SRC_URI[sha256sum] = "e4561fd43a50539a8c2ceb37841691baf03ecb7daf043766da1b112e4280d584"

inherit autotools

EXTRA_OECONF = "--with-libssl-prefix=${STAGING_DIR_HOST}"
