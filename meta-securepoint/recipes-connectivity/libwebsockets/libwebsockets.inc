SUMMARY = "A lightweight C library for Websockets"
DESCRIPTION = "Libwebsockets (LWS) is a flexible, lightweight pure C \
  library for implementing modern network protocols easily with a \
  tiny footprint, using a nonblocking event loop."
HOMEPAGE = "https://libwebsockets.org"
SECTION = "libs/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ce87f3facb6f911c142c8bef9bfb380"
MIRRORS += "gitsm://libwebsockets.org/repo gitsm://github.com/tenchman \n"

# needed to generate the test certificates
DEPENDS = "openssl"

PACKAGECONFIG ??= "mbedtls json jwt dbus http2 examples"
PACKAGECONFIG[mbedtls]  = "-DLWS_WITH_MBEDTLS=ON,,mbedtls"
PACKAGECONFIG[plugins]  = "-DLWS_WITH_PLUGINS=ON,,zlib libuv"
PACKAGECONFIG[json]     = "-DLWS_WITH_STRUCT_JSON=ON,,"
PACKAGECONFIG[jwt]      = "-DLWS_WITH_JOSE=ON,,"
PACKAGECONFIG[dbus]     = "-DLWS_ROLE_DBUS=ON,,dbus"
PACKAGECONFIG[http2]    = "-DLWS_WITH_HTTP2=ON,-DLWS_WITH_HTTP2=OFF,"
PACKAGECONFIG[examples] = "-DLWS_WITH_MINIMAL_EXAMPLES=ON,,"

EXTRA_OECMAKE += "\
        -DLWS_WITH_SECURE_STREAMS=ON -DLWS_WITH_TLS_SESSIONS=ON \
        -DLWS_WITH_NETLINK=ON -DLWS_WITH_NETWORK=ON \
        -DLWS_WITH_LIBUV=OFF -DLWS_WITH_LIBEV=OFF -DLWS_WITH_LIBEVENT=OFF \
        -DLWS_LOG_TAG_LIFECYCLE=OFF -DLWS_WITH_GENCRYPTO=ON \
        -DLWS_IPV6=ON -DLWS_MAX_SMP=8 -DCMAKE_BUILD_TYPE=Release \
"

CFLAGS:append = " -Wno-implicit-fallthrough "
PACKAGES:prepend = " ${PN}-minimal-ws ${PN}-bin "

inherit cmake pkgconfig

do_configure:append() {
    echo "#define LWS_HAVE_mbedtls_ssl_conf_alpn_protocols" >> lws_config.h
}

do_install:prepend() {
        touch ${B}/libwebsockets-test-server.key.pem
        touch ${B}/libwebsockets-test-server.pem
}

do_install:append() {
    install bin/lws-minimal-* ${D}/usr/bin
}

FILES:${PN}-minimal-ws = "/usr/bin/lws-minimal-ws*"
FILES:${PN}-bin = "/usr/bin /usr/share"
