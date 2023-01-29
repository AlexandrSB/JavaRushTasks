package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file_1 = null;
        File file_2 = null;

        try {
            file_1 = new File(reader.readLine());
            file_2 = new File(reader.readLine());
            reader.close();

            BufferedReader fileReader = new BufferedReader(new FileReader(file_1));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file_2));

            String result = fileReader.lines()
                    .flatMap(s -> Arrays.stream(s.split("\\s+")))
                    .filter(s -> s.matches("\\d+"))
                    .collect(Collectors.joining(" "));

            fileWriter.write(result);

            fileReader.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
