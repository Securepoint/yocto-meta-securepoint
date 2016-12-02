SRC_URI += "file://config-lfs.patch \
            file://quota.patch;striplevel=0 \
"

EXTRA_OECONF += "\
        --enable-nss-wrapper \
        --without-ads \
        --with-winbind \
        --without-ldap \
        --without-krb5"

PACKAGES =+ "libwinbind libwinbind-dbg libnss-winbind winbind winbind-dbg cifs cifs-doc swat"
PACKAGES =+ "libsmbsharemodes libsmbsharemodes-dev"
PACKAGES =+ "libsmbclient libsmbclient-dev"
PACKAGES =+ "libnetapi libnetapi-dev"
PACKAGES =+ "libwbclient libwbclient-dev"
PACKAGES =+ "libtdb libtdb-dev"
PACKAGES =+ "libtevent libtevent-dev libtevent-doc"
PACKAGES =+ "libtalloc libtalloc-dev libtalloc-doc"
PACKAGES =+ "${PN}-tools-net"

FILES_winbind-dbg = "${libdir}/idmap/.debug/*.so \
                     ${libdir}/security/.debug/pam_winbind.so \
"

FILES_${PN} += "${libdir}/vfs/*.so \
                ${libdir}/charset/*.so \
                ${libdir}/*.dat \
                ${libdir}/auth/*.so \
                ${libdir}/security/pam_smbpass.so \
"

FILES_${PN}-dbg += "${libdir}/vfs/.debug/*.so \
                    ${libdir}/charset/.debug/*.so \
                    ${libdir}/auth/.debug/*.so \
                    ${libdir}/security/.debug/pam_smbpass.so \
"

FILES_${PN}-tools-net = "${bindir}/net"

FILES_libwbclient = "${libdir}/libwbclient.so.*"
FILES_libwbclient-dev = "${libdir}/libwbclient.so ${includedir}/wbclient.h ${libdir}/pkgconfig/wbclient.pc"

FILES_libnetapi = "${libdir}/libnetapi.so.*"
FILES_libnetapi-dev = "${libdir}/libnetapi.so ${includedir}/netapi.h ${libdir}/pkgconfig/netapi.pc"

FILES_libsmbsharemodes = "${libdir}/libsmbsharemodes.so.*"
FILES_libsmbsharemodes-dev = "${libdir}/libsmbsharemodes.so ${includedir}/smb_share_modes.h ${libdir}/pkgconfig/smbsharemodes.pc"

FILES_libtdb = "${libdir}/libtdb.so.*"
FILES_libtdb-dev = "${libdir}/libtdb.so ${includedir}/tdb.h ${libdir}/pkgconfig/tdb.pc"

FILES_libtevent = "${libdir}/libtevent.so.*"
FILES_libtevent-dev = "${libdir}/libtevent.so ${includedir}/*tevent*.h ${libdir}/pkgconfig/tevent.pc"
FILES_libtevent-doc = "${mandir}/man3/*tevent*"

FILES_libsmbclient = "${libdir}/libsmbclient.so.*"
FILES_libsmbclient-dev = "${libdir}/libsmbclient.so ${includedir}/libsmbclient.h"

FILES_cifs = "${base_sbindir}/mount.cifs ${base_sbindir}/umount.cifs"
FILES_cifs-doc = "${mandir}/man8/mount.cifs.8 ${mandir}/man8/umount.cifs.8"
FILES_winbind = "${sbindir}/winbindd \
                 ${bindir}/wbinfo \
                 ${bindir}/ntlm_auth \
                 ${sysconfdir}/init.d/winbind \
"
FILES_libwinbind = "${libdir}/idmap/*.so \
                    ${libdir}/pdb \
                    ${libdir}/gpext \
                    ${libdir}/perfcount \
                    ${libdir}/security/pam_winbind.so \
"

FILES_libnss-winbind = "${libdir}/libnss_*${SOLIBS} \
                        ${libdir}/nss_info"

FILES_swat       = "${sbindir}/swat ${datadir}/swat ${libdir}/*.msg"
