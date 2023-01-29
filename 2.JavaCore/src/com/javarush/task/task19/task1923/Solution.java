package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static boolean isStringContainsDigits(String string) {
        Pattern p = Pattern.compile("\\S*\\d\\S*");
        if (string == null) {
            return false;
        }
        Matcher matchder = p.matcher(string);
        return matchder.matches();
    }

    public static void main(String[] args) {
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(args[0]));
            writer = new FileWriter(args[1]);
            String[] strArray;

            while (reader.ready()) {
                strArray = reader.readLine().split("\\s");
                for (String s : strArray) {
                    if (isStringContainsDigits(s)) {
                        writer.write(s+" ");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
