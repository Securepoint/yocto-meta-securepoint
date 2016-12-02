FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit
RUNIT_SERVICES = "ipsec"

EXTRA_OECONF = "\
    --disable-curl \
    --disable-soup \
    --disable-ldap \
    --disable-gmp \
    --disable-mysql \
    --disable-sqlite \
    --enable-nat-transport \
    --enable-af-alg \
    --enable-openssl"

PACKAGES += " ${PN}-plugins-staticdev "
FILES_${PN}-plugins = "${libdir}/ipsec/plugins/*.so"
FILES_${PN}-plugins-staticdev = "${libdir}/ipsec/plugins/*.a ${libdir}/ipsec/plugins/*.la"

SRC_URI += "\
    file://etc_sv_ipsec_down \
    file://etc_sv_ipsec_run \
"

