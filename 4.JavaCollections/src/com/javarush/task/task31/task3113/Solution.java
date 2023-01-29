package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {

        SearchFileVisitor fileVisitor = new SearchFileVisitor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String dirName = reader.readLine();
        reader.close();

        Path pathToDir = Paths.get(dirName);

        if (!Files.isDirectory(pathToDir)) {
            System.out.println(pathToDir.toAbsolutePath() + " - не папка");
            return;
        }

        Files.walkFileTree(pathToDir, fileVisitor);

        int countFolders = fileVisitor.getFoundDirectories().size() - 1;

        System.out.println("Всего папок - " + countFolders);
        System.out.println("Всего файлов - " + fileVisitor.getFoundFiles().size());
        System.out.println("Общий размер - " + fileVisitor.getContentSize());
    }
}
