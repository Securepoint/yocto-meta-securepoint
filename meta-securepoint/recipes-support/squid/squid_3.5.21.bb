# Copyright (C) 2013 Gernot Tenchio <gernot@tenchio.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The Squid proxy caching server ${PV}"
HOMEPAGE = "http://www.squid-cache.org/"
LICENSE = "GPLv2"
SECTION = "net"
DEPENDS = "openssl libtool \
    db \
    "
PR = "r0"

SRC_URI = "http://www.squid-cache.org/Versions/v3/3.5/squid-${PV}.tar.bz2 \
    file://Fix-flawed-dynamic-ldb-link-test-in-configure.patch \
    file://squid-3.5.15-cross.patch \
    file://squid-3.3.9-remove-key-usage.patch \
    file://etc_sv_squid_run \
    file://etc_sv_squid_down \
    file://etc_sv_squid-reverse_run \
    file://etc_sv_squid-reverse_down \
"
SRC_URI[md5sum] = "95bfeb8796bb4e139e7d43ee396db38f"
SRC_URI[sha256sum] = "94ae3a83776d05be63abff7e88ce7d20f7336735391ac12d2833c03748e7f314"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=c492e2d6d32ec5c1aad0e0609a141ce9 \
    file://errors/COPYRIGHT;md5=0d98c4448c368d146f31a970bb0ced21 \
    file://libltdl/COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

inherit autotools runit

PACKAGECONFIG ??= "ecap ssl"
PACKAGECONFIG[ecap] = "--enable-ecap,--disable-ecap,libecap"
PACKAGECONFIG[ssl] = "--enable-ssl-crtd --with-openssl,--without-openssl,openssl"

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
    --enable-external-acl-helpers=session \
    --disable-url-rewrite-helpers \
    --disable-auth-negotiate \
    --disable-auth-digest \
    --disable-strict-error-checking \
    --enable-auth-basic='PAM,RADIUS' \
    --with-krb5-config=no \
    --without-aio \
    --without-libxml2 \
    --without-netfilter-conntrack \
    --with-included-ltdl \
    squid_cv_gnu_atomics=yes \
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
