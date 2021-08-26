SUMMARY = "C library for country/city/organization to IP address or hostname mapping"
DESCRIPTION = "GeoIP is a C library that enables the user to find the country that any IP \
address or hostname originates from. It uses a file based database that is \
accurate as of March 2003. This database simply contains IP blocks as keys, and \
countries as values. This database should be more complete and accurate than \
using reverse DNS lookups."

HOMEPAGE = "http://dev.maxmind.com/geoip/"
SECTION = "libdevel"

SRC_URI[md5sum] = "fae9021779dffe1b87f038d21f8afd56"
SRC_URI[sha256sum] = "1fe859ed714f94fc902a145453f7e1b5cd928718179ba4c4fcb7f6ae0df7ad37"

SRC_URI = "https://github.com/maxmind/libmaxminddb/releases/download/1.2.0/libmaxminddb-1.2.0.tar.gz \
"
SRCREV = "4f487bf95532e0bba7783d591faff178ab0aa462"

LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
"

inherit autotools

EXTRA_OECONF = "--disable-static               \
                --disable-dependency-tracking  "

do_install() {
    make DESTDIR=${D} install
}

# XXX we just build this to be able to build webalizer 4.0.6.1 without modifications
#     because we can't tell the latter to do without (so far) 
