FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
  file://cyrus-sasl-gssapi-nodebug.patch \
"
PACKAGECONFIG += " ntlm gssapi"

EXTRA_OECONF += " --enable-login=yes "
