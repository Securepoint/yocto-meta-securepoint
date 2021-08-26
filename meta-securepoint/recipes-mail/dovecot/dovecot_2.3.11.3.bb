LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"
SRC_URI = "https://dovecot.org/releases/2.3/${PN}-${PV}.tar.gz \
file://dovecot-lmtpnonascii.patch \
file://dovecot-doveadm-fetch-fname.patch"

# recheck this bug in new version. ohterwise see ticket and fix upstream
#file://dovecot-add_dn_escaping_#6768.patch 

SRC_URI[md5sum] = "f06f2272fad04e7b0207f8d00a291f66"
SRC_URI[sha256sum] = "d3d9ea9010277f57eb5b9f4166a5d2ba539b172bd6d5a2b2529a6db524baafdc"

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
'
INSANE_SKIP_${PN} += " dev-so "

include dovecot.inc
