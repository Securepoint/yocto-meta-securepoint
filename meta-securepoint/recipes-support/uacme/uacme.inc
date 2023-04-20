DESCRIPTION = "lightweight client for the RFC8555 ACMEv2 protocol, written in plain C code with minimal dependencies"
LICENSE = "GPLv3"
SECTION = "net"
PR = "r0"

DEPENDS += " \
  curl\
  mbedtls\
  autoconf-archive\
"

EXTRA_OECONF += "\
  --with-mbedtls\
  --disable-docs\
"

inherit autotools pkgconfig

FILES:${PN} += "/usr/bin/uacme"


