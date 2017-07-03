# vim:set ft=sh:
DESCRIPTION = "Nginx is a free, open-source, high-performance HTTP server and reverse proxy, as well as an IMAP/POP3 proxy server."
HOMEPAGE = "http://wiki.nginx.org"
SECTION = "net"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0bb58ed0dfd4f5dbece3b52aba79f023"
PR = "r1"

SRC_URI = "http://nginx.org/download/nginx-${PV}.tar.gz \
	   git://github.com/evanmiller/mod_rrd_graph.git;protocol=http;destsuffix=rrdgraph;tag=5d4c1a90d4d67cffb9df241540db1401fae7423c \
           git://github.com/openresty/echo-nginx-module.git;protocol=http;destsuffix=echomod;tag=v0.53 \
           file://allow-cross.patch \
	   file://nginx.init \
	   file://users"

SRC_URI[md5sum] = "204a20cb4f0b0c9db746c630d89ff4ea"
SRC_URI[sha256sum] = "75020f1364cac459cb733c4e1caed2d00376e40ea05588fb8793076a4c69dd90"

DEPENDS = "libpcre rrdtool openssl zlib"

inherit autotools-brokensep

S = "${WORKDIR}/${PN}-${PV}"

NGINX_CONFIG_FLAGS = "\
    ${@base_contains('DISTRO_FEATURES', 'ipv6', '--with-ipv6', '', d)} \
    --with-http_ssl_module \
    --with-http_auth_request_module \
    --with-http_gzip_static_module \
    --with-http_v2_module \
    --with-cc="${HOST_PREFIX}gcc -Wl,--hash-style=gnu -DNGX_ENABLE_SYSLOG" \
    --sbin-path=${sbindir}/nginx \
    --pid-path=${localstatedir}/run/nginx.pid \
    --lock-path=${localstatedir}/lock/nginx.lock \
    --error-log-path=${localstatedir}/log/nginx_error \
    --conf-path=${sysconfdir}/nginx/nginx.conf \
    --http-log-path=${localstatedir}/log/nginx_access \
    --http-client-body-temp-path=${localstatedir}/lib/nginx/client_body_temp \
    --http-proxy-temp-path=${localstatedir}/lib/nginx/proxy_temp \
    --http-fastcgi-temp-path=${localstatedir}/lib/nginx/fastcgi_temp \
    --http-uwsgi-temp-path=${localstatedir}/lib/nginx/uwsgi_temp \
    --http-scgi-temp-path=${localstatedir}/lib/nginx/scgi_temp"

NGINX_CONFIG_FLAGS += " --add-module=../rrdgraph"
NGINX_CONFIG_FLAGS += " --add-module=../echomod"

do_configure() {
  export cross_compiling="yes"
  # we need this because of
  #  configure: error: invalid option "--host=i586-poky-linux"
  ./configure ${NGINX_CONFIG_FLAGS} --with-cc='${CC}';
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${localstatedir}/lib/nginx
    install -d ${D}${localstatedir}/log
    install -d ${D}${sysconfdir}/nginx
    install -m 0644 ${WORKDIR}/users ${D}${sysconfdir}/nginx/users
    install -m 0755 ${WORKDIR}/nginx.init ${D}${sysconfdir}/init.d/nginx
    oe_runmake DESTDIR=${D} install
    mv ${D}${sysconfdir}/nginx/nginx.conf ${D}${sysconfdir}/nginx/nginx.conf.default
    rm -rf ${D}/usr/local
    rm -f ${D}${sysconfdir}/nginx/*.default
}

FILES_${PN}-dbg += "${bindir}.debug"
FILES_${PN} = "${sysconfdir}/* ${localstatedir} ${sbindir}/${PN} /run"

pkg_postinst_${PN} () {
    update-rc.d nginx defaults 94
}

pkg_postrm_${PN} () {
    update-rc.d nginx remove
}

