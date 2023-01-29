package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        Map<String, Double> map = new TreeMap<>();
        String[] s;


        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                s = reader.readLine().split(" ", 2);
                Double value = Double.parseDouble(s[1]);
                map.merge(s[0], value, (a, b) -> a+b);
            }

            for (Map.Entry<String, Double> entry:map.entrySet()) {
                if (entry.getValue().equals(Collections.max(map.values()))) {
                    System.out.println(entry.getKey());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
