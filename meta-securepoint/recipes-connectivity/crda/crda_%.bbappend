# vim:ft=sh:
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://0001-crda-support-python-3-in-utils-key2pub.py.patch \
"

DEPENDS_append = "python-pycrypto-native"
