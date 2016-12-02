# Copyright (C) 2016 Matthias Lay <matthias.lay@securepoint.de>
# Released under the MIT license (see COPYING.MIT for the terms)

require djbmake.inc

DESCRIPTION = "tcpserver and tcpclient are easy-to-use command-line tools for building TCP client-server applications."
DEPENDS = ""
PR = "r0"

SRC_URI[md5sum] = "39b619147db54687c4a583a7a94c9163"
SRC_URI[sha256sum] = "4a0615cab74886f5b4f7e8fd32933a07b955536a3476d74ea087a3ea66a23e9c"

do_install(){
    bin_files="fixcrio addcr delcr recordio tcpcat mconnect rblsmtpd mconnect-io tcpserver tcpclient tcprules"
    install -d -o root -m 755 "${D}${bindir}"
    for file in $bin_files ; do
        install -o root -m 555 "${S}/$file" "${D}${bindir}"
    done


}
