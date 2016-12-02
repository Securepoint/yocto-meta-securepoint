# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A secure IMAP server"
HOMEPAGE = "http://www.dovecot.org"
LICENSE = "MIT"
SECTION = "mail"
DEPENDS = "openldap krb5 openssl libpam"
PR = "r0"

inherit autotools-brokensep pkgconfig runit useradd

USERADD_PACKAGES += "${PN}"
USERADD_PARAM_${PN} = "\
    -r -d /var/empty -g dovecot -s /bin/false dovecot;\
    -r -d /var/empty -g dovenull -s /bin/false dovenull;"
GROUPADD_PARAM_${PN} = "\
    -r -f dovecot;\
    -r -f dovenull;"

RUNIT_SERVICES = "${PN}"

EXTRA_OECONF = ' \
i_cv_epoll_works="yes" \
i_cv_inotify_works="yes" \
i_cv_posix_fallocate_works="no" \
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
--without-sia \
--without-vpopmail \
--without-cdb \
--without-sql \
--without-pgsql \
--without-mysql \
--without-sqlite \
--without-lucene \
--without-stemmer \
--without-solr \
\
--with-ssl=openssl \
--with-storages=maildir \
--with-pam=yes \
--with-ldap=plugin \
--with-gssapi=plugin \
'

FILES_${PN} = " ${bindir}/ \
                ${sbindir}/ \
                ${libdir}/libdovecot.so \
                ${libdir}/dovecot/*.so.* \
                ${prefix}/libexec/dovecot/* \
                ${libdir}/dovecot/doveadm/*.so.* \
                ${libdir}/dovecot/auth/*.so.* \
                /etc/sv/${PN} \
                /var/service/${PN} \
"
FILES_${PN}-dbg = " ${bindir}/.debug \
                ${sbindir}/.debug \
                ${prefix}/libexec/dovecot/.debug/* \
                ${libdir}/dovecot/.debug \
                ${libdir}/dovecot/doveadm/.debug \
                ${libdir}/dovecot/auth/.debug/ \
                ${libdir}/dovecot/stats/.debug \
"
FILES_${PN}-staticdev = "${libdir}/dovecot/*.a \
                ${libdir}/dovecot/doveadm/*.a \
                ${libdir}/dovecot/auth/*.a \
                ${libdir}/dovecot/*.la \
                ${libdir}/*/*/*.la \
                ${libdir}/dovecot/stats/*.a \
"
FILES_${PN}-dev = "/usr/src \
                /usr/include \
                ${libdir}/*/*.so \
                ${libdir}/*/*/*.so \
                ${libdir}/dovecot/dovecot-config \
                /etc/dovecot \
"
FILES_${PN}-doc = "/usr/share"
