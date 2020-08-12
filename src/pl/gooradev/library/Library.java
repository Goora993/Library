package pl.gooradev.library;

import pl.gooradev.library.model.Book;

public class Library {
    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setAuthor("Henryk Sienkiewicz");
        book.setReleaseDate(2000);
        book.setPages(350);
        book.setPublisher("Wydawnictwo Greg");
        book.setIsbn("13572468");

        System.out.println("Książki w bibliotece: ");
        System.out.println(book);
    }
}
