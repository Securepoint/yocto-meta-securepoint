PACKAGES:prepend = " ${BPN}-utils ${BPN}-proxy "

FILES:${BPN}-utils = "\
	/usr/bin/mbim-network \
	/usr/bin/mbimcli"

FILES:${BPN}-proxy = "\
	/usr/libexec/mbim-proxy"

