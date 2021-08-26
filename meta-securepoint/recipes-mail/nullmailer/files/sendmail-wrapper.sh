#!/bin/bash
export NULLMAILER_FLAGS=${NULLMAILER_FLAGS:="f"} #Overwrites FROM: header in mail file.
allmailfrom=$(cat /etc/nullmailer/allmailfrom 2>/dev/null)
#you can use sendmail -f to overwrite NULLMAILER_HOST and NULLMAILER_USER 
if [ "$allmailfrom" ];then
 export NULLMAILER_HOST=${allmailfrom##*@}
 export NULLMAILER_USER=${allmailfrom%@*}
fi

sendmail-bin $@
