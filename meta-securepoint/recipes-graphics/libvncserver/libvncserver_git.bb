require libvncserver.inc
inherit cmake pkgconfig


FILES_${PN}-dev += " /usr/bin/libvncserver-config"
INSANE_SKIP_${PN} += " rpaths"

#EXTRA_OECONF += "\
#    --without-x11vnc \
#    --with-jpeg=${STAGING_LIBDIR}/.. \
#    --with-png=${STAGING_LIBDIR}/.. \
#    --without-x \
#    --without-gnutls --without-gcrypt --without-crypt \
#"


