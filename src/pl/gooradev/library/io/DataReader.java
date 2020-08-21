package pl.gooradev.library.io;

import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Magazine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    Scanner sc = new Scanner(System.in);
    ConsolePrinter consolePrinter;

    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void close() {
        sc.close();
    }

    public Book readAndCreateBook() {
        consolePrinter.printLine("Tytuł: ");
        String title = sc.nextLine();
        consolePrinter.printLine("Autor: ");
        String author = sc.nextLine();
        consolePrinter.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        consolePrinter.printLine("ISBN: ");
        String isbn = sc.nextLine();
        consolePrinter.printLine("Rok wydania: ");
        int year = getInt();
        consolePrinter.printLine("Ilość stron: ");
        int pages = getInt();

        return new Book(title, publisher, year, author, pages, isbn);
    }

    public Magazine readAndCreateMagazine() {
        consolePrinter.printLine("Tytuł: ");
        String title = sc.nextLine();
        consolePrinter.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        consolePrinter.printLine("Język: ");
        String language = sc.nextLine();
        consolePrinter.printLine("Rok wydania: ");
        int year = getInt();
        consolePrinter.printLine("Miesiąc: ");
        int month = getInt();
        consolePrinter.printLine("Dzień: ");
        int day = getInt();

        return new Magazine(title, publisher, language, year, month, day);
    }

    public int getInt() throws InputMismatchException {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Użytko błędnego znaku");
        } finally {
            sc.nextLine();
        }
    }

    public String getString() {
        return sc.nextLine();
    }

}
