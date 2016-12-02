# split scripts from package to get rid of bash dependency
PACKAGES = "${PN}-scripts ${PN}-base-dbg ${PN}-base ${PN}-base-dev"

RDEPENDS_${PN} = ""
RDEPENDS_${PN}-base = ""
RDEPENDS_${PN}-scripts = "bash"

FILES_${PN}-scripts = "${sbindir}/fsadm ${sbindir}/lvmconf ${sbindir}/lvmdump ${sbindir}/vgimportclone"
FILES_${PN}-base = "${sysconfdir} ${sbindir} ${libdir}/device-mapper/*.so ${base_libdir}/udev ${libdir}/*.so.*"
FILES_${PN}-base-dbg = "${libdir}/device-mapper/.debug ${libdir}/.debug ${sbindir}/.debug"
FILES_${PN}-base-dev += "/usr"

EXTRA_OECONF += " --disable-udev_sync"
