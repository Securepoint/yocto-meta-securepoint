PACKAGECONFIG = ""
DEPENDS:remove = "alsa-lib"
EXTRA_OECMAKE:remove = " -DWITH_ALSA=ON"
EXTRA_OECMAKE:append = " -DWITH_ALSA=OFF"