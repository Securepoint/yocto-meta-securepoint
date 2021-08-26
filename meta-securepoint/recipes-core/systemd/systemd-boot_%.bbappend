PACKAGES += " ${PN}ctl"

do_compile() {
	ninja -v ${PARALLEL_MAKE}
	SYSTEMD_BOOT_EFI_ARCH="ia32"
	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		SYSTEMD_BOOT_EFI_ARCH="x64"
	fi

	ninja src/boot/efi/${SYSTEMD_BOOT_IMAGE_PREFIX}${SYSTEMD_BOOT_IMAGE}
}

do_install_append() {
	install -d ${D}/etc/ld.so.conf.d/
	echo "${libdir}/systemd" > ${D}/etc/ld.so.conf.d/systemd.conf
	install -d ${D}${bindir}
	install -d ${D}${libdir}/systemd/boot/efi
	install ${B}/src/shared/libsystemd-shared-241.so ${D}${libdir}/systemd
	install ${B}/src/boot/efi/systemd-bootx64.efi ${D}${libdir}/systemd/boot/efi
        install ${B}/src/boot/efi/linuxx64.efi.stub ${D}${libdir}/systemd/boot/efi
	install ${B}/bootctl ${D}${bindir}
}

FILES_${PN}ctl = "${bindir}/bootctl ${libdir}/systemd/ /etc/ld.so.conf.d/systemd.conf"
