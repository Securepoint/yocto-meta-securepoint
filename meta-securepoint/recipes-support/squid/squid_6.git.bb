require squid.inc

SRC_URI = "\
    gitsm://github.com/yadij/squid.git;branch=auth-bearer;protocol=https \
    file://squid-openssl3.patch \
    file://squid-typecast.patch \
"
SRC_URI:remove = "\
    file://0006-do_not_stop_listening_on_getsockopt_failure.patch \
    file://squid-4.16_fake_connetion_add_cfinfo.patch \
"
SRC_URI:append = "file://squid-6_fake_connetion_add_cfinfo.patch \
"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
CXXFLAGS:append = " -fpermissive -Wno-attributes -Wno-deprecated-declarations -Wno-permissive"

EXTRA_OECONF:append = "\
    --enable-auth-bearer \
"
