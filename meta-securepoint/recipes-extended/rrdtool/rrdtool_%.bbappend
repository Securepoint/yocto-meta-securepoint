
# remove perl files (adds perl dependency otherwise)
do_install_append() {
  rm -rf ${D}${datadir}/rrdtool/examples
  rmdir --ignore-fail-on-non-empty "${D}/usr/share/rrdtool"
  rmdir --ignore-fail-on-non-empty "${D}/usr/share"
}
