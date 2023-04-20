#!/bin/sh
# Simple tool to fetch messages from the syslog database.

DB_PATH="/data/syslog"
[ -r /etc/syslog-ng/LOGPATH ] && DB_PATH=$(cat /etc/syslog-ng/LOGPATH)

# use URI format for file to open readonly (https://www.sqlite.org/uri.html)
CMD="sqlite3 -batch file:${DB_PATH}/messages.db?mode=ro"
numlines=""
id=0
follow=false
filter=""
program=""
table="messages"

usage() {
  if [ ! -z "$1" ]; then
    echo "ERROR: $1"
  fi
  echo "Usage: $(basename $0) [-f] [-n N] [-p PROGRAM] [-e FILTER] [TABLE]"
  echo ""
  echo "  -h          display this help message"
  echo "  -f          'tail -f'-like follow"
  echo "  -n N        only display the last N lines of the log"
  echo "              defaults to 'all' in non-follow and 10 in follow mode"
  echo ""
  echo "  -p PROGRAM  only display log messages of PROGRAM (exact match!); mainly useful for messages TABLE"
  echo "              applied before -n is evaluated"
  echo ""
  echo "  -e FILTER   only shows lines matching the FILTER regex"
  echo "              applied after -n is evaluated, i.e., only the last N lines will be matched against the regex"
  echo ""
  echo "  TABLE       messages --> general system messages (default)"
  echo "              audit    --> commands sent to spcli"
  echo "              firewall --> portfilter output"
  echo "              auth     --> authentication messages"
  echo "              cloud    --> cloud messages"
  echo ""
  echo ""
}

get_id() {
  while true; do
    id=$($1 "SELECT id FROM $2 ORDER BY id DESC LIMIT 1" 2>/dev/null)
    if [ -z "$id" ]; then
      sleep 1 
    else
      break;
    fi
  done
}


while getopts "hfn:p:e:" opt; do
  case $opt in
    f) follow=true
       ;;
    n) numlines=$OPTARG
       ;;
    p) program=$OPTARG
       ;;
    e) filter=$OPTARG
       ;;
    h) usage
       exit 0
       ;;
    \?) usage
        exit 1
        ;;
    :) usage
       exit 1
       ;;
  esac
done
shift $((OPTIND-1))

if [ ! -z "$1" ]; then
  table=$1
fi

case $table in
  messages|firewall|audit|auth|cloud) ;;
  *) usage "TABLE must be one of 'messages', 'audit', 'firewall', 'auth', 'cloud'"; exit 1 ;;
esac

if [ ! -z "$numlines" ]; then
  [ $numlines -eq $numlines 2> /dev/null ] || { usage "N must be an integer"; exit 1; }
fi

query="SELECT datetime, program, pid, message FROM "
p_cond=""
if [ ! -z "$program" ]; then
  p_cond=" WHERE program='$program' "
fi

if ! $follow; then

  ## simple mode, just get the requested rows and exit
  if [ -z "$numlines" ]; then
    query="$query $table $p_cond"
  else
    query="$query (SELECT * FROM $table $p_cond ORDER BY id DESC LIMIT $numlines) ORDER BY id ASC"
  fi

  if [ -z "$filter" ]; then
    $CMD "$query"
  else
    $CMD "$query" | grep -E "$filter"
  fi

else

  ## follow mode, periodically try to get any new DB entries
  if [ -z $numlines ]; then
    numlines=10
  fi
 
  get_id "$CMD" "$table"
  
  if [ $(($id - $numlines)) -lt 0 ]; then
    id=0
  else
    id=$(($id-$numlines))
  fi

  while true; do
    if [ -z "$program" ]; then
      loopquery="$query $table WHERE id > $id"
    else
      loopquery="$query $table $p_cond AND id > $id"
    fi

    ## get last entry
    get_id "$CMD" "$table"
    if [ -z "$filter" ]; then
      $CMD "$loopquery"
    else
      $CMD "$loopquery" | grep -E "$filter"
    fi
    sleep 0.1 2> /dev/null || usleep 100000 2> /dev/null || sleep 1
  done
fi
