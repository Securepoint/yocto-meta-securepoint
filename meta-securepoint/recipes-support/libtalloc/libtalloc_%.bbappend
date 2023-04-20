FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# *prepend* libpytalloc-util, otherwise, libpytalloc-util.so* ends up in
# the libtalloc package and pulls in a libpython dependency.

PACKAGES:prepend = "libpytalloc-util "

FILES:libpytalloc-util = "${libdir}/libpytalloc-util.so.*"
