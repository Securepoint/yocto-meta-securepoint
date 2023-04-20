DEPENDS:remove = "libxml2"

PACKAGECONFIG = ""
PACKAGECONFIG[xml] = "--enable-rrd_restore,--disable-rrd_restore,libxml2"

FILES:${PN}-perl = "${libdir}/perl5"

RDEPENDS:${PN}:remove = "perl"
