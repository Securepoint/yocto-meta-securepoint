FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-4.2:"
SRC_URI = "\
        gitsm://libwebsockets.org/repo/${PN};protocol=https;branch=v4.2-stable \
	file://libwebsockets-remove-mbedtls-noise.patch \
	file://libwebsockets-client-ping.patch \
	file://libwebsockets-reduce-noise.patch \
        file://0001-__lws_shadow_wsi-fix-segfault-on-wrong-assertations.patch \
"
SRCREV = "8d605f0649ed1ab6d27a443c7688598ea21fdb75"
S = "${WORKDIR}/git"
PR = "r1"

include libwebsockets.inc
