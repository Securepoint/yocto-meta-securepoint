# libnetlink and related header files are not installed by default
do_install:append() {
    mkdir -p ${D}${libdir} ${D}${includedir}
    cp lib/libnetlink.a ${D}${libdir}
    cp include/libnetlink.h ${D}${includedir}
    cp include/ll_map.h ${D}${includedir}
}
