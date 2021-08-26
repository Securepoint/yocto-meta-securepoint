# vim:set ft=sh:
DESCRIPTION = "Nginx is a free, open-source, high-performance HTTP server and reverse proxy, as well as an IMAP/POP3 proxy server."
HOMEPAGE = "http://wiki.nginx.org"
SECTION = "net"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0bb58ed0dfd4f5dbece3b52aba79f023"
PR = "r1"

SRC_URI = "https://nginx.org/download/nginx-${PV}.tar.gz \
	   git://github.com/evanmiller/mod_rrd_graph.git;protocol=http;destsuffix=rrdgraph;tag=5d4c1a90d4d67cffb9df241540db1401fae7423c \
           git://github.com/openresty/echo-nginx-module.git;protocol=http;destsuffix=echomod;tag=v0.61 \
           file://nginx-cross.patch \
	   file://nginx.init \
	   file://users \
	   file://0001-Allow-the-overriding-of-the-endianness-via-the-confi.patch"

SRC_URI[md5sum] = "cf509a62c6789e4a922a87d822ab6c1d"
SRC_URI[sha256sum] = "308919b1a1359315a8066578472f998f14cb32af8de605a3743acca834348b05"

DEPENDS = "libpcre rrdtool openssl zlib"

inherit autotools-brokensep

S = "${WORKDIR}/${PN}-${PV}"

NGINX_CONFIG_FLAGS = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '--with-ipv6', '', d)} \
    --user=nobody --group=nobody \
    --with-http_ssl_module \
    --with-http_auth_request_module \
    --with-http_gzip_static_module \
    --with-http_v2_module \
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

CC_append = " ${LDFLAGS}"

do_configure() {

    if [ "${SITEINFO_BITS}" = "64" ]; then
        PTRSIZE=8
    else
        PTRSIZE=4
    fi

  export cross_compiling="yes"
  # we need this because of
  #  configure: error: invalid option "--host=i586-poky-linux"
  ./configure \
    --crossbuild=Linux:${TUNE_ARCH} \
    --with-endian=${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
    --with-int=4 \
    --with-long=${PTRSIZE} \
    --with-long-long=8 \
    --with-ptr-size=${PTRSIZE} \
    --with-sig-atomic-t=${PTRSIZE} \
    --with-size-t=${PTRSIZE} \
    --with-off-t=${PTRSIZE} \
    --with-time-t=${PTRSIZE} \
    --with-sys-nerr=132 \
    ${NGINX_CONFIG_FLAGS} --with-cc='${CC}';
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
    rm -rf ${D}/run ${D}/var/run
}

FILES_${PN}-dbg += "${bindir}.debug"
FILES_${PN} = "${sysconfdir}/* ${localstatedir} ${sbindir}/${PN}"
