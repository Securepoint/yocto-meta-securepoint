# Requires the project to provide a make target to build documentation
# with doxygen. Variables to customize behavior:
#  GEN_DOCUMENTATION
#     generated documentation will be copied to ${GEN_DOCUMENTATION}/${PN}
#     if not set, no documentation will be generated
#  DOXY_MAKE_TARGET
#     target provided by project
#  DOXY_OUTPUT_DIR
#     location of documentation files

DEPENDS:append = " doxygen-native"

DOXY_OUTPUT_DIR ??= "doc"
DOXY_MAKE_TARGET ??= "srcdoc"

doxygen_do_documentation() {
    if [ -n "${GEN_DOCUMENTATION}" ]; then
        if [ -z ${DOXY_OUTPUT_DIR} ] || [ -z ${DOXY_MAKE_TARGET} ]; then
            echo "DOXY_OUTPUT_DIR and DOXY_MAKE_TARGET need to be specified for each recipe using doxygen if GEN_DOCUMENTATION is set"
            exit -1
        fi
        if [ ! -d ${GEN_DOCUMENTATION} ]; then
            mkdir -p ${GEN_DOCUMENTATION}
        fi
        bbnote "trying to run ${MAKE} ${DOXY_MAKE_TARGET}"
        cd ${B}
        ${MAKE} ${DOXY_MAKE_TARGET}
        output_dir=${GEN_DOCUMENTATION}/${PN}
        mkdir -p ${output_dir}
        cp -r ${DOXY_OUTPUT_DIR}/* ${output_dir}
    else
        echo "No dir for documentation output specified"
    fi
}

addtask do_documentation after do_configure

EXPORT_FUNCTIONS do_documentation
