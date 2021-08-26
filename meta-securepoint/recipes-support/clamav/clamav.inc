# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Clam AntiVirus"
HOMEPAGE = "http://www.clamav.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=2c0b5770a62017a3121c69bb9f680b0c \
    file://COPYING.bzip2;md5=ae8d555c34b656ff864ea9437a10d3a0 \
    file://COPYING.file;md5=e63a61022c36cff2fdfbf02dd51674bd \
    file://COPYING.getopt;md5=5d05c2548fde51bf7c7132e09b2947de \
    file://COPYING.LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://COPYING.llvm;md5=c82fc668ef1809acdd0684811df93bfc \
    file://COPYING.lzma;md5=84044362a179fe47ab28fec03be352d0 \
    file://COPYING.regex;md5=7a04124fed6082129732f23a7d16db60 \
    file://COPYING.YARA;md5=58810d217b67bebe14e636bfe2595bdf \
    file://COPYING.unrar;md5=6a741ba21afc8b71aeaee3b5f86a8111 \
    file://COPYING.zlib;md5=3648a0b9713ab246e11536055165a41a \
"

SECTION = "Applications/File"
DEPENDS = "libxml2 zlib bzip2 openssl pcre libtool curl"
PR = "r0"

SRC_URI = "\
    https://www.clamav.net/downloads/production/${PN}-${PV}.tar.gz \
    file://pcre.m4 \
    file://curl.m4 \
    file://clamav_fdpassing.patch \
    file://clamav_0.102.0_llvm.patch \
    file://etc_sv_clamd_run \
    file://etc_sv_clamd_down \
"

inherit autotools useradd runit pkgconfig

RUNIT_SERVICES = "clamd"
PACKAGES_prepend += " ${PN}-clamd ${PN}-update ${PN}-libs ${PN}-common "

USERADD_PACKAGES += "${PN}-common"
USERADD_PARAM_${PN}-common = "-r -d /var/empty -g clamav -s /bin/false clamav;"
GROUPADD_PARAM_${PN}-common = "-r -f clamav;"

# export LDFLAGS='-Wl,--as-needed'
export FRESHCLAM_LIBS='-lz'
# IPv6 check is buggy and does not work when there are no IPv6 interface on build machine
export have_cv_ipv6='yes'

# curl/json is only required for 'clamsubmit' so disable it
EXTRA_OECONF = "\
    --program-prefix='' \
    --disable-static \
    --disable-rpath \
    --disable-clamdtop \
    --disable-clamonacc \
    --disable-llvm \
    --disable-fanotify \
    --with-included-ltdl \
    --with-xml=${STAGING_LIBDIR}/.. \
    --with-libcheck-prefix=${STAGING_LIBDIR}/.. \
    --with-libbz2-prefix=${STAGING_LIBDIR}/.. \
    --with-zlib=${STAGING_LIBDIR}/.. \
    --with-openssl=${STAGING_LIBDIR}/.. \
    --with-pcre=${STAGING_LIBDIR}/.. \
    --without-libjson \
    --without-iconv \
    --disable-zlib-vcheck \
    CPPFLAGS=-I${STAGING_LIBDIR}/../include/libxml2 \
    have_cv_gai=yes \
    ac_cv_c_mmap_private=yes \
    ac_cv_c_can_get_pagesize=yes \
    ac_cv_c_mmap_anonymous=MAP_ANONYMOUS \
"

do_configure_prepend() {
    # remove the autoconf crap to avoid version mismatches
    cp -f ${WORKDIR}/pcre.m4 ${WORKDIR}/curl.m4 ${S}/m4/reorganization/libs/
    echo "dnl emptied by yocto receipe" > ${S}/m4/reorganization/llvm.m4
    find ${S} -name configure -o -name ltversion.m4 -o -name ltmain.sh -o -name aclocal.m4 | xargs rm -f
}

do_configure_append() {
    # configure can't detect this if cross compiling
    echo "#define HAVE_FD_PASSING 1" >> ${B}/clamav-config.h
}

do_install_prepend() {
    # remove last standing rpath
    sed -i -e 's|^relink_command=.*||' libclamav/libclamunrar_iface.la libclamav/.libs/libclamunrar_iface.la
    install -d ${D}/var/empty
}

# INSANE_SKIP += "unknown-configure-option" doesn't work
# so we have to use this bloody hack
do_qa_configure() {
	echo ""
}

FILES_${PN}-libs       = "${libdir}/libclam*.so.*"
FILES_${PN}-clamd      = "/usr/sbin/clamd /etc/clamd.* /etc/sv/clamd/* /var/service/clamd"
FILES_${PN}-update     = "/usr/bin/freshclam /etc/freshclam.*"
FILES_${PN}-common     = "/var/empty"

RDEPENDS_${PN}        = "${PN}-common"
RDEPENDS_${PN}-clamd  = "${PN}-common"
RDEPENDS_${PN}-update = "${PN}-common"
