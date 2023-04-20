FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-4.3:"
SRC_URI = "\
        gitsm://libwebsockets.org/repo/${PN};protocol=https;branch=v4.3-stable \
	file://libwebsockets-remove-mbedtls-noise.patch \
	file://libwebsockets-client-ping.patch \
	file://libwebsockets-reduce-noise.patch \
        file://libwebsockets-minimal-ws-debug.patch \
        file://0001-__lws_shadow_wsi-fix-segfault-on-wrong-assertations.patch \
        file://0004-mbedtls-always-log-ssl-read-write-errors.patch \
"
SRCREV = "b0a749c8e7a8294b68581ce4feac0e55045eb00b"
S = "${WORKDIR}/git"
PR = "r1"

EXTRA_OECMAKE += "\
        -DLWS_LOGS_TIMESTAMP=OFF \
"

include libwebsockets.inc
