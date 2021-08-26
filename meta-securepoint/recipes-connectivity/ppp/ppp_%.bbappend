FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "\
        file://ppp-2.4.2-ifname.patch \
        file://ppp-2.4.5-increment_req_unit_on_eexist.patch \
        file://ppp-2.4.5-ms_chaphashed.patch \
        file://ppp-ip_pre_down.patch \
        file://0001-pppd-Fix-bounds-check-in-EAP-code.patch \
"
