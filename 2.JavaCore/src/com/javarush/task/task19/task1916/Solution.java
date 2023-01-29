package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String file1 = null;
        String file2 = null;

        ArrayDeque<String> file1Contents = new ArrayDeque<>();
        ArrayDeque<String> file2Contents = new ArrayDeque<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            file1 = reader.readLine();
            file2 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file1))) {
            while (fileReader.ready()) {
                file1Contents.add(fileReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file2))) {
            while (fileReader.ready()) {
                file2Contents.add(fileReader.readLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String f1, f2;
        while (file1Contents.size() > 0 || file2Contents.size() > 0) {
            f1 = file1Contents.poll();
            f2 = file2Contents.poll();

            if (f1 != null && f2 != null) {
                if (f1.equals(f2)) {
                    lines.add(new LineItem(Type.SAME, f1));
                } else if (f2 != null && f2.equals(file1Contents.peek())) {
                    lines.add(new LineItem(Type.REMOVED, f1));
                    file2Contents.push(f2);
                } else if (f1 != null && f1.equals(file2Contents.peek())) {
                    lines.add(new LineItem(Type.ADDED, f2));
                    file1Contents.push(f1);
                }
            } else if (f2 == null) {
                lines.add(new LineItem(Type.REMOVED, f1));
            } else if (f1 == null) {
                lines.add(new LineItem(Type.ADDED, f2));
            }
        }

        for (LineItem s : lines) {
            System.out.println(s.toString());
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + " " + line;
        }
    }
}
