package pl.gooradev.library.io;

import pl.gooradev.library.control.info.InfoOption;
import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Magazine;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.comparator.AlphabeticalAuthorComparator;
import pl.gooradev.library.model.comparator.AlphabeticalPublisherComparator;
import pl.gooradev.library.model.comparator.AlphabeticalTitleComparator;

import java.util.Arrays;

public class ConsolePrinter {


    public void printAll(Library library) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();
        Arrays.sort(publications, new AlphabeticalTitleComparator());

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

    public void printBooks(Library library, InfoOption option) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();
        int counter = 0;

        if (option.equals(InfoOption.SORT_BY_TITLE))
            Arrays.sort(publications, new AlphabeticalTitleComparator());
        else if(option.equals(InfoOption.SORT_BY_AUTHOR))
            Arrays.sort(publications, new AlphabeticalAuthorComparator());

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

    public void printMagazines(Library library, InfoOption option) {
        StringBuffer sb = new StringBuffer();
        Publication[] publications = library.getPublications();
        int counter = 0;

        if (option.equals(InfoOption.SORT_BY_NAME))
            Arrays.sort(publications, new AlphabeticalTitleComparator());
        else if(option.equals(InfoOption.SORT_BY_PUBLISHER))
            Arrays.sort(publications, new AlphabeticalPublisherComparator());

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

    public <T> void printLine(T t) {
        System.out.println(t.toString());
    }
}

