package pl.gooradev.library.model;


public class Library {
    private int maxPublications = 1;
    private Publication[] publications = new Publication[maxPublications];
    private int publicationNumber;

    public Library() {
    }


    public void addPublication(Publication publication){
        if(maxPublications<=publicationNumber){
            publications = extendTab(publications);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }
    
    private Publication[] extendTab(Publication[] publications){
        maxPublications*=maxPublications*2;
        Publication[] newPublications = new Publication[maxPublications];
        for (int i = 0; i < publications.length; i++) {
            newPublications[i] = publications[i];
        }
        return newPublications;
    }

    public void printAll() {
        if(publicationNumber == 0) {
            System.out.println("Brak publikacji w bibliotece");
        }
        for(int i=0; i<publicationNumber; i++) {
            System.out.println(publications[i].toString());
        }
    }

    public void printBooks(){
        int booksNumber = 0;
        for (Publication publication : publications) {
            if(publication instanceof Book){
                System.out.println(publication);
                booksNumber++;
            }
            if (booksNumber == 0){
                System.out.println("Brak książek w bibliotece");
            }
        }
    }

    public void printMagazines(){
        int magazinesNumber = 0;
        for (Publication publication : publications) {
            if(publication instanceof Magazine){
                System.out.println(publication);
                magazinesNumber++;
            }
            if (magazinesNumber == 0){
                System.out.println("Brak magazynów w bibliotece");
            }
        }
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
