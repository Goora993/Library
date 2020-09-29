package pl.gooradev.library.model;

import java.time.MonthDay;
import java.util.Objects;

public class Magazine extends Publication {
    public static final String TYPE = "Magazyn";
    private String language;
    private MonthDay releaseMonthDay;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        super(title, publisher, year);
        this.language = language;
        this.releaseMonthDay = MonthDay.of(month, day);
    }

    public Magazine(String title, String publisher, String language, int year, int month, int day, int id, boolean borrowed) {
        super(title, publisher, year, id, borrowed);
        this.language = language;
        this.releaseMonthDay = MonthDay.of(month, day);
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MonthDay getReleaseMonthDay() {
        return releaseMonthDay;
    }

    public void setReleaseMonthDay(MonthDay releaseMonthDay) {
        this.releaseMonthDay = releaseMonthDay;
    }

    @Override
    public String toString() {
        String description = "ID: " + getId() + ", tytuł: " + getTitle() + ", język: " + getLanguage() + ", wydawnictwo: "
                + getPublisher() + ", data wydania: " + getReleaseYear() + "-" + getReleaseMonthDay().getMonthValue() + "-"
                + getReleaseMonthDay().getDayOfMonth()+ ", aktualny status: ";

        if (isBorrowed()==true)
            return description + "wypożyczona";
        else
            return description + "niewypożyczona";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(language, magazine.language) &&
                Objects.equals(releaseMonthDay, magazine.releaseMonthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language, releaseMonthDay);
    }


    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getReleaseYear() + ";" +
                getReleaseMonthDay().getMonthValue() + ";" +
                getReleaseMonthDay().getDayOfMonth() + ";" +
                language + ";" +
                getId() + ";" +
                isBorrowed() + "";
    }
}
