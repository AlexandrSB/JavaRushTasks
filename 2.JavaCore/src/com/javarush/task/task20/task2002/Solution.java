package com.javarush.task.task20.task2002;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

            User user = new User();
            user.setFirstName("FirstName");
            user.setLastName("LastName");
            user.setBirthDate(dateFormat.parse("11-04-1998"));
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);

            User user1 = new User();
            user1.setFirstName("User2_FirstName");
            user1.setLastName("User2_LastName");
            user1.setBirthDate(dateFormat.parse("24-12-1988"));
            user1.setMale(false);
            user1.setCountry(User.Country.OTHER);

            javaRush.users.add(user);
            javaRush.users.add(user1);

            System.out.println("Begin save users");
            javaRush.save(outputStream);
            outputStream.flush();

            System.out.println("Begin load users");
            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            if (users.size() > 0) {
                for (User user : this.users) {
                    user.save(outputStream);
                    outputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                }
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            User user = null;
            String str = null;

            while (reader.ready()) {
                str = reader.readLine();
                if (str != "") {
                    users.add(User.load(str, ";"));
                } else {
                    users = new ArrayList<>();
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
