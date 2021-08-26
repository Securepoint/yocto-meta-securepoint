# Copyright (C) 2013 Matthias Lay <matthiasl.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A secure IMAP server"
HOMEPAGE = "http://www.dovecot.org"
LICENSE = "MIT"
SECTION = "mail"
DEPENDS = "openldap krb5 openssl xz zlib bzip2 libcap icu"
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

EXTRA_OECONF += ' \
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
--with-pam=no \
--with-ldap=plugin \
--with-gssapi=plugin \
'


FILES_${PN} = " ${bindir}/ \
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
FILES_${PN}-dbg = " ${bindir}/.debug \
                ${sbindir}/.debug \
                ${prefix}/libexec/dovecot/.debug/* \
                ${libdir}/dovecot/.debug \
                ${libdir}/dovecot/doveadm/.debug \
                ${libdir}/dovecot/auth/.debug/ \
                ${libdir}/dovecot/stats/.debug \
                ${libdir}/dovecot/dict/.debug \
"
FILES_${PN}-staticdev = "${libdir}/dovecot/*.a \
                ${libdir}/dovecot/doveadm/*.a \
                ${libdir}/dovecot/auth/*.a \
                ${libdir}/dovecot/*.la \
                ${libdir}/*/*/*.la \
                ${libdir}/dovecot/stats/*.a \
                ${libdir}/dovecot/dict/*.a \
"
FILES_${PN}-dev = "/usr/src \
                /usr/include \
                ${libdir}/dovecot/dovecot-config \
                /etc/dovecot \
"
FILES_${PN}-doc = "/usr/share"
