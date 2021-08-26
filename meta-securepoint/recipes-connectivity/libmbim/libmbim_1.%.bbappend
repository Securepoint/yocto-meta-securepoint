PACKAGES_prepend += " ${BPN}-utils ${BPN}-proxy "

FILES_${BPN}-utils = "\
	/usr/bin/mbim-network \
	/usr/bin/mbimcli"

FILES_${BPN}-proxy = "\
	/usr/libexec/mbim-proxy"

