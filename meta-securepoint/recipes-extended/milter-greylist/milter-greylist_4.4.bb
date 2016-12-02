# Copyright (C) 2013 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A stand-alone milter written in C that implements greylist filtering"
DESCRIPTION = "\
milter-greylist is a stand-alone milter written in C that implements the \
greylist filtering method, as proposed by Evan Harris. \
Grey listing works by assuming that, unlike legitimate MTA, spam engines \
will not retry sending their junk mail on a temporary error. The filter \
will always reject mail temporarily on a first attempt, then accept it \
after some time has elapsed."
HOMEPAGE = "http://hcpnet.free.fr/milter-greylist/"
LICENSE = "BSD-3-Clause & BSD-4-Clause & ISC"
LIC_FILES_CHKSUM = "\
    file://README;beginline=764;endline=790;md5=bd28f1aa4196a0bdc5508de0d666ff1c \
    file://README;beginline=797;endline=807;md5=2958571d7af80dacc3add729910386fd \
    file://README;beginline=811;endline=840;md5=2339a75434ff9beaea70c51e804168f1 \
    file://README;beginline=854;endline=879;md5=d8cf094a47cd2c78e763221a50f2716e \
    file://README;beginline=884;endline=909;md5=2ba0e2108f8fce60a32c892764064e74 \
"
SECTION = "net"
DEPENDS = "sendmail bison flex"
PR = "r0"
PARALLEL_MAKE = ""

SRC_URI = "\
    ftp://ftp.espci.fr/pub/milter-greylist/milter-greylist-4.4.tgz \
    file://milter-greylist-cross.patch \
"
SRC_URI[md5sum] = "5030c7767c4e4d59497224cd38728ba6"
SRC_URI[sha256sum] = "7f799ba358f24cf3d17c0129e5b417421e582aba00f1147e0ffc5b8a5af0c7ac"

inherit autotools-brokensep siteinfo
