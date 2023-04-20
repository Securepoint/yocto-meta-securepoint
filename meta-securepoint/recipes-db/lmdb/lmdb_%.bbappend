PACKAGES:prepend = " ${PN}-utils "
FILES:${PN}-utils = "/usr/bin"
PRIVATE_LIBS:${PN}:append = "liblmdb.so.${PV}"
