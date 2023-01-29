package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
        String path = args[0];
        File resultFileAbsolutePath = new File(args[1]);
        File newFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        List<String> files;


        if (FileUtils.isExist(resultFileAbsolutePath)) {
            FileUtils.renameFile(resultFileAbsolutePath, newFile);
        }


        try {
            files = getFileTree(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (String f : files) {
            try (FileOutputStream writer = new FileOutputStream(newFile, true)) {
                File file = new File(f);
                Path pathe = Paths.get(f);
                if (file.length() <= 50) {
                    writer.write(Files.readAllBytes(pathe));
                    writer.write("\n".getBytes());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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

}
