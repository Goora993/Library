package pl.gooradev.library.model;

import java.util.Objects;

public class Book extends Publication {
    public static final String TYPE = "Książka";
    private String author;
    private int pages;
    private String isbn;


    public Book(String title, String publisher, int year, String author, int pages, String isbn){
        super(title, publisher, year);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public Book(String title, String publisher, int year, String author, int pages, String isbn, int id, boolean borrowed){
        super(title, publisher, year, id, borrowed);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        String description = "ID: " + getId() + ", tytuł: " + getTitle() + ", autor: " + getAuthor() + ", ilość stron " + getPages() + ", wydawnictwo: "
                + getPublisher() + ", rok wydania: " + getYear() + ", ISBN: " + getIsbn()
                + ", aktualny status: ";

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
        Book book = (Book) o;
        return pages == book.pages &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, pages, isbn);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String toCsv() {
        return  (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getYear() + ";" +
                author + ";" +
                pages + ";" +
                isbn + ";" +
                + getId() + ";" +
                isBorrowed() + "";
    }
}
