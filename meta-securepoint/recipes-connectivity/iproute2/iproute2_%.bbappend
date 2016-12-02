EXTRA_OEMAKE = "CC='${CC}' KERNEL_INCLUDE=${STAGING_INCDIR} DOCDIR=${docdir}/iproute2 SUBDIRS='lib tc ip misc' SBINDIR='${base_sbindir}' LIBDIR='${libdir}'"

do_configure_append() {
  sed -i -e "s/TARGETS=.*/TARGETS=ss nstat ifstat rtacct lnstat/" misc/Makefile
}

# libnetlink and related header files are not installed by default
do_install_append() {
    mkdir -p ${D}${libdir} ${D}${includedir}
    cp lib/libnetlink.a ${D}${libdir}
    cp include/libnetlink.h ${D}${includedir}
    cp include/ll_map.h ${D}${includedir}
    sed -i -e "s;/bash;/sh;"  ${D}/sbin/ifcfg
    sed -i -e "s;/bash;/sh;"  ${D}/sbin/rtpr
}

PACKAGES =+ "${PN}-ss ${PN}-misc"
FILES_${PN}-ss = "${base_sbindir}/ss"
FILES_${PN}-misc = "${base_sbindir}/*stat ${base_sbindir}/rtacct"
