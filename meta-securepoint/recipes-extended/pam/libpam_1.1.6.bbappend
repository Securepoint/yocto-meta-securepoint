# disable rhosts module
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
  file://pam_no_rhosts.patch \
  file://pam_unix.patch"
