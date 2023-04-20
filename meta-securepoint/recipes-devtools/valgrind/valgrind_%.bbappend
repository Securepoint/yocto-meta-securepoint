PACKAGES:prepend = "${PN}-utils "

RDEPENDS:${PN} = ""

FILES:${PN}-utils = "\
    /usr/bin/callgrind_annotate \
    /usr/bin/callgrind_control \
    /usr/bin/cg_annotate \
    /usr/bin/cg_diff \
    /usr/bin/cg_merge \
    /usr/bin/ms_print \
"
