package astro.astro.util;

import java.io.*;
import java.lang.reflect.Field;

public final class CefLibPath {

        public static void setPath() throws Exception {

            addLibraryDir("./PROJECT/CEFLIB");

        }

        private static void addLibraryDir(String libraryPath) throws Exception {
            Field userPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            userPathsField.setAccessible(true);
            String[] paths = (String[]) userPathsField.get(null);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                if (libraryPath.equals(paths[i])) {
                    continue;
                }
                sb.append(paths[i]).append(File.pathSeparatorChar);
            }
            sb.append(libraryPath);
            System.setProperty("java.library.path", sb.toString());
            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
        }


    }

