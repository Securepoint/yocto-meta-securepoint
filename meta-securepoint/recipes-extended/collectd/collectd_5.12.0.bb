SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2 | MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1bd21f19f7f0c61a7be8ecacb0e28854"

DEPENDS = "rrdtool libtool lmsensors libmnl curl"

SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2 \
"

SRC_URI[md5sum] = "2b23a65960bc323d065234776a542e04"
SRC_URI[sha256sum] = "5bae043042c19c31f77eb8464e56a01a5454e0b39fa07cf7ad0f1bfc9c3a09d6"

inherit autotools-brokensep pkgconfig

# Floatingpoint layout, architecture dependent
# 'nothing', 'endianflip' or 'intswap'
FPLAYOUT ?= "--with-fp-layout=nothing"

EXTRA_OECONF = " \
                ${FPLAYOUT} \
                --enable-conntrack \
                --sysconfdir=${sysconfdir}/collectd \
                --enable-cpu \
                --enable-disk \
                --enable-netlink \
                --enable-wireless \
                --enable-sensors \
                --enable-unixsock \
                --enable-filecount \
                --disable-perl   --with-libperl=no --with-perl-bindings=no \
                --with-libpython=no \
                --disable-mysql  --with-libmysql=no \
                --disable-snmp  --with-libnetsnmp=no \
                --disable-postgresql --disable-pinba \
                --enable-write_http \
                --disable-notify_desktop \
                --without-libgcrypt \
                --with-libgcrypt=no \
                --with-java=no \
"

BUILD_CFLAGS += " -Wno-format-truncation -Wno-implicit-fallthrough"
TARGET_CFLAGS += " -Wno-format-truncation -Wno-implicit-fallthrough"

do_install:append() {
    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

# See:
# https://www.yoctoproject.org/docs/current/dev-manual/dev-manual.html#handling-optional-module-packaging
PACKAGES_DYNAMIC += "^collectd-plugin-.*"

python populate_packages:prepend () {
    collectd_libdir = d.expand('${libdir}/collectd')
    do_split_packages(d, collectd_libdir, '^(.*)\.so$', 'collectd-plugin-%s', 'collectd plugin for %s', extra_depends='')
}

FILES:${PN} = "/usr/sbin/collectd /usr/share /usr/lib/lib* /etc /var"
FILES:${PN}-dev += "/usr/lib/collecd/*.la"
