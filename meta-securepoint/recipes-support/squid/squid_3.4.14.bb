# Copyright (C) 2013 Gernot Tenchio <gernot@tenchio.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The Squid proxy caching server ${PV}"
HOMEPAGE = "http://www.squid-cache.org/"
LICENSE = "GPLv2"
SECTION = "net"
DEPENDS = "openssl libtool"
PR = "r0"

SRC_URI = "http://www.squid-cache.org/Versions/v3/3.4/squid-${PV}.tar.bz2 \
    file://squid-3.4.3-cross.patch \
    file://squid-3.3.9-remove-key-usage.patch \
    file://squid-3.5-13849.patch;striplevel=0 \
    file://etc_sv_squid_run \
    file://etc_sv_squid_down \
    file://etc_sv_squid-reverse_run \
    file://etc_sv_squid-reverse_down \
"
SRC_URI[md5sum] = "f8095c701f33a3917e586f80ac4d854f"
SRC_URI[sha256sum] = "366539ef792a1845fb6c0d42b1499e21a19938b42a638d6bfc5d7d3bb421c48f"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=c492e2d6d32ec5c1aad0e0609a141ce9 \
    file://COPYRIGHT;md5=2900f50789c498be8e9f1eb23b55cbe9 \
    file://helpers/negotiate_auth/kerberos/COPYING;md5=fdedcde17b1ffd967d86c20fe0ac158a \
    file://helpers/basic_auth/SMB/COPYING-2.0;md5=925f7ebc86d51df54fa4a0efb7f25306 \
    file://errors/COPYRIGHT;md5=e91ec80d0231fe14e1c5cb501ce083da \
    file://lib/libTrie/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools runit

PACKAGECONFIG ??= "ecap ssl"
PACKAGECONFIG[ecap] = "--enable-ecap,--disable-ecap,libecap"
PACKAGECONFIG[ssl] = "--enable-ssl-crtd --enable-ssl,--disable-ssl,openssl"

RUNIT_SERVICES = "squid squid-reverse"

EXTRA_OECONF = "\
    --sysconfdir=/etc/squid \
    --datadir=${datadir}/squid \
    --libexecdir=/usr/libexec/squid \
    --enable-delay-pools \
    --disable-wccp \
    --disable-wccpv2 \
    --disable-snmp \
    --disable-ident-lookups \
    --disable-external-acl-helpers \
    --disable-url-rewrite-helpers \
    --disable-auth-ntlm \
    --disable-auth-negotiate \
    --disable-auth-digest \
    --disable-strict-error-checking \
    --enable-auth-basic='PAM' \
    --with-krb5-config=no \
    --without-aio \
    --without-libxml2 \
    --without-netfilter-conntrack \
    --with-included-ltdl \
    CXXFLAGS='-DSQUID_USE_CONST_SSL_METHOD=1 -DSQUID_USE_SSLGETCERTIFICATE_HACK=1 -DSQUID_USE_SSLLHASH_HACK=1 -DSQUID_SSLTXTDB_PSTRINGDATA -DSQUID_STACKOF_PSTRINGDATA_HACK' \
    HOSTCXX=${BUILD_PREFIX}g++ \
    "

do_install() {
    oe_runmake DESTDIR=${D} install
    # delete to remove perl dependency"
    rm -f ${D}/usr/libexec/squid/*.pl
    rm -f ${D}/usr/libexec/squid/log_db_daemon ${D}/usr/libexec/squid/storeid_file_rewrite
    rm -f ${D}/etc/squid/*.{default,documented}
    rm -rf ${D}/run
}

FILES_${PN} = "\
    /etc/sv/* \
    /etc/squid/*.conf \
    /etc/squid/errorpage.css \
    /usr/bin \
    /usr/sbin \
    /var \
    /usr/libexec/squid \
    ${datadir}/squid/icons \
    ${datadir}/squid/errors/templates \
    ${datadir}/squid/errors/TRANSLATORS \
    ${datadir}/squid/errors/COPYRIGHT \
    ${datadir}/squid/mib.txt \
    /run/squid"

FILES_${PN}-dbg += "/usr/libexec/squid/.debug"

include lang.inc
