package com.javarush.task.task31.task3113;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private int contentSize = 0;
    private List<Path> foundFiles = new LinkedList<>();
    private List<Path> foundDirectories = new LinkedList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        if (attrs.isDirectory()) {
            foundDirectories.add(dir);
        }

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        contentSize += content.length;

            foundFiles.add(file);

        return super.visitFile(file, attrs);
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public List<Path> getFoundDirectories() {
        return foundDirectories;
    }

    public int getContentSize() {
        return contentSize;
    }

}
