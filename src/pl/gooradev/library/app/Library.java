package pl.gooradev.library.app;

import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Book;

public class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.7";

        DataReader dataReader = new DataReader();

        Book[] books = new Book[1000];

        System.out.println("Wprowadź nową książkę: ");
        books[0] = dataReader.readAndCreateBook();
        books[1] = dataReader.readAndCreateBook();
        dataReader.close();


        System.out.println("Książki w bibliotece: ");
        for (Book book : books) {
            if(book!=null)
            System.out.println(book);
        }

        System.out.println("System może przechowywać " + books.length + " pozycji.");
    }

    private static void addBook() {

    }
}
