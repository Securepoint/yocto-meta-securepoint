#!/bin/sh

a=""
b=""

for i in `ls -1 .`
do 
  test -d $i || continue
  a="$a \${PN}_lang_$i"
  b="$b\nFILES_\${PN}_lang_$i = \"/usr/share/errors/$i\""
done

echo "PACKAGES += \"$a\""
echo -e "$b"
