FILESEXTRAPATHS:prepend := "${THISDIR}/linux-5.10:${THISDIR}/files:"
COMPATIBLE_MACHINE:append = "|spx86|spx86-64"

SRC_URI += " \
    file://defconfig \
    file://modules.cfg \
    file://squashfs.cfg \
    file://mm.cfg \
    file://nics.cfg \
    file://crypto.cfg \
    file://vm.cfg \
    file://cputemp.cfg \
    file://cong_algos.cfg \
    file://taskstats.cfg \
    file://cifs.cfg \
    file://${MACHINE}.cfg \
    file://i915.cfg \
    file://tomoyo.cfg \
    file://usbserial.cfg \
    file://security.cfg \
    file://netfilter.cfg \
    file://cgroups.cfg \
    file://video.cfg \
    file://storevsc_drv_32bit_tablesize.patch \
    file://0002-e1000e-Intel-Quadport-0x10A0-hack.patch \
    file://hv_vss-excludeloop-option.patch \
    file://09-hyperv \
    file://fanotify.cfg \
    file://iscsi.cfg \
    file://mmc.cfg \
    file://hv_get_dns_info \
    file://hv_get_dhcp_info \
    file://100-balloon.rules \
    ${@oe.utils.conditional('PREFERRED_wifidrivers', 'backports', '', '${SRC_URI_wifi}', d)} \
"

SRC_URI:append:x86-64 = "file://efi.cfg"

SRC_URI_wifi = "\
    file://wifi.cfg \
    file://linux-ath_regd_optional.patch \
    file://ath10k_improve_tx_rate.patch \
"

RRECOMMENDS:${KERNEL_PACKAGE_NAME}-base = ""

include hyperv.inc
