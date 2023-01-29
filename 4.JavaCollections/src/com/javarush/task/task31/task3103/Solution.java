package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
        java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        return b;
    }

    public static List<String> readLines(String fileName) throws IOException {
        java.io.BufferedReader bure = new java.io.BufferedReader(new java.io.FileReader(fileName));
        List<String> list = new java.util.ArrayList<>();


        while (bure.ready()) {
            list.add(bure.readLine());
        }
        return list;
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        java.io.FileOutputStream fos = new java.io.FileOutputStream(fileName);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        java.io.InputStream is = null;
        java.io.OutputStream os = null;

        try {
            is = new java.io.FileInputStream(resourceFileName);
            os = new java.io.FileOutputStream(destinationFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
