SUMMARY = "cryptographically secure pseudorandom number generator"
DESCRIPTION = "\
The csprng project provides cryptographically secure pseudorandom number generator. \
It consists of \
\
* C library \
* csprng-generate utility to generate stream of random numbers written to file \
  or to STDOUT \
* Linux daemon csprngd to fill entropy of Linux kernel random device /dev/random \
"
HOMEPAGE = "http://csrng.googlecode.com/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
"
DEPENDS = "openssl"

PR = "r0"
SRC_URI = "\
    http://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/csrng/csprng-1.1.3.tar.bz2 \
    file://etc_sv_rngd_run \
    file://csprng-openssl-1.1.patch \
    file://csprng-ror32.patch \
    file://csprng-read_wakeup_threshold.patch \
"
SRC_URI[md5sum] = "026e9a2e6e9729b8cd133baf637cd6c0"
SRC_URI[sha256sum] = "522882e255a013d24a3c2dd639b69380ecd6b48b549e1ea345dd385e889e051b"

inherit autotools-brokensep runit

PACKAGES_prepend += " ${PN}-progs "
RUNIT_SERVICES = "rngd"

do_configure_prepend() {
    sed -i -e "s/-Werror //" configure.ac
}

FILES_${PN}-progs = "/usr/bin/*"
