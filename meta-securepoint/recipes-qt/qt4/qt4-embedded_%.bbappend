
DEPENDS = "openssl postgresql sqlite3 glib-2.0"
DEPENDS += "qt4-tools-native freetype jpeg libpng zlib openssl glib-2.0 sqlite3 tiff"

QT_EMBEDDED_FLAGS ?= " \
    -embedded $QT_ARCH \
    -qtlibinfix ${QT_LIBINFIX} \
"
QT_EXTRA_CONFIG_FLAGS = " \
 -plugin-sql-psql \
 -plugin-sql-sqlite \
 -qt-sql-psql \
 -qt-sql-sqlite \
 -system-sqlite \
 -openssl-linked \
 -no-qt3support \
 -nomake demos \
 -nomake examples \
 -nomake tools \
 -no-gui  \
 -no-phonon  \
 -no-dbus  \
 -no-cups  \
 -no-webkit  \
 -no-xmlpatterns  \
 -no-gfx-directfb  \
 -no-gfx-linuxfb  \
 -no-gfx-vnc  \
 -no-gfx-qvfb \
 -no-gfx-transformed \
 -no-gfx-multiscreen \
 -no-stl  \
 -no-multimedia  \
 -no-audio-backend \
 -no-mouse-qvfb \
 -no-mouse-tslib \
 -no-accessibility \
 -no-exceptions \
 -DQT_NO_CRASHHANDLER \
"

QT_CONFIG_FLAGS = "${QT_EMBEDDED_FLAGS} ${QT_EXTRA_CONFIG_FLAGS}"

#do_install() {
#	oe_runmake PREFIX=/usr DESTDIR=${D} install
#}
