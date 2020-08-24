package pl.gooradev.library.model;

import java.util.Objects;

public class Magazine extends Publication {
    public static final String TYPE = "Magazyn";
    private String language;
    private int month;
    private int day;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        super(title, publisher, year);
        this.language = language;
        this.month = month;
        this.day = day;
    }

    public Magazine(String title, String publisher, String language, int year, int month, int day, long id) {
        super(title, publisher, year, id);
        this.language = language;
        this.month = month;
        this.day = day;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Tytuł: " + getTitle() + ", język: " + getLanguage() + ", wydawnictwo: "
                + getPublisher() + ", data wydania: " + getYear() + "-" + getMonth() + "-" + getDay()
                + ", ID: " + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return month == magazine.month &&
                day == magazine.day &&
                Objects.equals(language, magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, month, day);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getYear() + ";" +
                month + ";" +
                day + ";" +
                language + ";" +
                getId() + "";
    }
}
