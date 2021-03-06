do_install_append_class-target () {
    mkdir -p ${D}${sysconfdir}/ssl/certs ${D}${libdir}/ssl
    find ${D}${datadir}/ca-certificates -type f|xargs cat >> ${D}${libdir}/ssl/cert.pem
    ln -sf ${libdir}/ssl/cert.pem ${D}${sysconfdir}/ssl/cert.pem
    ln -sf ${libdir}/ssl/cert.pem ${D}${sysconfdir}/ssl/certs/ca-bundle.crt
}

DEPENDS_${PN} = "ca-certificates-native"
DEPENDS = ""

PACKAGES_prepend_class-target  = " ca-bundle "
FILES_ca-bundle_class-target   = "\
  ${libdir}/ssl/cert.pem \
  ${sysconfdir}/ssl/certs/ca-bundle.crt \
  ${sysconfdir}/ssl/cert.pem"
DEPENDS_ca-bundle_class-target = ""
