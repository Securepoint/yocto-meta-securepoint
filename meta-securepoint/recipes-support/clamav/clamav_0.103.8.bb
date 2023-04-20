SRC_URI[sha256sum] = "6f49da6ee927936de13d359e559d3944248e3a257d40b80b6c99ebe6fe8c8c3f"
SRC_URI:append = " file://clamav_0.103.7-freshclam-install.patch "
EXTRA_OECMAKE:append = " -DHAVE_ATTRIB_PACKED=1 "
require clamav.inc
