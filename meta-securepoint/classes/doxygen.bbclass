

# If doxygen run is desired, do additional call to make with doxygen target "srcdoc"
# and copy the generated stuff to the desired location.
# We make this dependent on the existence of a GEN_DOCUMENTATION variable 
# Additionally, this class uses a make target name and a target dir

DEPENDS_append = " doxygen-native"

do_compile_append() {
    if [ -z ${DOXY_OUTPUT_DIR} ] || [ -z ${DOXY_MAKE_TARGET} ]; then
        echo "DOXY_OUTPUT_DIR and DOXY_MAKE_TARGET need to be specified for each recipe using doxygen if GEN_DOCUMENTATION is set"
        exit -1
    fi 
    if [ -n "${GEN_DOCUMENTATION}" ]; then
        if [ ! -d ${GEN_DOCUMENTATION} ]; then
            mkdir -p ${GEN_DOCUMENTATION}
        fi
        ${MAKE} ${DOXY_MAKE_TARGET}
        output_dir=${GEN_DOCUMENTATION}/${PN} 
        mkdir -p ${output_dir} 
        cp -r ${B}/${DOXY_OUTPUT_DIR}/* ${output_dir}
    else
        echo "No dir for documentation output specified"
    fi
}


