DESCRIPTION = "This is nullmailer, a sendmail/qmail/etc replacement MTA for hosts which relay to a fixed set of smart relays.  It is designed to be simple to configure, secure, and easily extendable."
HOMEPAGE = "http://untroubled.org/nullmailer/"
SECTION = "mta"
DEPENDS = "gnutls coreutils-native"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://github.com/bruceg/nullmailer.git;protocol=http \
    file://etc_sv_${PN}_run\
    file://etc_sv_${PN}_log_run\
"

SRCREV = "${PV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep runit useradd

RUNIT_SERVICES = "${PN}"

USERADD_PACKAGES += " ${PN}"
GROUPADD_PARAM:${PN}  = "-r nullmail;"
USERADD_PARAM:${PN} = "-r -g nullmail -d /var/spool/nullmailer nullmail;\
"

EXTRA_OECONF += " --enable-tls"
EXTRA_OEMAKE += " DESTDIR=${D}"

#make autotools happy
do_configure:prepend(){
    cd ${S}
    touch NEWS README AUTHORS ChangeLog
}

do_install:append(){
   make install-root DESTDIR=${D}
# HACK HACK HACK
# TODO! this remove is a workaround for a bug in pyros QA file check
# which tries to open all normal files(which includes pipes in pyro), 
# so when trying to open the "trigger" pipe, bitbake hangs forever. 
# I found no way to exclude files from this QA-check
# so the pipe is created in the runit file for now and removed from yocto
   rm ${D}//var/spool/nullmailer/trigger
}
