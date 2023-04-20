require dhcp.inc

SRC_URI += "file://0001-define-macro-_PATH_DHCPD_CONF-and-_PATH_DHCLIENT_CON.patch \
            file://0002-dhclient-dbus.patch \
            file://0003-link-with-lcrypto.patch \
            file://0005-dhcp-client-fix-invoke-dhclient-script-failed-on-Rea.patch \
            file://0006-site.h-enable-gentle-shutdown.patch \
            file://0009-remove-dhclient-script-bash-dependency.patch \
	    file://0013-fixup_use_libbind.patch \
"

SRC_URI[md5sum] = "9076af4cc1293dde5a7c6cae7de6ab45"
SRC_URI[sha256sum] = "0e3ec6b4c2a05ec0148874bcd999a66d05518378d77421f607fb0bc9d0135818"

LDFLAGS:append = " -pthread"
