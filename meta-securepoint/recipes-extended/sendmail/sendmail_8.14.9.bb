# vim:set ft=sh:
DESCRIPTION = "A widely used Mail Transport Agent (MTA)"
HOMEPAGE = "http://www.sendmail.com/sm/open_source/"
SECTION = "net"
PRIORITY = "optional"
LICENSE = "Sendmail"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f18794e9db4c7f74936569f283150e38"
PR = "r2"

SRC_URI = "http://ftp.sendmail.org/sendmail.${PV}.tar.gz \
	  file://sendmail-8.14.3-milterfdleaks.patch;patch=1;pnum=1 \
	  file://sendmail-8.14.3-sharedmilter.patch;patch=1;pnum=1 \
	  file://sendmail-8.14.7-qos.patch;patch=1;pnum=1 \
	  file://fix-sloppy-DB-check.patch \
	  file://sendmail.init \
	  file://site.config.m4"

SRC_URI[md5sum] = "6a3bdceffa592316f830be289a4bd783"
SRC_URI[sha256sum] = "f5a497151abd8f341cca0736c3f9bd703d574d93146b2989689dff6d7a445d75"

DEPENDS = "gdbm openssl cyrus-sasl openldap"


inherit siteinfo

do_define() {
  printf "define(\`${1}\', \`${2}\')\n" >> devtools/Site/site.config.m4
}

do_appenddef() {
  printf "APPENDDEF(\`${1}\', \`${2}\')\n" >> devtools/Site/site.config.m4
}

do_configure() {

  cp -f ${WORKDIR}/site.config.m4 devtools/Site/

  do_define    "confCC"         "${TARGET_PREFIX}gcc -Wl,--hash-style=gnu ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"
  do_define    "confLIBS"       "-lrt -lresolv"
  do_define    "confLIBSEARCH"  ""
  do_define    "confMANROOTMAN" "${mandir}/man"
  do_define    "confMANROOT"    "${mandir}/man"

  # The owner of the MSP queue.
  do_define    "confMSPQOWN" "root"
  # The group for set-group-ID binaries.
  do_define    "confGBINGRP" "mail"

  do_appenddef "confOPTIMIZE"   "${SELECTED_OPTIMIZATION}"

  ### IPv6 support by default
  do_appenddef "confENVDEF"  "-DNETINET6=1"
  # FFR_TLS_1      :: enable CipherList option (eg. ServerSSLOptions/ClientSSLOptions)
  # FFR_TLS_EC     :: enable ECDH
  # FFR_LINUX_MHNL :: set MAXHOSTNAMELEN to 256 (Linux)
  # FFR_QOS        :: support for DSCP/RFC-4594 environments with QoS
  do_appenddef "confENVDEF"  "-D_FFR_TLS_1 -D_FFR_TLS_EC -D_FFR_LINUX_MHNL -D_FFR_QOS -D_FILE_OFFSET_BITS=64"

  ### SASL2 (smtp authentication)
  do_appenddef "confENVDEF" "-DSASL=2"
  do_appenddef "confLIBS" "-lsasl2 -lcrypto"

  ### STARTTLS (smtp + tls/ssl)
  do_appenddef "confENVDEF" "-DSTARTTLS"
  do_appenddef "confLIBS" "-lssl -lcrypto"

  ### LDAP support
  do_appenddef "confMAPDEF" "-DLDAPMAP"
  # enable support for ldap referrals
  do_appenddef "confENVDEF" "-DLDAP_REFERRALS -DSM_CONF_LDAP_MEMFREE=1"
  do_appenddef "confLIBS" "-lldap -llber -lssl -lcrypto"

  ### MILTER
  do_appenddef "confENVDEF" "-DMILTER"

  ### GDBM
  do_appenddef "confMAPDEF" "-DNDBM"
  do_appenddef "confLIBS" "-lgdbm_compat"

  ### BDB
  # do_appenddef "confMAPDEF" "-DNEWDB"
  # do_appenddef "confLIBS" "-ldb"
}

do_compile () {
  oe_runmake
  oe_runmake -C libmilter
}

do_install () {
  OBJDIR=obj.$(uname -s).$(uname -r).$(uname -m)
  MAILDIR=${sysconfdir}/mail

  mkdir -p ${D}${MAILDIR}
  mkdir -p ${D}/usr/{bin,sbin,lib,include}
  mkdir -p ${D}${mandir}/{man1,man5,man8}
  oe_runmake DESTDIR=${D} MANDIR=${mandir} install
  oe_runmake -C ${OBJDIR}/libmilter DESTDIR=${D} MANDIR=${mandir} install
}

PACKAGES_prepend = "${PN}-libmilter-dev ${PN}-libmilter "

FILES_${PN} = "/usr/bin /usr/sbin /etc /var"
FILES_${PN}-libmilter = "/usr/lib/libmilter*"
FILES_${PN}-libmilter-dev = "/usr/lib/libmilter.so /usr/include/libmilter"
FILES_${PN}-dbg += "/usr/.debug"

RDEPENDS_${PN} += "m4 cyrus-sasl-bin-saslauthd cyrus-sasl-plugin-plain cyrus-sasl-plugin-login"
