package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {

        int count = 0;
        int size = a[0].length-1;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < size+1; j++) {
                if (a[i][j] == 1) {
                    if ((i < size && a[i+1][j] == 1)
                            || (j < size && a[i][j+1] == 1)) {
                        a[i][j] = 0;
                    }

                    if (a[i][j] == 1) {
                        count++;
                    }
                }

            }
        }

        return count;
    }
}
