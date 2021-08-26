LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"
SRC_URI = "http://dovecot.org/releases/2.2/${PN}-${PV}.tar.gz \
file://dovecot-doveadm-fetch-fname.patch"

# recheck this bug in new version. ohterwise see ticket and fix upstream
#file://dovecot-add_dn_escaping_#6768.patch 

SRC_URI[md5sum] = "5c9f69bcda1b48cc7a64b94a311df07f"
SRC_URI[sha256sum] = "ccfa9ffb7eb91e9e87c21c108324b911250c9ffa838bffb64b1caafadcb0f388"


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
# 2.2.28 doesnt create correct lib-symlinks anymore
INSANE_SKIP_${PN} += " dev-so "

include dovecot.inc

