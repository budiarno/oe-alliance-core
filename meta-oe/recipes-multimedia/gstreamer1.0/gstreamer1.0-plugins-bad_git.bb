DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV_base = "f106a109941347ec7d7ff34c422889161f94f93c"
SRCREV_common = "cd1dee06bf07f094677d0cf3eea4a2e8c2636b24"
SRCREV_FORMAT = "base"

SRC_URI = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=master;name=base \
           git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
"

SRC_URI += " \
			file://configure-allow-to-disable-libssh2.patch \
			file://fix-maybe-uninitialized-warnings-when-compiling-with-Os.patch \
			file://avoid-including-sys-poll.h-directly.patch \
			file://ensure-valid-sentinels-for-gst_structure_get-etc.patch \
			file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
			\
			file://0001-rtmp-fix-seeking-and-potential-segfault.patch \
			file://0004-rtmp-hls-tsdemux-fix.patch \
			file://dvbapi5-fix-old-kernel.patch \
			file://hls-main-thread-block.patch \
			"
S = "${WORKDIR}/git"

GST_VERSION_FULL ="1.14.4"
inherit gitpkgv
PV = "${GST_VERSION_FULL}+git${SRCPV}"
PKGV = "${GST_VERSION_FULL}+git${GITPKGV}"

EXTRA_OECONF += " \
    --disable-openjpeg \
    "

TARGET_CFLAGS_append = " -Wno-error=maybe-uninitialized -Wno-error=redundant-decls"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}
