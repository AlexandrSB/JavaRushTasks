package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            reader.close();
            while (true) {
                if (!fileReader.ready()) break;
                str = fileReader.readLine();
                System.out.println(new StringBuilder(str).reverse().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
