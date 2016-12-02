# Copyright (C) 2014 Gernot Tenchio <gernot.tenchio@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

# overwrite perl runtime dependency, to prevent yocto from building it
RDEPENDS_${PN}-sensorsdetect = "${PN}-sensors"
RDEPENDS_${PN}-sensorsconfconvert = "${PN}-sensors"
