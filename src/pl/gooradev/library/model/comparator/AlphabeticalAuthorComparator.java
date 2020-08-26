package pl.gooradev.library.model.comparator;


import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Publication;


import java.util.Comparator;

public class AlphabeticalAuthorComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication p1, Publication p2) {
        Book b1 = null;
        Book b2 = null;

        if(p1 instanceof Book && p2 instanceof Book){
            b1 = (Book) p1;
            b2 = (Book) p2;
        }

        if (b1 == null && b2 == null)
            return 0;
        else if (b1 == null)
            return 1;
        else if (b2 == null)
            return -1;
        return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
    }

}
