DESCRIPTION = "geoip database from "
LICENSE = "CLOSED"
URL = "https://www.ipdeny.com"
SECTION = "database"
PR = "0"
S = "${WORKDIR}"
SRC_URI = "http://build.intern.securepoint.de/downloads/ipdeny/all-zones.tar.gz"
SRC_URI[md5sum] = "80b567ec1f95451a5ce16a0f56f29b95"
SRC_URI[sha256sum] = "370687a2cfe25d1edeab75d4bd1884c059fc7b051ec8992485a158ea038e4793"

inherit allarch

do_patch() {
}

do_configure() {
}

do_qa_configure() {
} 

do_install() {
  install -g 0 -o 0 -d ${D}/data/ipdeny-ip4
  for z in *.zone
  do
    install -g 0 -o 0 $z ${D}/data/ipdeny-ip4/$z
  done
  echo "80b567ec1f95451a5ce16a0f56f29b95" > ${D}/data/ipdeny-ip4.md5
  chown 0:0 ${D}/data/ipdeny-ip4.md5
}

FILES:${PN} = "/data"
