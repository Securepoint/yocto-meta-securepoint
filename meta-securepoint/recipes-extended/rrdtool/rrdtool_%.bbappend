DEPENDS_remove = "libxml2"

PACKAGECONFIG = ""
PACKAGECONFIG[xml] = "--enable-rrd_restore,--disable-rrd_restore,libxml2"

FILES_${PN}-perl = "${libdir}/perl5"

RDEPENDS_${PN}_remove = "perl"
