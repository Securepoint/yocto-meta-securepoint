RUNIT_SERVICES = ""

# run script
RUN_FILEMODE="500"

# down, finish, check scripts
OTHER_FILEMODE="700"

# startup initialization scripts in /etc/runit/1.d/
SCRIPT_FILEMODE="500"

# to avoid code redundancy when checking the directories, the first parameter to this
# function is the action to execute (installer or check). Check simply ignores the
# third parameter passed to it.
install_or_check_runit_files() {
    check_runit_file() {
        # $1 mode
        # $2 path to check
        test "${1}" = $(stat -c "%a" ${2}) || { bbfatal "Incorrect permissions for file ${2}" && exit 1; }
    }

    install_runit_file() {
        # $1 mode
        # $2 destination path
        # $3 source path
        install -d $(dirname ${2})
        install -m 0${1} ${3} ${2}
    }

    if [ "${RUNIT_SERVICES}" != "" ]
    then
        for each in ${RUNIT_SERVICES}
        do
            $1 ${RUN_FILEMODE} ${D}/etc/sv/${each}/run ${WORKDIR}/etc_sv_${each}_run
            #check for log subdir
            if [[ -e ${WORKDIR}/etc_sv_${each}_log_run ]]; then
                $1 ${RUN_FILEMODE} ${D}/etc/sv/${each}/log/run ${WORKDIR}/etc_sv_${each}_log_run
            fi
            for file in check down finish
            do
                if [[ -f ${WORKDIR}/etc_sv_${each}_${file} ]]; then
                    $1 ${OTHER_FILEMODE} ${D}/etc/sv/${each}/${file} ${WORKDIR}/etc_sv_${each}_${file}
                fi
            done
            if [[ -f ${WORKDIR}/etc_runit_1.d_${each} ]]; then
                $1 ${SCRIPT_FILEMODE} ${D}/etc/runit/1.d/${each} ${WORKDIR}/etc_runit_1.d_${each}
            fi

            if [ "$1" = "install_runit_file" ]; then
                ln -sf /etc/sv/${each} ${D}/var/service/
            fi
        done
    fi
}

addtask fetch_runitfiles after do_fetch before do_unpack
python do_fetch_runitfiles() {
    import glob
    svfiles = 'etc_sv_* etc_runit_1.d_*'
    filespath = (d.getVar("FILESPATH") or "")
    localdata = bb.data.createCopy(d)
    bb.data.update_data(localdata)
    rootdir = localdata.getVar('WORKDIR', True)
    srcuri = ''

    for path in filespath.split(':'):
        for each in svfiles.split():
            fullpath = path + '/' + each
            for match in (glob.glob(fullpath)):
                srcfile = os.path.basename(match)
                srcurientry = 'file://%s' % srcfile
                # Double-check it's not there already
                if not srcurientry in srcuri.split():
                    bb.note("srcuri: appending '%s'" % srcurientry)
                    srcuri = '%s %s' % (srcuri, srcurientry)

    bb.note("srcuri: '%s'" % srcuri)

    try:
        fetcher = bb.fetch2.Fetch(srcuri.split(), localdata)
        fetcher.unpack(rootdir)
        fetcher.download()
    except bb.fetch2.BBFetchException as e:
        bb.fatal(str(e))
}

# https://docs.yoctoproject.org/ref-manual/tasks.html#do-install
#
# > The do_install task, as well as other tasks that either directly or
# > indirectly depend on the installed files (e.g. do_package,
# > do_package_write_*, and do_rootfs), run under fakeroot.
#
# seems to be a goot idea, to run our tasks under fakeroot too.
addtask move_runitfiles after do_install before do_populate_sysroot do_package
fakeroot do_move_runitfiles() {
    echo "Entering move_runitfiles"
    install -m 0700 -d ${D}/var/service
    install_or_check_runit_files install_runit_file
}

addtask check_runitfiles after do_move_runitfiles before do_package
fakeroot do_check_runitfiles() {
    echo "Entering check_runitfiles()"
    install_or_check_runit_files check_runit_file
}
