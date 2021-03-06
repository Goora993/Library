package pl.gooradev.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

public abstract class Publication implements Serializable {

    private int id;
    private String title;
    private String publisher;
    private Year releaseYear;
    private boolean borrowed;



    Publication(String title, String publisher, int releaseYear){
        this.title = title;
        this.publisher = publisher;
        this.releaseYear = Year.of(releaseYear);
}

    Publication(String title, String publisher, int releaseYear, int id, boolean borrowed){
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.releaseYear = Year.of(releaseYear);
        this.borrowed = borrowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id &&
                borrowed == that.borrowed &&
                Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(releaseYear, that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publisher, releaseYear, borrowed);
    }

    @Override
    public String toString() {
        String description = "ID: " + getId() + ", tytuł: " + getTitle()  + ", wydawnictwo: "
                + getPublisher() + ", rok wydania: " + getReleaseYear() + ", aktualny status: ";

        if (borrowed==true)
            return description + "wypożyczona";
        else
            return description + "niewypożyczona";
    }

    public abstract String toCsv();

}
