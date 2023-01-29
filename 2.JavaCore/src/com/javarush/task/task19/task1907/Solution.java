package com.javarush.task.task19.task1907;

import java.io.*;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        Integer counter = 0;

        try {
            fileName = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            while (file.ready()) {
                String[] strings = file.readLine().split("\\W");
                for (String s : strings) {
                    if (s.equals("world")) {
                        counter++;
                    }
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
