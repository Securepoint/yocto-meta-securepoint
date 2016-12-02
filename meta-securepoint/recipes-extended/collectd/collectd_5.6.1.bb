SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2 | MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1bd21f19f7f0c61a7be8ecacb0e28854"

DEPENDS = "rrdtool libtool lmsensors libmnl libgcrypt curl dbus"


SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2 \
           file://check-wordexp-harder.patch \
           file://check-utmp-harder.patch \
           file://0001-dbus-plugin-Started-working-on-a-dbus-plugin-for-col.patch \
           file://etc_sv_${PN}_run \
           file://etc_sv_${PN}_down \
"
SRC_URI[md5sum] = "979f43b6439d7df93f0b40c73d779223"
SRC_URI[sha256sum] = "c30ff644f91407b4dc2d99787b99cc45ec00e538bd1cc269429d3c5e8a4aee2c"

inherit autotools-brokensep pkgconfig runit

RUNIT_SERVICES = "${PN}"

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
                --enable-dbus \
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


do_install_append() {
    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

PACKAGES_prepend = " ${PN}-utils "
# See:
# https://www.yoctoproject.org/docs/current/dev-manual/dev-manual.html#handling-optional-module-packaging
PACKAGES_DYNAMIC += "^collectd-plugin-.*"

python populate_packages_prepend () {
    collectd_libdir = d.expand('${libdir}/collectd')
    do_split_packages(d, collectd_libdir, '^(.*)\.so$', 'collectd-plugin-%s', 'collectd plugin for %s', extra_depends='')
}

FILES_${PN} = "/usr/sbin/collectd /usr/share /usr/lib/lib* /etc /var"
FILES_${PN}-utils = "/usr/sbin/collectdmon /usr/bin/collect*"
FILES_${PN}-dev += "/usr/lib/collecd/*.la"
