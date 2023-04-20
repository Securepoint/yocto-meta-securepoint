SUMMARY = "Postfix Mail Transport Agent"
DESCRIPTION = "Postfix is Wietse Venema's mail server that started life at \
IBM research as an alternative to the widely-used Sendmail program. \
Postfix attempts to be fast, easy to administer, and secure. The outsidei \
has a definite Sendmail-ish flavor, but the inside is completely different."

HOMEPAGE= "http://www.postfix.org"
SECTION = "mail"
DEPENDS = "lmdb libpcre openssl postfix-native m4-native \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ldap', 'openldap', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sasl', 'cyrus-sasl', '', d)} \
"

DEPENDS:class-native = "db-native openssl-native libpcre-native m4-native"

LICENSE = "EPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=64375f37431336ea1b1b3005fe3fa354"

S = "${WORKDIR}/postfix-${PV}"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native"

inherit useradd update-alternatives

RUNIT_SERVICES = "smtpd"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = \
"-d /var/spool/postfix -r -g postfix -G mailrelay --shell /bin/false postfix; \
 -d /var/spool/vmail -r -g vmail --shell /bin/false vmail \
"
GROUPADD_PARAM:${PN} = "--system mailrelay;--system postfix;--system postdrop;--system vmail"

export SYSLIBS = "${LDFLAGS}"

# CCARGS specifies includes, defines
# AUXLIBS specifies libraries
# Linux2/Linux3 has HAS_DB defined
# makedefs will make CC to be CC+CCARGS

# ldap support
CCARGS-ldap  = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'ldap', '-DHAS_LDAP', '', d)}"
AUXLIBS-ldap = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'ldap', '-lldap -llber', '', d)}"

# no native openldap
CCARGS-ldap:class-native = ""
AUXLIBS-ldap:class-native = ""

# SASL support -DUSE_LDAP_SASL -DUSE_SASL_AUTH
# current openldap didn't enable SASL
CCARGS-sasl  = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'sasl', '-DUSE_SASL_AUTH -DUSE_CYRUS_SASL -I=/usr/include/sasl', '', d)}"
AUXLIBS-sasl = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'sasl', '-lsasl2', '', d)}"
CCARGS-sasl:class-native = ""
AUXLIBS-sasl:class-native = ""

CCARGS-lmdb = "-DHAS_LMDB"
aux-lmdb = "-llmdb"
CCARGS-lmdb:class-native = ""
aux-lmdb:class-native = ""

# PCRE, TLS support default, no support for Berkeley DB
export CCARGS  = "${CFLAGS} -DNO_DB -DNO_NIS -DNO_NISPLUS -DHAS_PCRE -DUSE_TLS ${CCARGS-ldap} ${CCARGS-sasl} ${CCARGS-lmdb}"
export AUXLIBS = "-lpcre -lssl -lcrypto ${AUXLIBS-sasl} ${AUXLIBS-ldap}"
export POSTCONF = "${STAGING_DIR_NATIVE}${sbindir_native}/postconf"
export AUXLIBS_LMDB = "${aux-lmdb}"

# OPT,DEBUG is aready in CFLAGS
# ignore the OPTS="CC=$CC" in Makefile it will not use the CC=$CC $CCARGS
EXTRA_OEMAKE += "OPT= DEBUG= OPTS= "

