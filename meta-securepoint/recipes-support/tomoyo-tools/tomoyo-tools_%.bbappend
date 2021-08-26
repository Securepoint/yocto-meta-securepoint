FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
  file://profile.conf \
  file://no-daemon.patch \
"


do_install_append(){
    install -d -m 700 ${D}/etc/tomoyo
    install -m 400 ../profile.conf ${D}/etc/tomoyo
}

