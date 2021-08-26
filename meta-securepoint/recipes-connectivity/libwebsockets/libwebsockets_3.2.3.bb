SUMMARY = "A lightweight C library for Websockets"
DESCRIPTION = "Libwebsockets (LWS) is a flexible, lightweight pure C \
  library for implementing modern network protocols easily with a \
  tiny footprint, using a nonblocking event loop."
HOMEPAGE = "https://libwebsockets.org"
SECTION = "libs/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ce87f3facb6f911c142c8bef9bfb380"

# needed to generate the test certificates
DEPENDS = "openssl"

SRC_URI = "\
        gitsm://libwebsockets.org/repo/${PN};protocol=https;branch=v3.2-stable;rev=v${PV} \
        file://libwebsockets-v3.2.2-vhost.patch \
	file://libwebsockets-4.2.0-client-ping.patch \
"
S = "${WORKDIR}/git"
PR = "r1"

PACKAGECONFIG ??= "mbedtls plugins json dbus"
PACKAGECONFIG[mbedtls] = "-DLWS_WITH_MBEDTLS=ON,,mbedtls"
PACKAGECONFIG[plugins] = "-DLWS_WITH_PLUGINS=ON,,zlib libuv"
PACKAGECONFIG[json]    = "-DLWS_WITH_STRUCT_JSON=ON,,"
PACKAGECONFIG[dbus]    = "-DLWS_ROLE_DBUS=ON,,dbus"

EXTRA_OECMAKE += "\
        -DLWS_IPV6=ON \
        -DLWS_DBUS_INCLUDE1=${STAGING_INCDIR}/dbus-1.0/ \
        -DLWS_DBUS_INCLUDE2=${STAGING_LIBDIR}/dbus-1.0/include/ \
"

PACKAGES_prepend += " ${PN}-bin "

inherit cmake

do_install_prepend() {
        touch ${B}/libwebsockets-test-server.key.pem
        touch ${B}/libwebsockets-test-server.pem
}

FILES_${PN}-bin = "/usr/bin /usr/share"
