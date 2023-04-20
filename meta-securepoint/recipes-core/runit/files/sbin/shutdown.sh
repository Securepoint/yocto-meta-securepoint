#!/bin/sh

PROG=${0##*/}
SYSRQHOOK=""
RUNLEVEL=""
dosleep=""

if [ "shutdown" = "$PROG" ];then
	while [ $# -gt 0 ]; do
	  case "$1" in
		-r) PROG="reboot" ;;
		-h) PROG="halt"  ;;
		-p) PROG="poweroff"  ;;
		+*) dosleep=${1/+/} ;;
	  esac
	shift
	done
fi

if [ "reboot" = "$PROG" ];then
	SYSRQHOOK="b"
	RUNLEVEL="6"
elif [ "halt" = "$PROG" -o "poweroff" = "$PROG" ];then
	SYSRQHOOK="o"
	RUNLEVEL="0"
fi

[ "x$dosleep" = "x" ] || sleep $dosleep
/usr/sbin/runit-init $RUNLEVEL
ret=$?

#sysrq fallback
if [ $ret -ne 0 -a -w /proc/sysrq-trigger ];then
	#Send a SIGTERM to all processes, except for init
	echo e > /proc/sysrq-trigger
	sleep 1
	#Send a SIGKILL to all processes, except for init.
	echo i > /proc/sysrq-trigger
	#attempt to sync all mounted filesystems.
	echo s > /proc/sysrq-trigger
	#attempt to remount all mounted filesystems read-only.
	echo u > /proc/sysrq-trigger
	sleep 2
	# reboot or halt
	echo $SYSRQHOOK > /proc/sysrq-trigger
fi
exit $ret
