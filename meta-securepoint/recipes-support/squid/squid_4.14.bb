require squid.inc

SRC_URI[md5sum] = "d0bbd59ddd068d3cde93c1ee4b53b983"
SRC_URI[sha256sum] = "4ad08884f065f8e1dac166aa13db6a872cde419a1717dff4c82c2c5337ee5756"

do_install_append() {
    ln -s /etc/squid/ERR_NWK_AUTH_REQUIRED ${D}/${datadir}/squid/errors/templates/ERR_NWK_AUTH_REQUIRED
}
