dnl Check for PCRE

want_pcre=""
dnl handle the --with-pcre flag
AC_ARG_WITH([pcre],
[AS_HELP_STRING([--with-pcre@<:@=DIR@:>@], [path to directory containing libpcre library
                @<:@default=/usr/local or /usr if not found in /usr/local@:>@])],
[
  want_pcre=$withval
],
[
dnl default ON if present
  want_pcre="yes"
])

if test "X$want_pcre" != "Xno"; then
  PKG_CHECK_MODULES(PCRE, [libpcre], [have_pcre=yes], [have_pcre=no])
fi

if test "$have_pcre" = "yes"; then
  ML_CPPFLAGS="$PCRE_CFLAGS"
  AC_DEFINE([HAVE_PCRE],1,[Define to 1 if you have the 'libpcre' library (-lpcre).])
  AC_SUBST(PCRE_CPPFLAGS)
  AC_SUBST(PCRE_LIBS)
  AC_MSG_NOTICE([Compiling and linking with libpcre])
else
  if test "$want_pcre" = "yes"; then
     AC_MSG_ERROR([****** Please install libpcre packages!])
  else
    if test "$want_pcre" != "no"; then
      AC_MSG_NOTICE([****** libpcre support unavailable])
    fi
  fi
  PCRE_CPPFLAGS=""
  PCRE_LIBS=""
  AC_SUBST(PCRE_CPPFLAGS)
  AC_SUBST(PCRE_LIBS)
fi

dnl AM_CONDITIONAL([HAVE_PCRE], test "x$HAVE_PCRE" = "xyes")
