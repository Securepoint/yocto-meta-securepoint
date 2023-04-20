FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "\
	file://noip.patch \
"

PACKAGECONFIG = "libnl"
FILES:${PN} += "${datadir}/snmp/mibs"
