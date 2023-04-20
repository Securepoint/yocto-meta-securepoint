FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://e2fsck.conf"

FILES:e2fsprogs-e2fsck:append = " /etc/e2fsck.conf"

do_install:append() {
    install -m 0644 ${WORKDIR}/e2fsck.conf "${D}${sysconfdir}"
}
