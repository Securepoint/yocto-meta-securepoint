# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

# overwrite perl runtime dependency, to prevent yocto from building it
RDEPENDS:${PN}-sensorsdetect = "${PN}-sensors"
RDEPENDS:${PN}-sensorsconfconvert = "${PN}-sensors"
