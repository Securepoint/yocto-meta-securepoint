# Error: Transaction check error:
# file /etc/sysctl.conf conflicts between attempted installs of procps-3.3.12-r0.i586 and utm-base-1.0-r1.noarch
do_install:append () {
	rm -f "${D}/etc/sysctl.conf"
}
