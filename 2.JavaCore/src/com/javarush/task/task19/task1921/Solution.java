package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        String str = null;
        String name = null;
        String birthday = null;
        Integer index = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);


        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            str = reader.readLine();
            index = str.lastIndexOf(" ");
            index = str.lastIndexOf(" ", index-1);
            index = str.lastIndexOf(" ", index-1);
            name = str.substring(0, str.lastIndexOf(" ", index)).trim();
            birthday = str.substring(str.lastIndexOf(" ", index), str.length()).trim();
            PEOPLE.add(new Person(name, dateFormat.parse(birthday)));

            System.out.println(name);
            System.out.println(birthday);
        }
        reader.close();
    }
}
