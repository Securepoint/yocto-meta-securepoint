# build without xml support since we don't use xar @ all
PACKAGECONFIG = "zlib bz2"
# Doooh! libxml2, nei, nei, nei
PACKAGECONFIG:remove:class-target = "libxml2"
