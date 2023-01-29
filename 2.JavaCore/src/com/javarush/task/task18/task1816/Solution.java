package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        Integer counter = 0;
        int i;
        while ((i = fileInputStream.read()) != -1) {
            if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122 ) ) {
                counter++;
            }
        }
        fileInputStream.close();
        System.out.println(counter);
    }
}
