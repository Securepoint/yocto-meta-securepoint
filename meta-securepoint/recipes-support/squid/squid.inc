# Copyright (C) 2013 Gernot Tenchio <gernot@tenchio.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The Squid proxy caching server ${PV}"
HOMEPAGE = "http://www.squid-cache.org/"
LICENSE = "GPLv2"
SECTION = "net"
DEPENDS = "openssl libtool libpam krb5"
PR = "r0"
# FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# XXX the unquoted_errpage_msg is a rather ugly hack
#     and should be removed with a potential redesign of the captive_portal
SRC_URI = "http://www.squid-cache.org/Versions/v4/squid-${PV}.tar.bz2 \
    file://etc_sv_squid_run \
    file://etc_sv_squid_down \
    file://etc_sv_squid-reverse_run \
    file://etc_sv_squid-reverse_down \
    file://squid-3.3.9-remove-key-usage.patch \
    file://squid-increase-max-capacity.patch \
    file://squid-setup-hostcxx.patch \
    file://squid-nofdcheck.patch \
    file://0002-auth-negotiate-unescape-returned-username.patch \
    file://0003-auth-ntlm-unescape-returned-username.patch \
"

SRC_URI[md5sum] = "341b0dcd5e6f830e0394606083e3f2f6"
SRC_URI[sha256sum] = "4e57918abea56cb8d04f2bb4742ea6c0f113d474f75e1b86c48aa14cd0dbdc46"

LIC_FILES_CHKSUM = "\
    file://COPYING;md5=c492e2d6d32ec5c1aad0e0609a141ce9 \
    file://errors/COPYRIGHT;md5=0d98c4448c368d146f31a970bb0ced21 \
    file://libltdl/COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

inherit autotools runit pkgconfig

PACKAGECONFIG ??= "ecap ssl"
PACKAGECONFIG[ecap] = "--enable-ecap,--disable-ecap,libecap"
PACKAGECONFIG[ssl] = "--enable-ssl-crtd --with-openssl,--without-openssl,openssl"

RUNIT_SERVICES = "squid squid-reverse"

PACKAGES_prepend = " ${PN}-utils ${PN}-auth-ntlm ${PN}-auth-negotiate ${PN}-perl "

EXTRA_OECONF = "\
    --sysconfdir=/etc/squid \
    --datadir=${datadir}/squid \
    --libexecdir=/usr/libexec/squid \
    --enable-delay-pools \
    --enable-auth-basic='PAM,RADIUS' \
    --disable-wccp \
    --disable-wccpv2 \
    --disable-snmp \
    --disable-ident-lookups \
    --disable-external-acl-helpers \
    --enable-log-daemon-helpers=file \
    --disable-url-rewrite-helpers \
    --disable-auth-digest \
    --disable-strict-error-checking \
    --without-aio \
    --without-libxml2 \
    --without-netfilter-conntrack \
    --with-included-ltdl \
    --with-filedescriptors=8192 \
    squid_cv_gnu_atomics=yes \
    squid_cv_working_gssapi=yes \
    squid_cv_working_krb5=yes \
    "

do_install() {
    oe_runmake DESTDIR=${D} install
    # delete to remove perl dependency"
    rm -f ${D}/usr/libexec/squid/*.pl
    rm -f ${D}/usr/libexec/squid/log_db_daemon ${D}/usr/libexec/squid/storeid_file_rewrite
    rm -f ${D}/etc/squid/*.{default,documented}
    rm -rf ${D}/run ${D}/var/run
}

## theoretically, this could be done by --with-filedescriptors=4096 but this setting
## does not work with cross compilation (hard limit of 256 is set then, leading to
## a segfault); see #7749
do_configure_append () {
    echo "#undef SQUID_MAXFD" >> include/autoconf.h
    echo "#define SQUID_MAXFD 8192" >> include/autoconf.h
    sed -i -e "s:^BUILDCXXFLAGS.*:BUILDCXXFLAGS = ${BUILD_CXXFLAGS}:" src/Makefile
}

FILES_${PN}-perl = "/usr/libexec/squid/helper-mux \
		    /usr/libexec/squid/security_fake_certverify"
FILES_${PN}-utils = "/usr/bin/purge /usr/bin/squidclient"
FILES_${PN}-auth-ntlm = "/usr/libexec/squid/ntlm*"
FILES_${PN}-auth-negotiate = "/usr/libexec/squid/negotiate*"
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
    ${datadir}/squid/mib.txt"

FILES_${PN}-dbg += "/usr/libexec/squid/.debug"

include lang.inc
