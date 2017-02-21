require libvncserver.inc
DEPENDS = "cmake-native"

inherit native cmake pkgconfig

EXTRA_OECMAKE += "\
    -DWITH_TESTS=ON \
"
DEPENDS = "cmake-native"

do_compile_append() {
  ctest  
}


