# vim:set ft=sh:

do_install:append:class-target () {
    mkdir -p ${D}${sysconfdir}/ssl/certs ${D}${libdir}/ssl ${D}${libdir}/ssl-3
    find ${D}${datadir}/ca-certificates -type f|xargs cat >> ${D}${libdir}/ssl/cert.pem
    # create links to standard lookup paths
    ln -sf ${libdir}/ssl/cert.pem ${D}${sysconfdir}/ssl/cert.pem
    ln -sf ${libdir}/ssl/cert.pem ${D}${libdir}/ssl-3/cert.pem
    ln -sf ${libdir}/ssl/cert.pem ${D}${sysconfdir}/ssl/certs/ca-bundle.crt
    ln -sf ${libdir}/ssl/cert.pem ${D}${sysconfdir}/ssl/certs/ca-certificates.crt
}

pkg_postinst:${PN}:class-target () {
        echo ""
}

DEPENDS = "ca-certificates-native"
PACKAGES:prepend = " ca-bundle "
FILES:ca-bundle:class-target   = "\
  ${libdir}/ssl/cert.pem \
  ${libdir}/ssl-3/cert.pem \
  ${sysconfdir}/ssl/certs/ca-bundle.crt \
  ${sysconfdir}/ssl/certs/ca-certificates.crt \
  ${sysconfdir}/ssl/cert.pem"
DEPENDS:ca-bundle = ""
RPROVIDES:ca-bundle = "ca-bundle"
