package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static java.util.Collections.min;
import static java.util.Collections.singletonList;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream fio;
        fio = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());

        HashMap<Integer, Integer> map = new HashMap<>();

        while (fio.available() > 0) {
            Integer item = fio.read();
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }

        fio.close();
        ArrayList<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values);
        Integer min;
        if (values.get(0) != 1) {
            min = values.get(0);
        } else {
            min = values.get(1);
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == min) {
                System.out.print(i+' ');
            }
        }
    }
}
