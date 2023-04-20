FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
  file://profile.conf \
  file://no-daemon.patch \
"

do_install:append(){
    install -d -m 700 ${D}/etc/tomoyo
    install -m 400 ../profile.conf ${D}/etc/tomoyo
}

PACKAGES:prepend = " tomoyo-init "
FILES:tomoyo-init = "/sbin/tomoyo-init /etc/tomoyo/profile.conf"

PACKAGES:prepend = " ${PN}-libs "
FILES:${PN}-libs = "${libdir}/lib*.so.*"
