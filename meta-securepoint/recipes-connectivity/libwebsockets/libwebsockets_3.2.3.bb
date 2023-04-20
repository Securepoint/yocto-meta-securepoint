FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-3.2:"
SRC_URI = "\
        gitsm://libwebsockets.org/repo/${PN};protocol=https;branch=v3.2-stable \
        file://libwebsockets-v3.2.2-vhost.patch \
	file://libwebsockets-remove-mbedtls-noise.patch \
	file://libwebsockets-client-ping.patch \
"
SRCREV = "c744c0934d69912e1cc50ff23d203ad60d535709"
S = "${WORKDIR}/git"
PR = "r1"

EXTRA_OECMAKE += "\
        -DLWS_DBUS_INCLUDE1=${STAGING_INCDIR}/dbus-1.0/ \
        -DLWS_DBUS_INCLUDE2=${STAGING_LIBDIR}/dbus-1.0/include/ \
"

include libwebsockets.inc
