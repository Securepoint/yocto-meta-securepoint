# this image relies on
#  poky/meta
#  poky/meta-yocto
#  poky/meta-yocto-bsp
#  poky/meta-oe/meta-oe
#  poky/meta-securepoint


# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = "\
    runit \
    nginx \
    bind \
    net-snmp-server-snmpd \
    openvpn \
    strongswan \
    radvd \
    squid \
    postfix \
    openldap \
    openldap-slapd \
    dovecot \
    dovecot-pigeonhole \
    lua5.1 \
    lualdap \
    coreutils \
    util-linux-fsck \ 
    e2fsprogs-mke2fs \
    collectd \
    collectd-plugin-unixsock collectd-plugin-cpu collectd-plugin-syslog \
    collectd-plugin-interface collectd-plugin-memory collectd-plugin-rrdtool \
    collectd-plugin-entropy \
    strace \
    tcpdump \
    lsof \
    nut \
    smartmontools \
    hddtemp \
    util-linux-getopt \
    kernel-module-tun \
    krb5-workstation \
    mskutil \
    cifs-utils kernel-module-cifs \
    "
IMAGE_FSTYPES = "ext3 live"
inherit core-image
