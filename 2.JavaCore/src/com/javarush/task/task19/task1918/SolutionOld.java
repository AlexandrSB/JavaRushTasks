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

public class SolutionOld {
    public static void main(String[] args) {
        String text;
        String tag = args[0].trim();

        List<String> list = new LinkedList<>();

//        String regex = "(<" + tag + ".*?>)(([^<>]*)|(<.*?>))(</" + tag + ">)";
        String regex = "<" + tag + "[\\s|>]|<[/]" + tag + ">";


        text = joinString();
        text = text.substring(text.indexOf("<" + tag),
                        text.lastIndexOf("</" + tag + ">") + 3 + tag.length()).
                replaceAll("\\s{2,}", "");

        Matcher matcher = Pattern.compile(regex).matcher(text);

        tagsToList(matcher, text, list);
        list.forEach(System.out::println);

//        for (String s : splitIncludingDelimeters(regex, text)) {
//            System.out.println(s);
//        }
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

    // https://ru.stackoverflow.com/questions/1036927/split-%D0%BF%D0%BE-%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%BE%D0%B2%D0%BE%D0%BC%D1%83-%D0%BB%D0%B8%D1%82%D0%B5%D1%80%D0%B0%D0%BB%D1%83-%D0%B1%D0%B5%D0%B7-%D1%83%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F-%D1%80%D0%B0%D0%B7%D0%B4%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8F
    private static List<String> splitIncludingDelimeters(String regex, String text) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        List<String> list = new LinkedList<>();

        while(matcher.find()){
//            System.out.println("matcher.group(): " + matcher.group());
            list.add(matcher.group());
            list.addAll(splitIncludingDelimeters(regex,
                    matcher.group().substring(1, matcher.group().length()-1)));
        }

        return list;
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