//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.garbagemule.MobArena.util;

import com.garbagemule.MobArena.MobArena;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class FileUT {
    public static void copy(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] array = new byte[1024];

            int read;
            while((read = inputStream.read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
            }

            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            MobArena.getInstance().getLogger().warning("Couldn't copy file properly: " + e.getMessage());
        }
    }

    @SuppressWarnings("all")
    public static void mkdir(File file) {
        try {
            file.mkdir();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    public static void create(File file) {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null)  {
                parent.mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException var3) {
                    var3.printStackTrace();
                }
            }
        }
    }

    public static List<File> getFiles(String path) {
        List<File> names = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    names.add(file);
                } else if (file.isDirectory()) {
                    names.addAll(getFiles(file.getPath()));
                }
            }

        }
        return names;
    }

    public static List<File> getFolders(String path) {
        List<File> dirs = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File f : listOfFiles) {
                if (f.isDirectory()) {
                    dirs.add(f);
                }
            }

        }
        return dirs;
    }

    public static boolean deleteRecursive(String path) {
        File dir = new File(path);
        return dir.exists() && deleteRecursive(dir);
    }

    public static boolean deleteRecursive(File dir) {
        File[] inside = dir.listFiles();
        if (inside != null) {
            for (File file : inside) {
                deleteRecursive(file);
            }
        }
        return dir.delete();
    }
}