FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "\
    file://openldap-ldapi-sasl.patch \
"
# GnuTLS nizze gut
PACKAGECONFIG = "sasl openssl modules ldap meta monitor null passwd proxycache dnssrv hdb"
