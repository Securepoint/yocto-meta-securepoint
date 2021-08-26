# yocto-meta-securepoint
This is a meta layer for the yocto project buildsystem https://www.yoctoproject.org/

to build this software into a bootable image you need the yocto buildsystem setup with the following steps

* git clone -b ${branch} git://git.yoctoproject.org/poky.git
* cd poky
* git clone -b ${branch} git://git.openembedded.org/meta-openembedded meta-oe
* git clone -b ${branch} git://github.com/Securepoint/yocto-meta-securepoint meta-securepoint-public


after this steps are done enter the build environment

* source meta-securepoint-public/setup.sourceme
* you are now ready to build the known targets
*   bitbake test-image


see  https://www.yoctoproject.org/documentation
for Documentation of yocto, bitbake, openembedded etc.





