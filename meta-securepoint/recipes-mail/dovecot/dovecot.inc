# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A secure IMAP server"
HOMEPAGE = "http://www.dovecot.org"
LICENSE = "MIT"
SECTION = "mail"
DEPENDS = "openssl xz zlib bzip2 libcap"
PR = "r0"

inherit autotools-brokensep pkgconfig runit useradd

USERADD_PACKAGES += "${PN}"
USERADD_PARAM:${PN} = "\
    -r -d /var/empty -g dovecot -s /bin/false dovecot;\
    -r -d /var/empty -g dovenull -s /bin/false dovenull;"
GROUPADD_PARAM:${PN} = "\
    -r -f dovecot;\
    -r -f dovenull;"

RUNIT_SERVICES = "${PN}"

PACKAGECONFIG ??= "pam ldap gssapi icu"

PACKAGECONFIG[solr] = "--with-solr,--without-solr,expat"
PACKAGECONFIG[pam] = "--with-pam=yes,--with-pam=no"
PACKAGECONFIG[ldap] = "--with-ldap=plugin,--with-ldap=no,openldap"
PACKAGECONFIG[gssapi] = "--with-gssapi=plugin,--with-gssapi=no,krb5"
PACKAGECONFIG[icu] = "--with-icu,--without-icu,icu"


EXTRA_OECONF += ' \
i_cv_epoll_works="yes" \
i_cv_inotify_works="yes" \
i_cv_posix_fallocate_works="yes" \
i_cv_signed_size_t="no" \
i_cv_gmtime_max_time_t="40" \
i_cv_signed_time_t="yes" \
i_cv_fd_passing="yes" \
i_cv_c99_vsnprintf="yes" \
i_cv_mmap_plays_with_write="yes" \
i_cv_gssapi_spnego="yes" \
lib_cv_va_copy="yes" \
lib_cv___va_copy="yes" \
lib_cv_va_val_copy="yes" \
--libexecdir=/usr/libexec \
--without-nss \
--without-shadow \
--without-bsdauth \
--without-cdb \
--without-sql \
--without-pgsql \
--without-mysql \
--without-sqlite \
--without-lucene \
--with-ssl=openssl \
'

PACKAGES =+ "${PN}-ldap ${PN}-gssapi ${PN}-fts ${PN}-solr"



FILES:${PN} = " ${bindir}/ \
                ${sbindir}/ \
                ${libdir}/libdovecot.so \
                ${libdir}/dovecot/*.so.* \
                ${libdir}/dovecot/lib* \
                ${libdir}/dovecot/doveadm/*.so \
                ${libdir}/dovecot/auth/*.so / \
                ${libdir}/dovecot/stats/*.so \
                ${libdir}/dovecot/dict/*.so \
                ${prefix}/libexec/dovecot/* \
                ${libdir}/dovecot/doveadm/*.so.* \
                ${libdir}/dovecot/auth/*.so.* \
                /etc/sv/${PN} \
                /var/service/${PN} \
"
FILES:${PN}-dbg = " ${bindir}/.debug \
                ${sbindir}/.debug \
                ${prefix}/libexec/dovecot/.debug/* \
                ${libdir}/dovecot/.debug \
                ${libdir}/dovecot/doveadm/.debug \
                ${libdir}/dovecot/auth/.debug/ \
                ${libdir}/dovecot/stats/.debug \
                ${libdir}/dovecot/dict/.debug \
"
FILES:${PN}-staticdev = "${libdir}/dovecot/*.a \
                ${libdir}/dovecot/doveadm/*.a \
                ${libdir}/dovecot/auth/*.a \
                ${libdir}/dovecot/*.la \
                ${libdir}/*/*/*.la \
                ${libdir}/dovecot/stats/*.a \
                ${libdir}/dovecot/dict/*.a \
"
FILES:${PN}-dev = "/usr/src \
                /usr/include \
                ${libdir}/dovecot/dovecot-config \
                /etc/dovecot \
"
FILES:${PN}-doc = "/usr/share"

FILES:${PN}-ldap = "${libdir}/dovecot/auth/libauthdb_ldap.so \
		   ${libdir}/dovecot/libdovecot-ldap.so* \
		   ${libdir}/dovecot/dict/libdict_ldap.so \
"
FILES:${PN}-gssapi = "${libdir}/dovecot/auth/libmech_gssapi.so"

FILES:${PN}-fts = "${libdir}/dovecot/lib20_fts_plugin.so \
		  ${libdir}/dovecot/lib21_fts_squat_plugin.so \
		  ${libdir}/dovecot/libdovecot-fts.so* \
		  ${libdir}/dovecot/doveadm/lib20_doveadm_fts_plugin.so \
"
FILES:${PN}-solr = "${libdir}/dovecot/lib21_fts_solr_plugin.so"

INSANE_SKIP:${PN} += " dev-so "
INSANE_SKIP:${PN}-ldap += " dev-so "
INSANE_SKIP:${PN}-gssapi += " dev-so "
INSANE_SKIP:${PN}-fts += " dev-so "
INSANE_SKIP:${PN}-solr += " dev-so "
