package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream input = null;
        FileOutputStream out = null;

        ArrayList<Integer> bytes = new ArrayList<>();

        try {
            input = new FileInputStream(reader.readLine());
            out = new FileOutputStream(reader.readLine());

            while (input.available() > 0) {
                bytes.add(input.read());
            }

            Collections.reverse(bytes);
            
            for (Integer aByte : bytes) {
                out.write(aByte);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }
}