do_compile () {
    unset CFLAGS CPPFLAGS CXXFLAGS

    native_build="${@['0', '1'][bb.data.inherits_class('native', d) or bb.data.inherits_class('nativesdk', d)]}"

    # if not native build, then pass SYSTEM and RELEASE to makedefs
    if [ "${native_build}" != "1" ]; then
        # uname -s for target
        SYSTEM="Linux"

        # uname -r, use 2.6 as bottomline, even target kernel ver > 2.6
        RELEASE="2.6.34"
        sed -i -e \
            "s:\$(SHELL) makedefs):\$(SHELL) makedefs $SYSTEM $RELEASE):" \
            ${S}/Makefile.in
        export BUILD_SYSROOT="${STAGING_DIR_HOST}"
        export BUILD_SYSROOT_NSL_PATH="${STAGING_BASELIBDIR} \
                                       ${STAGING_LIBDIR}"
    else
        # native build
        export BUILD_SYSROOT="${STAGING_DIR_NATIVE}"

        # ubuntu x86 host: /lib/x86_64-linux-gnu(64) /lib/i386-linux-gnu (32)
        #             on 64 bits, 32 libs in i386-linux-gnu
        # let makedefs finds nsl and resolv libs, host CC will link
        # the correct libraries
        BUILD_SYSROOT_NSL_PATH="$(${CC} -print-search-dirs 2>/dev/null | \
                      sed -n '/^libraries: =/s/libraries: =//p' | \
                      sed -e 's/:/\n/g' | xargs -n1 readlink -f | \
                      grep -v 'gcc\|/[0-9.]\+$' | sort -u)"
        if [ -z "$BUILD_SYSROOT_NSL_PATH" ]; then
            BUILD_SYSROOT_NSL_PATH="/usr/lib64 /lib64 \
                                    /lib/x86_64-linux-gnu \
                                    /usr/lib   /lib \
                                    /lib/i386-linux-gnu"
        fi
        export BUILD_SYSROOT_NSL_PATH
    fi

    oe_runmake makefiles
    oe_runmake
}

do_install:prepend:class-native() {
    export POSTCONF="bin/postconf"
}

do_install () {
    sh ./postfix-install 'install_root=${D}' \
        'config_directory=${sysconfdir}/postfix/' \
        'daemon_directory=${libexecdir}/postfix/' \
        'command_directory=${sbindir}' \
        'sendmail_path=${sbindir}/sendmail.postfix' \
        'newaliases_path=${bindir}/newaliases' \
        'mailq_path=${bindir}/mailq' \
        'manpage_directory=${mandir}' \
        'data_directory=${localstatedir}/lib/postfix' \
        -non-interactive
    rm -rf ${D}${localstatedir}/spool/postfix
    rm -rf ${D}${mandir}//*

    install -m 755 ${S}/bin/smtp-sink ${D}/${sbindir}/

    install -m 0755 ${WORKDIR}/aliasesdb ${D}${libexecdir}

    # keep PID and postfix data in /var
    install -m 0755 -d ${D}${localstatedir}/lib/postfix
    chown postfix:nogroup ${D}${localstatedir}/lib/postfix

    chown :postdrop ${D}${sbindir}/postqueue
    chown :postdrop ${D}${sbindir}/postdrop
    chmod g+s ${D}${sbindir}/postqueue
    chmod g+s ${D}${sbindir}/postdrop
}

do_install:append () {
    # cleanup configuration directory
    rm -rf ${D}/${sysconfdir}/postfix/main.cf*
    rm -rf ${D}/${sysconfdir}/postfix/master.cf*

    # remove documentation pages
    for doc in access canonical generic header_checks relocated transport virtual virtual_alias
    do
      rm -rf ${D}/${sysconfdir}/postfix/$doc
    done
}

do_install:append:class-target() {
    # Remove references to buildmachine paths in target makedefs.out
    sed -i 's:-fdebug-prefix-map[^ ]*::g; s:--sysroot=${STAGING_DIR_TARGET}::g' ${D}/etc/postfix/makedefs.out
}

NATIVE_INSTALL_WORKS = "1"

ALTERNATIVE:${PN} = "sendmail"
ALTERNATIVE_TARGET[sendmail] = "${sbindir}/sendmail.postfix"
ALTERNATIVE_LINK_NAME[sendmail] = "${sbindir}/sendmail"
ALTERNATIVE_PRIORITY = "120"

pkg_postinst:${PN} () {
    if [ "x$D" != "x" ]; then
        if ${@'true' if 'linuxstdbase' in d.getVar('DISTROOVERRIDES', False) else 'false'}; then
            # /usr/lib/sendmail is required by LSB core test
            [ ! -L $D/usr/lib/sendmail ] && ln -sf ${sbindir}/sendmail $D/usr/lib/
        fi
    fi
}

# Exclude .debug directories from the main package
FILES:${PN} = "${sysconfdir} ${localstatedir} ${bindir}//* ${sbindir}//* \
               ${libexecdir}//* ${systemd_unitdir}//*"
FILES:${PN}-dbg += "${libexecdir}/.debug"

RDEPENDS:${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'sasl', 'cyrus-sasl-bin-saslauthd cyrus-sasl-plugin-plain cyrus-sasl-plugin-login', '', d)}"
