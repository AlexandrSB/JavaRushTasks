package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream reader = null;
        Integer counterCharacter = 0;
        Integer counterWhitespace = 0;

        try {
            reader = new FileInputStream(args[0]);

            while (reader.available() > 0) {
                counterCharacter++;
                Integer i = reader.read();
                if (i == 32) {
                    counterWhitespace++;
                }
            }
            System.out.printf("%.2f", (float) counterWhitespace / counterCharacter * 100);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
