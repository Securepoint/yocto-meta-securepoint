dnl Check for CURL

want_curl=""
dnl handle the --with-curl flag
AC_ARG_WITH([curl],
[AS_HELP_STRING([--with-curl@<:@=DIR@:>@], [path to directory containing libcurl library
                @<:@default=/usr/local or /usr if not found in /usr/local@:>@])],
[
  want_curl=$withval
],
[
dnl default ON if present
  want_curl="yes"
])

if test "X$want_curl" != "Xno"; then
  PKG_CHECK_MODULES(CURL, [libcurl], [have_curl=yes], [have_curl=no])
fi

if test "$have_curl" = "yes"; then
  ML_CPPFLAGS="$CURL_CFLAGS"
  AC_DEFINE([HAVE_CURL],1,[Define to 1 if you have the 'libcurl' library (-lcurl).])
  CLAMSUBMIT_LIBS="$CLAMSUBMIT_LIBS $CURL_LDFLAGS $CURL_LIBS";
  CLAMSUBMIT_CFLAGS="$CLAMSUBMIT_CFLAGS $CURL_CPPFLAGS";
  FRESHCLAM_LIBS="$FRESHCLAM_LIBS $CURL_LDFLAGS $CURL_LIBS";
  FRESHCLAM_CPPFLAGS="$FRESHCLAM_CPPFLAGS $CURL_CPPFLAGS"
  AC_SUBST(CURL_CPPFLAGS)
  AC_SUBST(CURL_LIBS)
  AC_MSG_NOTICE([Compiling and linking with libcurl])
else
  if test "$want_curl" = "yes"; then
     AC_MSG_ERROR([****** Please install libcurl packages!])
  else
    if test "$want_curl" != "no"; then
      AC_MSG_NOTICE([****** libcurl support unavailable])
    fi
  fi
  CURL_CPPFLAGS=""
  CURL_LIBS=""
  AC_SUBST(CURL_CPPFLAGS)
  AC_SUBST(CURL_LIBS)
fi

AC_SUBST([FRESHCLAM_LIBS])
AC_SUBST([FRESHCLAM_CFLAGS])
AC_SUBST([CLAMSUBMIT_LIBS])
AC_SUBST([CLAMSUBMIT_CFLAGS])
