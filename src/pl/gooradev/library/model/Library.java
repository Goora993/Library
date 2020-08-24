package pl.gooradev.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {

    private int maxPublications = 10;
    private Publication[] publications = new Publication[maxPublications];
    private int publicationNumber;

    public Library() {
    }

    public void addPublication(Publication publication) {
        if (maxPublications <= publicationNumber) {
            publications = Arrays.copyOf(publications, publications.length*2);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }

    public boolean removePublicationByPublication(Publication publication){
        final int NOT_FOUND = -1;
        int found = NOT_FOUND;
        int i = 0;
        while (i < publications.length && found == NOT_FOUND) {
            if (publication.equals(publications[i])) {
                found = i;
            } else {
                i++;
            }
        }

        if (found != NOT_FOUND) {
            System.arraycopy(publications, found + 1, publications, found,
                    publications.length - found - 1);
            publicationNumber--;
        }

        return found != NOT_FOUND;
    }


    public Publication[] getPublications() {
        Publication[] resultTab = new Publication[publicationNumber];
        for (int i = 0; i < publications.length; i++) {
            if(publications[i]!=null)
                resultTab[i] = publications[i];
        }
        return resultTab;
    }


    public int getMaxPublications() {
        return maxPublications;
    }

    public void setMaxPublications(int maxPublications) {
        this.maxPublications = maxPublications;
    }

    public void setPublications(Publication[] publications) {
        this.publications = publications;
    }

    public int getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(int publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
