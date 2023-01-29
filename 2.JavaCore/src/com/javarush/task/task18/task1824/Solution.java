package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String fileName = reader.readLine();
            try (FileInputStream file = new FileInputStream(fileName)){
                file.read();
            } catch (FileNotFoundException exception) {
                System.out.println(fileName);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
