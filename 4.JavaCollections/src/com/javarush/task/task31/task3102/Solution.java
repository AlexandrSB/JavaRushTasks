package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    private static List<String> joinPathAndFile(File file) {
        List<String> strings = new ArrayList<>();


        for (File f : file.listFiles()) {
            strings.add(String.valueOf(f));
        }

        return strings;
    }

    public static List<String> getFileTree(String root) throws IOException {
        Queue<String> queueFiles = new LinkedList<>();
        List<String> files = new ArrayList<>();
        File file = new File(root);


        if (!file.exists()) {
            files.add("");
            System.out.println("File not found!");
            return files;
        }

        queueFiles.add(file.getAbsolutePath());

        while (queueFiles.size() != 0) {
            file = new File(queueFiles.remove());
            if (file.isDirectory()) {
                queueFiles.addAll(joinPathAndFile(file));
            } else if (file.isFile()) {
                files.add(file.getAbsolutePath());
            }

        }

        return files;
    }

    public static void main(String[] args) throws IOException {
        List<String> str = new ArrayList<>(getFileTree("E:\\ISO"));
        System.out.println("Count of files is " + str.size()+"\n");
        str.forEach(file -> {
            System.out.println(file);
        });
    }
}
