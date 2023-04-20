# build with widechar support
EXTRA_OECONF:append="--with-ncursesw"
PACKAGECONFIG[x11] = "--with-x --x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR},,virtual/libx11"