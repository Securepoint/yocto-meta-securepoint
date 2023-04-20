FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
# the original receipe sets -DENABLE_EXAMPLES=OFF -DENABLE_APP=OFF but
# rdepends on it ... Strange
RDEPENDS:${PN} = ""
