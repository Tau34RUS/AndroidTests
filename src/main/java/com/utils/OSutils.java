package com.utils;

import AlphaTests.OsUtils;

import java.io.IOException;
import java.util.Locale;

public class OSutils {
    public enum OS {
        WINDOWS,
        UNIX,
        POSIX_UNIX,
        MAC,
        OTHER;

        private String version;

        public String getVersion() {

            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    private static OsUtils.OS os;

    static {
        os = OsUtils.OS.OTHER;
        try {
            String osName = System.getProperty("os.name");
            if (osName == null) {
                throw new IOException("os.name not found");
            }
            osName = osName.toLowerCase(Locale.ENGLISH);
            if (osName.contains("windows")) {
                os = OsUtils.OS.WINDOWS;
            } else if (osName.contains("linux")
                    || osName.contains("mpe/ix")
                    || osName.contains("freebsd")
                    || osName.contains("irix")
                    || osName.contains("digital unix")
                    || osName.contains("unix")) {
                os = OsUtils.OS.UNIX;
            } else if (osName.contains("mac os")) {
                os = OsUtils.OS.MAC;
            } else if (osName.contains("sun os")
                    || osName.contains("sunos")
                    || osName.contains("solaris")) {
                os = OsUtils.OS.POSIX_UNIX;
            } else if (osName.contains("hp-ux")
                    || osName.contains("aix")) {
                os = OsUtils.OS.POSIX_UNIX;
            } else {
                os = OsUtils.OS.OTHER;
            }

        } catch (Exception ex) {
            os = OsUtils.OS.OTHER;
        } finally {
            os.setVersion(System.getProperty("os.version"));
        }
    }

    public static OsUtils.OS getOs() {

        return os;
    }
}