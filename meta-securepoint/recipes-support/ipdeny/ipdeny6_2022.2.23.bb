DESCRIPTION = "geoip database for ipv6 from ipdeny.com"
LICENSE = "CLOSED"
URL = "https://www.ipdeny.com"
SECTION = "database"
PR = "0"
S = "${WORKDIR}"
SRC_URI = "http://build.intern.securepoint.de/downloads/ipdeny/ipv6-all-zones.tar.gz"
SRC_URI[md5sum] = "b3504eedc06f5ae6e2f0db89c896122e"
SRC_URI[sha256sum] = "9d3cde9b399f35232e5354c5a9070af8f2935790de98a943b7c9db7a17230c2f"

inherit allarch

do_patch() {
}

do_configure() {
}

do_qa_configure() {
} 

do_install() {
  install -g 0 -o 0 -d ${D}/data/ipdeny-ip6
  for z in *.zone
  do
    install -g 0 -o 0 $z ${D}/data/ipdeny-ip6/$z
  done
  echo "b3504eedc06f5ae6e2f0db89c896122e" > ${D}/data/ipdeny-ip6.md5
  chown 0:0 ${D}/data/ipdeny-ip6.md5
}

FILES:${PN} = "/data"
