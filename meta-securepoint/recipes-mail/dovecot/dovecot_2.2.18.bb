LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"
SRC_URI = "http://dovecot.org/releases/2.2/${PN}-${PV}.tar.gz \
git://github.com/Securepoint/dovecot-xexec-2.2.git;protocol=http;name=xexec \
file://dovecot-xexec-pstimport.patch \
file://dovecot-add_dn_escaping_#6768.patch \
file://dovecot-doveadm-fetch-fname.patch"

SRC_URI[md5sum] = "1e42eb3b69544c447ad882d7858f3630"
SRC_URI[sha256sum] = "b6d8468cea47f1227f47b80618f7fb872e2b2e9d3302adc107a005dd083865bb"

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
