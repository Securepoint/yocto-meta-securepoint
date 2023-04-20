# Copyright (C) 2019 Andreas Weigel <andreas.weigel@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)
SRC_URI = "\
  https://github.com/ndilieto/uacme/archive/v${PV}.tar.gz\
  file://0001-return_revoked_name.patch \
"

LIC_FILES_CHKSUM = "\
  file://COPYING;md5=5574c6965ae5f583e55880e397fbb018 \
"

EXTRA_OECONF += "\
  --without-ualpn\
"

SRC_URI[md5sum] = "1c9d8499a5cf888ce3025eb8525be97f"
SRC_URI[sha256sum] = "84edb5ede161edf0452f9896fab5373dfe6f2094c5841ff0472a266119659fba"

require uacme.inc
