package com.javarush.task.task19.task1918;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) {
        String text;
        String tag = args[0].trim();

        List<String> list = new LinkedList<>();

        String regex = "<" + tag + "[\\s|>]|<[/]" + tag + ">";


        text = joinString();
        text = text.substring(text.indexOf("<" + tag),
                text.lastIndexOf("</" + tag + ">") + 3 + tag.length());
//                replaceAll("\\s{2,}", "");

        Matcher matcher = Pattern.compile(regex).matcher(text);

        tagsToList(matcher, text, list);
        list.forEach(System.out::println);
    }

    private static String joinString() {
        // Читаем имя файла
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()))) {
            if (fileReader.ready()) {
                return fileReader.lines().collect(Collectors.joining(""));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private static int tagsToList(Matcher matcher, String text, List<String> list) {

        while (matcher.find()) {
            if (!matcher.group().contains("</")) {//found a head of tag
                list.add("");//reserve place for a sequence of tags
                list.set(list.size() - 1,
                        text.substring(matcher.start(),
                                tagsToList(matcher, text, list)));
            } else //tag tail found, returns end position of tag sequence
                return matcher.end();
        }

        return 0;
    }
}
