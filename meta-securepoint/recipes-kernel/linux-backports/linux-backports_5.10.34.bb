# vim:syntax=sh:
DESCRIPTION = "Linux Backports"
HOMEPAGE = "https://backports.wiki.kernel.org"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += " coreutils-native flex-native bison-native"

PR = "1"

SRC_URI[md5sum] = "4cd50b05c13f69f456d315568731b379"
SRC_URI[sha256sum] = "eb69ad1ee07a2007fdda1c2661a5d2e92fa78e4051025c599f78b62f5b4d4677"
SRC_URI = "\
        https://mirrors.edge.kernel.org/pub/linux/kernel/projects/backports/stable/v${PV}/backports-${PV}-1.tar.gz \
        file://backports_config \
	file://kconfig-defaults.patch \
        file://make.patch \
	file://linux-ath_regd_optional.patch \
	file://ath10k_improve_tx_rate.patch \
"

SRC_URI:append = "\
       file://01_18_mac80211_assure_all_fragments_are_encrypted.patch \
       file://02_18_mac80211_prevent_mixed_key_and_fragment_cache_attacks.patch \
       file://03_18_mac80211_properly_handle_A-MSDUs_that_start_with_an_RFC_1042_header.patch \
       file://04_18_cfg80211_mitigate_A-MSDU_aggregation_attacks.patch \
       file://05_18_mac80211_drop_A-MSDUs_on_old_ciphers.patch \
       file://06_18_mac80211_add_fragment_cache_to_sta_info.patch \
       file://07_18_mac80211_check_defrag_PN_against_current_frame.patch \
       file://08_18_mac80211_prevent_attacks_on_TKIP_WEP_as_well.patch \
       file://09_18_mac80211_do_not_accept_forward_invalid_EAPOL_frames.patch \
       file://10_18_mac80211_extend_protection_against_mixed_key_and_fragment_cache_attacks.patch \
       file://11_18_ath10k_add_CCMP_PN_replay_protection_for_fragmented_frames_for_PCIe.patch \
       file://12_18_ath10k_drop_fragments_with_multicast_DA_for_PCIe.patch \
       file://13_18_ath10k_drop_fragments_with_multicast_DA_for_SDIO.patch \
       file://14_18_ath10k_drop_MPDU_which_has_discard_flag_set_by_firmware_for_SDIO.patch \
       file://15_18_ath10k_Fix_TKIP_Michael_MIC_verification_for_PCIe.patch \
       file://16_18_ath10k_Validate_first_subframe_of_A-MSDU_before_processing_the_list.patch \
       file://17_18_ath11k_Clear_the_fragment_cache_during_key_install.patch \
"

inherit module

do_configure () {
	set -e
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
  	make -C ${STAGING_KERNEL_DIR} M=${S} modules_prepare || true
        cp ${WORKDIR}/backports_config ${S}/.config
	oe_runmake -w oldconfig CC='${BUILD_CC}' V=1
	# sed -e 's/\(.*REQUIRE_SIGNED_REGDB\).*/# \1 is not set/g' ${S}/.config
}

S = "${WORKDIR}/backports-${PV}-1"

EXTRA_OEMAKE += "V=1 LEX=flex YACC=bison KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} KLIB=${STAGING_KERNEL_DIR}"

PACKAGES_DYNAMIC = " ^kernel-module-.*"
EXCLUDE_FROM_WORLD = "1"
