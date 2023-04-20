FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
DEFAULT_PREFERENCE = "1"
PV = "1.21.5"

SRC_URI:remove = "http://nginx.org/download/nginx-${PV}.tar.gz"
SRC_URI:prepend = "https://nginx.org/download/nginx-${PV}.tar.gz"
SRC_URI[md5sum] = "55783f98814279d5036cb0feb4a062b7"
SRC_URI[sha256sum] = "b20f3bf533a518a6f0f3a7967dfeed872d268d31e4cc121a0001329602ddcfbb"

DEPENDS:append = " libpcre2"

PACKAGECONFIG:append = " http2"
