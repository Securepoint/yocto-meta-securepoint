PACKAGECONFIG:remove = "system-pcre libmount"

RRECOMMENDS_${PN}:remove = "shared-mime-info"
RDEPENDS:${PN}-ptest:remove = "shared-mime-info"
