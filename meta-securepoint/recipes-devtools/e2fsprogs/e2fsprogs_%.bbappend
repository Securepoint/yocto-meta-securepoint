FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://e2fsck.conf"

FILES_e2fsprogs-e2fsck_append = " /etc/e2fsck.conf"

do_install_append() {
    install -m 0644 ${WORKDIR}/e2fsck.conf "${D}${sysconfdir}"
}
