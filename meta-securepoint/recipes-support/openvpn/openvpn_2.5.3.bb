require openvpn.inc

SRC_URI = "\
        http://swupdate.openvpn.org/community/releases/openvpn-${PV}.zip \
        file://openvpn-param-size.patch \
        file://openvpn-2.5-verify_x509_name-list.patch \
        file://openvpn-2.5-framed_ip_and_challenge.patch \
        file://openvpn-2.5-fix_stale_session.patch \
        file://openvpn-2.5-multihome-noif.patch \
        "

SRC_URI[md5sum] = "205fa5139d650cc2f41092e63bc25596"
SRC_URI[sha256sum] = "1848917729c79f688e2d0c5ce2af13e25cc631d616bd4ee5701259b6945cb6b4"
