package com.example.abdullah.bookreader.helpers;

import android.os.Environment;

import com.example.abdullah.bookreader.data.models.FileModel;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    private ArrayList<String> extensions = new ArrayList<>(Arrays.asList("epub", "mobi", "pdf"));

    public FileManager() {}

    public List<String> getFilesInDir(String dir) {
        File parent = new File(dir);
        File[] files = parent.listFiles();
        List<String> subFiles = new ArrayList<>();
        String ext;
        if (files != null)
            for (File f : files) {
                if (f.isHidden()) {
                    continue;
                }
                ext = getFileExtension(f.getName());
                if (f.isDirectory() || validExtension(ext)) {
                    subFiles.add(f.getPath());
                }
            }

        return subFiles;
    }

    private static String getFileExtension(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return name.substring(index + 1);
    }

    private boolean validExtension(String ext) {
        if (extensions == null) {
            return true;
        }
        for (String s : extensions) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

}
