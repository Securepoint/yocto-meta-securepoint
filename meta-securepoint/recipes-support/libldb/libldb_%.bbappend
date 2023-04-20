FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGES:prepend = "libpyldb-util "

FILES:libpyldb-util = "${libdir}/libpyldb-util.so.*"
