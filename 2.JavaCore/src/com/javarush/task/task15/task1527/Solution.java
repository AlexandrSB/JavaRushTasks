package com.javarush.task.task15.task1527;

/* 
Парсер реквестов
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Double> alertDouble = new ArrayList<>();
        List<String> alertString = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        try {
            String url = reader.readLine();
            String[] strArray = url.substring(url.indexOf('?')).split("&");
            reader.close();

            for (String s : strArray) {
                if (s.equals("obj")) {
                    if (pattern.matcher(s).matches()) {
                        alertDouble.add(Double.parseDouble(s));
                    } else {
                        alertString.add(s);
                    }
                    alertDouble.forEach(num -> alert(num));
                    alertString.forEach(str -> alert(str));
                }
                System.out.print(s+"\\s");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
