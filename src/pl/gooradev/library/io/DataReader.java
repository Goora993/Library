package pl.gooradev.library.io;

import pl.gooradev.library.model.Book;

import java.util.Scanner;

public class DataReader {
    Scanner sc = new Scanner(System.in);

    public void close() {
        sc.close();
    }

    public Book readAndCreateBook() {
        System.out.println("Tytuł: ");
        String title = sc.nextLine();
        System.out.println("Autor: ");
        String author = sc.nextLine();
        System.out.println("Wydawnictwo: ");
        String publisher = sc.nextLine();
        System.out.println("ISBN: ");
        String isbn = sc.nextLine();
        System.out.println("Rok wydania: ");
        int releaseDate = getInt();
        System.out.println("Ilość stron: ");
        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public int getInt(){
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }




}
