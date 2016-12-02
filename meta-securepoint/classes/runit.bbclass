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
                ln -sf /etc/sv/${each} ${D}/var/service
            fi
        done
    fi
}

addtask add_runitfiles after do_fetch before do_unpack
python do_add_runitfiles(){
    svfiles = 'file://etc_sv_* file://etc_runit_1.d_*'
    localdata = bb.data.createCopy(d)
    bb.data.update_data(localdata)
    rootdir = localdata.getVar('WORKDIR', True)

    try:
        fetcher = bb.fetch2.Fetch(svfiles.split(), localdata)
        fetcher.unpack(rootdir)
        fetcher.download()
    except bb.fetch2.BBFetchException as e:
        raise bb.build.FuncFailed(e)
}

addtask move_runitfiles after do_install before do_populate_sysroot do_package
do_move_runitfiles() {
    echo "Entering move_runitfiles"
    install -d ${D}/var/service
    install_or_check_runit_files install_runit_file
}

addtask check_runitfiles after do_move_runitfiles before do_package
do_check_runitfiles() {
    echo "Entering check_runitfiles()"
    install_or_check_runit_files check_runit_file
}
