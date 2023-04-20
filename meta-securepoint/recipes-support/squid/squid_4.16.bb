require squid.inc

SRC_URI = "http://www.squid-cache.org/Versions/v4/squid-${PV}.tar.bz2 \
	   file://squid-openssl3.patch \
"
SRC_URI[sha256sum] = "e4c1b0707d0510c36df42ac190bd5a582368bdccdc6be7f072ca33e6ee3515df"

CXXFLAGS:append = " -fpermissive -Wno-attributes -Wno-deprecated-declarations"
