require squid.inc

SRC_URI[md5sum] = "31e524a416715d6bfef30e072d2ca076"
SRC_URI[sha256sum] = "d09d3c31e3a7d158bda75501e763bd1cd3c3a99f5af6781ec1fd30eed2f771ed"

do_install_append() {
    ln -s /etc/squid/ERR_NWK_AUTH_REQUIRED ${D}/${datadir}/squid/errors/templates/ERR_NWK_AUTH_REQUIRED
}
