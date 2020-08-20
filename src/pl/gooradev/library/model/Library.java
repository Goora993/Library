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
