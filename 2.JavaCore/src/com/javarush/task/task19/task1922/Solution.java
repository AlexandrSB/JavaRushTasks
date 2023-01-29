package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        Integer counter = 0;
        String[] str_array;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        while (fileReader.ready()) {
            str_array = fileReader.readLine().split(" ");
            for (String s : str_array) {
                //s = s.toLowerCase();
                for (String word : words) {
                    if (s.equals(word)) {
                        counter++;
                    }
                    if (counter > 2) {
                        break;
                    }
                }
            }

            if (counter == 2) {
                System.out.println(String.join(" ", str_array));
            }

            counter = 0;

        }

        fileReader.close();
    }
}
