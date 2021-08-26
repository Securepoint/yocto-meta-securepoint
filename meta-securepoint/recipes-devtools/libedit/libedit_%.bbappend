FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
  file://libedit-tinfo.patch \
"

BBCLASSEXTEND = "native nativesdk"
