# Copyright (C) 2019 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "DNS Privacy stub resolver (using DNS-over-TLS)"
DESCRIPTION = "'Stubby' is an application that acts as a local DNS Privacy \
stub resolver (using DNS-over-TLS). Stubby encrypts DNS queries sent from \
a client machine (desktop or laptop) to a DNS Privacy resolver increasing \
end user privacy."
HOMEPAGE = "https://dnsprivacy.org/wiki/display/DP/DNS+Privacy+Daemon+-+Stubby"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "getdns openssl libyaml"

inherit autotools

SRC_URI = "https://github.com/getdnsapi/${PN}/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "f0d185ed5926265f2baf84378b270b39"
SRC_URI[sha256sum] = "56ee63f4b9ee00476a168e6ba5614f6830f93e89baa305c2d38577b2e39eae5b"

do_install:append () {
	# Remove /var/run to avoid QA error
	rm -rf ${D}${localstatedir}/run
}
