package pl.gooradev.library.model;


public class Library {
    private int maxPublications = 1;
    private Publication[] publications = new Publication[maxPublications];
    private int publicationNumber;

    public Library() {
    }


    public void addPublication(Publication publication) {
        if (maxPublications <= publicationNumber) {
            publications = extendTab(publications);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }

    private Publication[] extendTab(Publication[] publications) {
        maxPublications *= maxPublications * 2;
        Publication[] newPublications = new Publication[maxPublications];
        for (int i = 0; i < publications.length; i++) {
            newPublications[i] = publications[i];
        }
        return newPublications;
    }

    public String printAll() {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < publicationNumber; i++) {
            sb.append(publications[i].toString() + "\n");
        }
        if (publicationNumber == 0) {
            return "Brak publikacji w bibliotece";
        }

        return sb.toString();
    }

    public String printBooks() {

        StringBuffer sb = new StringBuffer();
        int booksNumber = 0;

        for (Publication publication : publications) {
            if (publication instanceof Book) {
                sb.append(publication.toString() + "\n");
                booksNumber++;
            }
            if (booksNumber == 0) {
                return "Brak książek w bibliotece";
            }
        }

        return sb.toString();
    }

    public String printMagazines() {
        StringBuffer sb = new StringBuffer();
        int magazinesNumber = 0;

        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                sb.append(publication.toString() + "\n");
                magazinesNumber++;
            }
            if (magazinesNumber == 0) {
                return "Brak magazynów/gazet w bibliotece";
            }
        }

        return sb.toString();
    }

    public int getMaxPublications() {
        return maxPublications;
    }

    public void setMaxPublications(int maxPublications) {
        this.maxPublications = maxPublications;
    }

    public Publication[] getPublications() {
        return publications;
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
