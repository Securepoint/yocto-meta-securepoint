FILESEXTRAPATHS_append := "${THISDIR}/files:"
SRC_URI += "\
  file://rrdtool-1.4.7-xml-conditional.patch;patch=1;pnum=1"

PACKAGECONFIG ??= ""
PACKAGECONFIG[xml] = "--enable-rrd_xml,--disable-rrd_xml,libxml2"

# remove perl files (adds perl dependency otherwise)
do_install_append() {
  rm -rf ${D}${datadir}/rrdtool/examples
  rmdir --ignore-fail-on-non-empty "${D}/usr/share/rrdtool"
  rmdir --ignore-fail-on-non-empty "${D}/usr/share"
}
