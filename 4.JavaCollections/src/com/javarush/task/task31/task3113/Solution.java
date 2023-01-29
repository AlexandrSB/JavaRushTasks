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
        Path pathToDir = Paths.get(args[0]);
//        Files file = new Files(pathToDir);

        if (!Files.isDirectory(pathToDir)) {
            System.out.println(pathToDir.toAbsolutePath() + " - не папка");
            return;
        }


    }
}
