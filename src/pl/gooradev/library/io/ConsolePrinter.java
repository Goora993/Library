package pl.gooradev.library.io;

import pl.gooradev.library.control.publication.info_publication.InfoPublicationOption;
import pl.gooradev.library.model.*;
import pl.gooradev.library.model.comparator.AlphabeticalAuthorComparator;
import pl.gooradev.library.model.comparator.AlphabeticalPublisherComparator;
import pl.gooradev.library.model.comparator.AlphabeticalTitleComparator;

import java.util.*;

public class ConsolePrinter {


    public void printAllPublications(Library library) {
        StringBuffer sb = new StringBuffer();
        Collection<Publication> publicationCollection = library.getPublicationsCollection();
        List<Publication> publicationList = new ArrayList<>(publicationCollection);

        Collections.sort(publicationList, new AlphabeticalTitleComparator());

        for (int i = 0; i < publicationList.size(); i++) {
            if (publicationList.get(i) instanceof Book)
                sb.append("Książka: " + publicationList.get(i).toString() + "\n");
            else
                sb.append("Magazyn/gazeta: " + publicationList.get(i).toString() + "\n");
        }

        if (publicationList.size() == 0) {
            printLine("Brak publikacji w bibliotece");
        }

        printLine(sb.toString());
    }

    public void printBooks(Library library, InfoPublicationOption option) {
        StringBuffer sb = new StringBuffer();
        Collection<Publication> publicationCollection = library.getPublicationsCollection();
        List<Publication> publicationList = new ArrayList<>(publicationCollection);

        int counter = 0;

        if (option.equals(InfoPublicationOption.SORT_BY_TITLE))
            Collections.sort(publicationList, new AlphabeticalTitleComparator());
        else if (option.equals(InfoPublicationOption.SORT_BY_AUTHOR))
            Collections.sort(publicationList, new AlphabeticalAuthorComparator());

        for (Publication publication : publicationList) {
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

    public void printMagazines(Library library, InfoPublicationOption option) {
        StringBuffer sb = new StringBuffer();
        Collection<Publication> publicationCollection = library.getPublicationsCollection();
        List<Publication> publicationList = new ArrayList<>(publicationCollection);
        int counter = 0;

        if (option.equals(InfoPublicationOption.SORT_BY_NAME))
            Collections.sort(publicationList, new AlphabeticalTitleComparator());
        else if (option.equals(InfoPublicationOption.SORT_BY_PUBLISHER))
            Collections.sort(publicationList, new AlphabeticalPublisherComparator());

        for (Publication publication : publicationList) {
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

    public void printAllUsers(Library library) {
        StringBuffer sb = new StringBuffer();
        Collection<User> userCollection = library.getUsers().values();
        List<User> userList = new ArrayList<>(userCollection);


        for (int i = 0; i < userList.size(); i++) {

            sb.append(userList.get(i).toString() + "\n");
        }

        if (userList.size() == 0) {
            printLine("Brak zarejestrowanych użytkowników");
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

