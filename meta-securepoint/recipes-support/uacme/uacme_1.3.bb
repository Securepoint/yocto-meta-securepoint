# Copyright (C) 2019 Andreas Weigel <andreas.weigel@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SRC_URI = "\
  https://github.com/ndilieto/uacme/archive/v${PV}.tar.gz\
  file://03_return_revoked_name.patch \
"

LIC_FILES_CHKSUM = "\
  file://COPYING;md5=5574c6965ae5f583e55880e397fbb018 \
"

EXTRA_OECONF += "\
  --without-ualpn\
"

SRC_URI[md5sum] = "317f986cf201ffc5f61f4b0c27415a9b"
SRC_URI[sha256sum] = "172dd6ca541fd9f89c212d19b0096d5299aa901bb8f9182d808e8243ea3a8a48"

require uacme.inc
