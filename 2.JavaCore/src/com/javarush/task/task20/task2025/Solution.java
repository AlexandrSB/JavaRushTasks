package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {
        Set<Long> tempResult = new TreeSet<>(); // Временный массив из объектов
        long[][] matrix; // Матрица степеней
        Long number = Long.valueOf(1); // Задаем первое число
        Long sumPows; // Сумма степеней числа

        boolean debug = false; // флаг отладки


        if (N < 0) {
            return null;
        }

        matrix = makeMatrix();

        while (number < N) {
            sumPows = getSumPowsNum(number, matrix);

            if (isArmstrong(number, sumPows, matrix)) {
                tempResult.add(sumPows);
            }

            ++number;
            number = numOk(number);
            //System.out.println("number = " + number);
        }


        return setToArray(tempResult);
    }

    public static Integer getCapacity(long N) {
        /** Разрядность числа */

        Long n = N;
        Integer capacity = 0;

        while (n > 0) {
            n = n / 10;
            capacity++;
        }

        return capacity;
    }

    public static Long pow(Integer value, Integer powValue) {
        /** Вычисляем степень(powValue) числа (value) */

        /*
        if (powValue == 1) {
            return Long.valueOf(value);
        } else if (powValue == 0 || value == 0) {
            return Long.valueOf(0);
        } else {
            return value * pow(value, powValue - 1);
        }
        */
        Long result = Long.valueOf(value);

        for (int i = 1; i < powValue; i++) {
            result *= value;
        }
        return result;
    }

    public static long[][] makeMatrix() {
        /** Создаем матрицу степеней чисел от 0 до 9 в степени от 2 до 19 */

        long[][] matrix = new long[10][20];

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 19; j++) {
                matrix[i][j] = pow(i, j);
            }
        }

        return matrix;
    }

    public static long getSumPowsNum(long N, long[][] matrix) {
        /** Находим сумму степеней числа */

        Long n = N;
        Long sum = (long) 0;
        int capacity = getCapacity(N);
        // Вызов внешней функции
        long[][] powMatrix = matrix;
        int k;

        while (n > 0) {
            k = (int) (n % 10);
            sum = sum + powMatrix[k][capacity];
            n = n / 10;
        }

        if (sum < 0) {
            return Long.MAX_VALUE;
        }

        return sum;
    }

    public static long numOk(Long N) {
        Integer capacity = getCapacity(N);
        List<Integer> numbers = new ArrayList<>(capacity);
        Long n = N;

        // Разложим число в массив
        while (n > 0) {
            numbers.add((int) (n % 10));
            n = n / 10;
        }

        // Если число больше Long.MAX_VALUE возвращаем LONG.MAX_VALUE
        if (numbers.size() == 19 && numbers.get(18) == 9) {
            return Long.MAX_VALUE;
        }

        // Проверяем чтобы числа в массиве шли по возрастанию
        if (numbers.size() == 1) {
            return N;
        }

        Integer lastNum = numbers.get(numbers.size()-2);
        Integer prevNum = 0;

        for (int num = numbers.size()-1; num > 0; num--) {

            if (num == 2 && numbers.get(0) == 1 && numbers.get(1) == 0) {
                numbers.set(1, numbers.get(num));
                numbers.set(0, 0);
                break;
            }

            if (lastNum == 0 && lastNum == numbers.get(0)) {
                if (num-2 >=0) {
                    lastNum = numbers.get(num - 2);
                }
                continue;
            }

            prevNum = numbers.get(num);

            if (prevNum > lastNum) {
                numbers.set(num-1, prevNum);
            }

            if (num-2 >=0) {
                lastNum = numbers.get(num - 2);
            }
        }

        // Преобразуем массив обратно в число (long)
        Long mult = Long.valueOf(1);

        for (int i = 0; i < capacity; i++) {
            n = Long.valueOf(numbers.get(i) * mult + n);
            mult *= 10;
        }

        return n;
    }

    public static Set<Integer> decomposeIntoArray(Long N) {
        Set<Integer> set = new TreeSet<>();

        while (N != 0) {
            set.add((int) (N % 10));
            N = N / 10;
        }

        return set;
    }

    public static boolean isArmstrong(Long N, Long sumPows, long[][] matrix) {
        Set<Integer> numSet = new TreeSet<>();
        Set<Integer> sumSet = new TreeSet<>();

        numSet = decomposeIntoArray(N);
        sumSet = decomposeIntoArray(sumPows);

        if (sumPows == getSumPowsNum(sumPows, matrix)) {
            return true;
        } else {
            return false;
        }
    }

    public static long[] setToArray(Set set) {
        long [] result = new long[set.size()];
        Iterator<Long> iter = set.iterator();

        for (int i = 0; i < set.size(); i++) {
            result[i] = iter.next();
        }

        return result;
    }

    public static String getCodePoint(String s) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        return ste.getFileName() + ":" +
                ste.getClassName() + ":" +
                ste.getMethodName() + ":" +
                ste.getLineNumber() + "\n" + s;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);


        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000_000_000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);


        /*
        List<Long> testArray = new ArrayList<>();
        testArray.addAll([1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727,
            93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208,
            472335975, 534494836, 912985153, 4679307774, 32164049650, 32164049651, 40028394225,
            42678290603, 44708635679, 49388550606, 82693916578, 94204591914, 28116440335967,
            4338281769391370, 4338281769391371, 21897142587612075, 35641594208964132,
            35875699062250035, 1517841543307505039, 3289582984443187032, 4498128791164624869,
            4929273885928088826])
         */
        
        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
