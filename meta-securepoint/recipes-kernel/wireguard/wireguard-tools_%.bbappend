FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
PACKAGES:prepend = "wg-quick "

SRC_URI:append = " file://001-syncconf_psk_pka.patch"

FILES:wg-quick = "/usr/bin/wg-quick"
RDEPENDS:${PN} = ""
REDPENDS:wg-quick = "bash"
