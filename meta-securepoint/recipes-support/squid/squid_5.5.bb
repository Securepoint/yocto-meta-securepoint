require squid.inc

SRC_URI = "http://www.squid-cache.org/Versions/v5/squid-${PV}.tar.bz2 \
	   file://squid-openssl3.patch \
"
SRC_URI[sha256sum] = "a614e81b63bcab50811274d1d9d9cc59035c43cadb344b254ba470bac26e1501"

CXXFLAGS:append = " -fpermissive -Wno-attributes -Wno-deprecated-declarations"
