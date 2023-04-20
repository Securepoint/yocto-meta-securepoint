# vim:ft=sh:

do_install() {
	oe_runmake CC="${CC} ${CFLAGS}" LD="${LD}" firmware="bios efi32 efi64" install INSTALLROOT="${D}"

	install -d ${D}${datadir}/syslinux/
	install -m 644 ${S}/bios/core/ldlinux.sys ${D}${datadir}/syslinux/
	install -m 644 ${S}/bios/core/ldlinux.bss ${D}${datadir}/syslinux/
	install -m 755 ${S}/bios/linux/syslinux-nomtools ${D}${bindir}/
}

PACKAGES:prepend = "${PN}-efi64 ${PN}-efi32 ${PN}-gptmbr "

FILES:${PN}-efi64 = "${datadir}/${BPN}/efi64"
FILES:${PN}-efi32 = "${datadir}/${BPN}/efi32"
FILES:${PN}-gptmbr = "${datadir}/${BPN}/gptmbr_c.bin ${datadir}/${BPN}/gptmbr_f.bin ${datadir}/${BPN}/gptmbr.bin"

INSANE_SKIP:${PN}-efi64 += "arch"
INSANE_SKIP:${PN}-efi32 += "arch"
