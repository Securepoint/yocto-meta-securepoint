# this append is to remove the yocto included bitops patch for lua
# the patch is not compatible with some libs (like lpeg)
# bitops is built as an external lib from lua-jit 
# and set as Dependency to lua-5.1

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#RDEPENDS_${PN} += "luabitop"
