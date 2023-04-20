# runit
DESCRIPTION = "A sysvinit replacement with service supervision"
HOMEPAGE = "http://smarden.org/runit"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://package/COPYING;md5=c9e8a560732fc8b860b6a91341cc603b"

# needed to include this recipe from runit-docker.bb
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PR = "r1"

SRC_URI = "http://smarden.org/runit/runit-${PV}.tar.gz \
           file://cross-compile.patch;patch=1;pnum=1 \
           file://build-svwait.patch;patch=1;pnum=1 \
           file://forced_reboot.patch;patch=1;pnum=1 \
           file://docker.patch;patch=1;pnum=1 \
           file://sbin \
           file://etc"

SRC_URI[md5sum] = "6c985fbfe3a34608eb3c53dc719172c4"
SRC_URI[sha256sum] = "6fd0160cb0cf1207de4e66754b6d39750cff14bb0aa66ab49490992c0c47ba18"

S = "${WORKDIR}/admin/${PN}-${PV}"

TARGET_CC_ARCH += "${LDFLAGS}"

export HOSTCC="${BUILD_PREFIX}gcc"
export CC

PARALLEL_MAKE = ""

do_compile() {
    # fix service dir: "/service/" -> "/var/service/"
    sed -i -e "s:\"/service/:\"/var/service/:" src/sv.c
    sed -i -e 's/ -static//' src/Makefile
    #sometimes errors are quite interesting
    # uncomment to see them
    #sed -i -e "s:2>/dev/null::" src/Makefile
    #sed -i -e "s:>/dev/null::" src/Makefile
    ./package/compile
}

do_install() {
    install -d ${D}/${sbindir}
    for p in `cat ./package/commands`; do
        install -m 0755 ${S}/compile/${p} ${D}/${sbindir}/${p}
    done
    mkdir -p ${D}/etc/runit/{1.d,3.d}
    install -m 0700 -d ${D}/var/service
    cp -rf ${WORKDIR}/etc ${D}/
    cp -rf ${WORKDIR}/sbin ${D}/
    ln -sf shutdown.sh ${D}/sbin/reboot
    ln -sf shutdown.sh ${D}/sbin/halt
    ln -sf shutdown.sh ${D}/sbin/shutdown
    ln -sf shutdown.sh ${D}/sbin/poweroff
}
