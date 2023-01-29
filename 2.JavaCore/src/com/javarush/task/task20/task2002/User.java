package com.javarush.task.task20.task2002;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean isMale;
    private Country country;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static enum Country {
        UKRAINE("Ukraine"),
        RUSSIA("Russia"),
        OTHER("Other");

        private String name;

        private Country(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return this.name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isMale != user.isMale) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        return country == user.country;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (isMale ? 1 : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public void save(OutputStream outputStream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));


        outputStream.write(this.getFirstName().getBytes(StandardCharsets.UTF_8));
        outputStream.write(";".getBytes(StandardCharsets.UTF_8));

        outputStream.write(this.getLastName().getBytes(StandardCharsets.UTF_8));
        outputStream.write(";".getBytes(StandardCharsets.UTF_8));

        outputStream.write(Long.toString(this.getBirthDate().getTime()).getBytes(StandardCharsets.UTF_8));
        outputStream.write(";".getBytes(StandardCharsets.UTF_8));

        outputStream.write(String.valueOf(this.isMale()).getBytes(StandardCharsets.UTF_8));
        outputStream.write(";".getBytes(StandardCharsets.UTF_8));

        outputStream.write(String.valueOf(this.getCountry().getDisplayName()).
                getBytes(StandardCharsets.UTF_8));
    }

    public static User load(String s, String delim) {
        User user = new User();
        String[] strings = null;

        strings = s.split(delim);
        try {
            user.firstName = strings[0].trim();
            user.lastName = strings[1].trim();
            user.birthDate = new Date(Long.parseLong(strings[2].trim()));
            user.setMale(Boolean.parseBoolean(strings[3].trim()));
            switch (strings[4]) {
                case "Russia": user.setCountry(Country.RUSSIA); break;
                case "Ukraine": user.setCountry(Country.UKRAINE); break;
                case "Other": user.setCountry(Country.OTHER); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }
}
