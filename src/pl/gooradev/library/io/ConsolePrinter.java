package pl.gooradev.library.io;

import pl.gooradev.library.control.Option;
import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Magazine;
import pl.gooradev.library.model.Publication;

public class ConsolePrinter {


    public void printAll(Library library) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();

        for (int i = 0; i < publications.length; i++) {
            if(publications[i] instanceof Book)
                sb.append("Książka: " + publications[i].toString() + "\n");
            else
                sb.append("Magazyn/gazeta: " + publications[i].toString() + "\n");
        }

        if (publications.length == 0) {
            printLine("Brak publikacji w bibliotece");
        }

        printLine(sb.toString());
    }

    public void printBooks(Library library) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();
        int counter = 0;

        for (Publication publication : publications) {
            if (publication instanceof Book) {
                sb.append(publication.toString() + "\n");
                counter++;
            }
        }

        if (counter == 0) {
            printLine("Brak książek w bibliotece");
        }

        printLine(sb.toString());
    }

    public void printMagazines(Library library) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();
        int counter = 0;

        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                sb.append(publication.toString() + "\n");
                counter++;
            }
        }

        if (counter == 0) {
            printLine("Brak magazynów/książek w bibliotece");
        }

        printLine(sb.toString());
    }

    public void printLine(String text) {
        System.out.println(text);
    }

    public void printLine(Option option) {
        System.out.println(option.toString());
    }
}

