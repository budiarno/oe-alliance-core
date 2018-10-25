SUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINEBUILD}"

SRCDATE = "20180917"
SRCDATE_openspa = "20181001"
KV = "3.14.28"
PV = "${KV}+${SRCDATE}"
PR = "r0"

SRC_URI[md5sum] = "bd437f51600f126eba4f66f55a3888f8"
SRC_URI[sha256sum] = "4b7c2e015ceb28c74991662c06a769d054c96cd8449b4c0d67fb1d0bd5587aec"
SRC_URI[openspa.md5sum] = "866c3b336005157ddd17249226ebe701"
SRC_URI[openspa.sha256sum] = "25f1818ea20faae5dafa787a46a297fd471b0cd0231e3b718bc267b4390981b1"

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/bcmlinuxdvb_7252S-300mm-${KV}-${SRCDATE}.tar.gz"
SRC_URI_openspa = "https://openspa.webhop.info/drivers/qviart/openspa_bcmlinuxdvb_7252S-300mm-${KV}-${SRCDATE_openspa}.tar.gz;name=openspa"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

do_populate_sysroot[noexec] = "1"

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    install -m 0644 ${WORKDIR}/lib/modules/${KV}/extra/bcmlinuxdvb.ko ${D}/lib/modules/${KV}/extra/bcmlinuxdvb_${MACHINEBUILD}.ko
    install -d ${D}/${sysconfdir}/modules-load.d
    echo bcmlinuxdvb_${MACHINEBUILD} _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINEBUILD}.conf
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINEBUILD}.conf"
