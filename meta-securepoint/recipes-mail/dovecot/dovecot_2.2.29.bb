LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"
SRC_URI = "http://dovecot.org/releases/2.2/${PN}-${PV}.tar.gz \
git://github.com/Securepoint/dovecot-xexec-2.2.git;protocol=http;name=xexec \
file://dovecot-doveadm-fetch-fname.patch"

# recheck this bug in new version. ohterwise see ticket and fix upstream
#file://dovecot-add_dn_escaping_#6768.patch 

SRC_URI[md5sum] = "ff95486a5c43025a2c3f5a712e7962b9"
SRC_URI[sha256sum] = "d939ea99126eb8a8c6955390b422b6e27ec0fa43a3b59b2b3218cb5ad67139a6"

SRCREV_xexec = "master"
# xexec chksum
SRC_URI[xexec.md5sum] = "cd3ddac3305d4f639420c4b84152d39d"
SRC_URI[xexec.sha256sum] = "f1724123bd16fe282ba665857f8541451b7657088eecc9258fab9f7fcc2758ac"

include dovecot.inc

# build xexec plugin
move_xexec_src(){
    [ -d src/plugins/xexec ] || cp -r ../git src/plugins/xexec
}

do_unpack_append() {
    bb.build.exec_func('move_xexec_src', d)
}

do_compile_append(){
    ./config.status --file=src/plugins/xexec/Makefile depfiles
    ${MAKE} -C src/plugins/xexec DESTDIR=${D}
}


do_install_append(){
    source ./dovecot-config
    install -m 644 src/plugins/xexec/.libs/lib10_xexec_plugin.so ${D}/$dovecot_moduledir
}
