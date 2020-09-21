package pl.gooradev.library.io.print;

import pl.gooradev.library.control.publication.info_publication.InfoPublicationOption;
import pl.gooradev.library.control.publication.info_publication.info_book.InfoBookOption;
import pl.gooradev.library.control.publication.info_publication.info_magazine.InfoMagazineOption;
import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Magazine;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.comparator.AlphabeticalAuthorComparator;
import pl.gooradev.library.model.comparator.AlphabeticalPublisherComparator;
import pl.gooradev.library.model.comparator.AlphabeticalTitleComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class PublicationPrinter {

    public static String printAllPublications(Library library) {
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
            return "Brak publikacji w bibliotece";
        }

        return sb.toString();
    }

    public static String printBooks(Library library, InfoBookOption infoBookOption) {
        StringBuffer sb = new StringBuffer();
        Collection<Publication> publicationCollection = library.getPublicationsCollection();
        List<Publication> publicationList = new ArrayList<>(publicationCollection);

        int counter = 0;

        if (infoBookOption.equals(InfoPublicationOption.SORT_BY_TITLE))
            Collections.sort(publicationList, new AlphabeticalTitleComparator());
        else if (infoBookOption.equals(InfoPublicationOption.SORT_BY_AUTHOR))
            Collections.sort(publicationList, new AlphabeticalAuthorComparator());

        for (Publication publication : publicationList) {
            if (publication instanceof Book) {
                sb.append(publication.toString() + "\n");
                counter++;
            }
        }

        if (counter == 0) {
            return "Brak książek w bibliotece";
        }

       return sb.toString();
    }

    public static String printMagazines(Library library, InfoMagazineOption infoMagazineOption) {
        StringBuffer sb = new StringBuffer();
        Collection<Publication> publicationCollection = library.getPublicationsCollection();
        List<Publication> publicationList = new ArrayList<>(publicationCollection);
        int counter = 0;

        if (infoMagazineOption.equals(InfoPublicationOption.SORT_BY_NAME))
            Collections.sort(publicationList, new AlphabeticalTitleComparator());
        else if (infoMagazineOption.equals(InfoPublicationOption.SORT_BY_PUBLISHER))
            Collections.sort(publicationList, new AlphabeticalPublisherComparator());

        for (Publication publication : publicationList) {
            if (publication instanceof Magazine) {
                sb.append(publication.toString() + "\n");
                counter++;
            }
        }

        if (counter == 0) {
            return "Brak magazynów/książek w bibliotece";
        }

        return sb.toString();
    }


}
