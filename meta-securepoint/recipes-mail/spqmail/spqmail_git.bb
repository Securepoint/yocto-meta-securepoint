DESCRIPTION = "spqmail is a secure, reliable, efficient, simple message transfer agent,and is based upon qmail, which was designed for typical Internet-connected UNIX hosts"
SECTION = "mail"
LICENSE = "CLOSED"
PR = "r0"

SRCREV = "master"
SRC_URI = "git://${GIT_HOSTNAME}/mas/qmail.git;protocol=${GIT_PROTO} \
    file://0000-qmail-uids.patch \
    file://0001-Makefile-fixed-to-use-n-option-with-head-tail.patch \
    file://0002-qmail-smtpd-UCSPISSL.patch \
    file://0003-qmail-smtpd-ehlo-tls-auth-started.patch \
    file://0004-qmail-sasl-auth-debug-log-no-password.patch \
    file://0005-qmail-sasl-depends-on-fork.h.patch \
    file://0006-qmail-bounce.patch \
"
SRC_URI[md5sum] = "622f65f982e380dbe86e6574f3abcb7c"
SRC_URI[sha256sum] = "21ed6c562cbb55092a66197c35c8222b84115d1acab0854fdb1ad1f301626f88"

S = "${WORKDIR}/git"
DEPENDS = ""

PARALLEL_MAKE = ""

inherit useradd

# we need these users AND U/GIDs
USERADD_PACKAGES += "${PN}"
GROUPADD_PARAM_${PN} = "-r -o -g 20 nofiles;\
-r -o -g 21 qmail;"
USERADD_PARAM_${PN} = "-d /var/qmail/alias -o -u 20 -g qmail -s /bin/true alias;\
-d /var/qmail -o -u 21 -g qmail -s /bin/true qmaild;\
-d /var/qmail -o -u 22 -g qmail -s /bin/true qmaill;\
-d /var/qmail -o -u 23 -g qmail -s /bin/true qmailp;\
-d /var/qmail -o -u 24 -g qmail -s /bin/true qmailq;\
-d /var/qmail -o -u 25 -g qmail -s /bin/true qmailr;\
-d /var/qmail -o -u 26 -g qmail -s /bin/true qmails;\
"

EXTRA_OEMAKE += " PREFIX=/usr "

FILES_${PN}-dbg += "/opt/spqmail-in/bin/.debug /opt/spqmail-out/bin/.debug"
FILES_${PN} += "/opt/spqmail-in/bin/ /opt/spqmail-out/bin/"


do_configure(){
    #for host
    echo "${STAGING_DIR_HOST}/var/qmail" > ${S}/conf-qmail
    sed -i -e "s#dialout:\*:20:.*##g" ${STAGING_DIR_HOST}/etc/group
    sed -i -e "s#fax:\*:21:.*##g" ${STAGING_DIR_HOST}/etc/group
    mkdir -p ${S}/host 
    echo "${BUILD_CC} ${BUILD_CFLAGS}" > ${S}/conf-cc
    echo "${BUILD_CC}" > ${S}/conf-ld
}

config_for_target(){
    #perl -pi -e "s:.*\./chkspawn::" ${S}/Makefile 
    echo "/var/qmail" > ${S}/conf-qmail
    echo "${CC} -fPIC" > ${S}/conf-cc
    echo "${CC}" > ${S}/conf-ld
    #target system specific flags
    echo "#define HASFLOCK 1" > hasflock.h
    echo "#define HASMKFIFO 1" > hasmkffo.h
    echo "#define HASSIGACTION 1" > hassgact.h
    echo "#define HASSIGPROCMASK 1" > hassgprm.h
    echo "#define HASSHORTSETGROUPS 1"  > hasshsgr.h
    echo "#define HASWAITPID 1"  > haswaitp.h
}

do_compile(){

HOST_PRGS="install auto-str auto-int8 auto-uid auto-gid auto-int chkshsgr chkspawn"
    #build host
    ${MAKE} ${HOST_PRGS}
    cp ${S}/Makefile ${S}/Makefile.bak
    for prg in ${HOST_PRGS} ; do
	cp ${prg} ${S}/host
	#patch makefile to use hostprogs
	sed -ri "s:\./${prg}:\./host/${prg}:g" ${S}/Makefile
    done
    ##
    #${MAKE} auto-str auto-int8 auto-int chkshsgr; \

    echo "make target..."
    config_for_target
    ${MAKE} compile makelib it man 
    #mv host prog into make dir do not install host prgs on target !
    mv ${S}/host/* ${S}

    rm -r ${S}/host
    mv ${S}/Makefile.bak ${S}/Makefile
}


install_bindir(){
    binaries_711="qmail-clean qmail-getpw qmail-local qmail-pw2u qmail-remote qmail-rspawn qmail-send splogger"
    binaries_755="maildirmake maildirwatch mailsubj predate preline qail qbiff qmail-inject qmail-showctl qmail-smtpd qmail-tcpok qmail-tcpto sendmail"
    install -d -o root -g qmail -m 755 $1
    for bin in "$binaries_711" ; do
        install -o root -g qmail -m 711 $bin $1 
    done
    for bin in "$binaries_755" ; do
        install -o root -g qmail -m 755 $bin $1 
    done
    install -o qmailq -g qmail -m 4711 qmail-queue $1

}

do_install(){
    #./install
    cd ${S}
    install -d -o root -g qmail -m 755 "${D}/opt/spqmail-out"
    install -d -o root -g qmail -m 755 "${D}/opt/spqmail-in"
    install_bindir "${D}/opt/spqmail-out/bin"
    install_bindir "${D}/opt/spqmail-in/bin"
} 
