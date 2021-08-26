SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2 | MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1bd21f19f7f0c61a7be8ecacb0e28854"

DEPENDS = "rrdtool libtool lmsensors libmnl curl"

SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2 \
           file://check-wordexp-harder.patch \
           file://check-utmp-harder.patch \
"

SRC_URI[md5sum] = "3cae7d422202f86efa5fc1ac33c5ebf9"
SRC_URI[sha256sum] = "8a97161b354456ed91ec02dd5f47658197f7e18388f3af9d636aae506f795304"

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
                --disable-python --with-python=no --with-libpython=no \
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

do_install_append() {
    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

# See:
# https://www.yoctoproject.org/docs/current/dev-manual/dev-manual.html#handling-optional-module-packaging
PACKAGES_DYNAMIC += "^collectd-plugin-.*"

python populate_packages_prepend () {
    collectd_libdir = d.expand('${libdir}/collectd')
    do_split_packages(d, collectd_libdir, '^(.*)\.so$', 'collectd-plugin-%s', 'collectd plugin for %s', extra_depends='')
}

FILES_${PN} = "/usr/sbin/collectd /usr/share /usr/lib/lib* /etc /var"
FILES_${PN}-dev += "/usr/lib/collecd/*.la"
