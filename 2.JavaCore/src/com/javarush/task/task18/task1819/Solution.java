package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file_1 = new File(reader.readLine());
        File file_2 = new File(reader.readLine());
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file_1))){
            BufferedInputStream bis_1 = new BufferedInputStream(new FileInputStream(file_1));
            BufferedInputStream bis_2 = new BufferedInputStream(new FileInputStream(file_2));

            byte[] byte_1 = new byte[bis_1.available()];
            byte[] byte_2 = new byte[bis_2.available()];

            bis_1.read(byte_1);
            bis_2.read(byte_2);

            bos.write(byte_2);
            bos.write(byte_1);

            bis_1.close();
            bis_2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
