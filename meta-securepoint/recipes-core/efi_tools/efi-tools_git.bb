SUMMARY = "EFI Tools"
DESCRIPTION = "EFI tools are a bunch of minimalistic programs to extend some functionality not directly available with bootctl, such as crediting entropy to the kernel from LoaderRandomSeed or determine the installed version (in the ESP) of systemd-boot"
HOMEPAGE = "https://development.intern.securepoint.de/sp-common/efi_tools.git"
SECTION = "System Environment/Kernel"
LICENSE = "CLOSED"

EFITOOLS_REPO ?= "sp-common/efi_tools.git"
EFITOOLS_BRANCH ?= "master"
EFITOOLS_SRCREV ?= "${AUTOREV}"

SRC_URI = "gitsm://${GIT_HOSTNAME}/${EFITOOLS_REPO};protocol=${GIT_PROTO};branch=${EFITOOLS_BRANCH}"

S = "${WORKDIR}/git"
SRCREV = "${EFITOOLS_SRCREV}"

EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=RELEASE"

PACKAGES  = "${PN}-seed ${PN}-seed-dbg ${PN}-bootversion ${PN}-bootversion-dbg"

FILES:${PN}-seed = "${bindir}/efiseed"
FILES:${PN}-seed-dbg = "${bindir}/.debug/efiseed"
FILES:${PN}-bootversion = "${bindir}/systemd_boot_version"
FILES:${PN}-bootversion-dbg = "${bindir}/.debug/systemd_boot_version"

inherit cmake
