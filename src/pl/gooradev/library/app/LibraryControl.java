package pl.gooradev.library.app;

import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;

public class LibraryControl {
    private static final int ADD_PUBLICATION = 1;
    private static final int PRINT_PUBLICATIONS = 2;
    private static final int EXIT = 3;


    Library library = new Library();
    DataReader dataReader = new DataReader();

    public void mainLoop(){
        int option = 0;
        do{
            printOptions();
            option = dataReader.getInt();
            switch (option){
                case ADD_PUBLICATION:
                    addPublication();
                    break;
                case PRINT_PUBLICATIONS:
                    printPublications();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
                    
            }
        } while(option!=0);
    }

    private void addPublication(){
        int option = 0;
        do{
            printMagazineOrBookMenu();
            option = dataReader.getInt();
            switch(option){
                case 1:
                    addBook();
                    break;
                case 2:
                    addMagazine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while(option!=0);
    }

    private void addMagazine() {
        Publication publication = dataReader.readAndCreateMagazine();
        library.addPublication(publication);
    }

    private void addBook() {
        Publication publication = dataReader.readAndCreateBook();
        library.addPublication(publication);
    }

    private void printPublications() {
        int option = 0;
        do{
            printAddPublicationsMenu();
            option = dataReader.getInt();
            switch(option){
                case 1:
                    printBooks();
                    break;
                case 2:
                    printMagazines();
                    break;
                case 3:
                    printAll();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while(option!=0);
    }



    private void printBooks() {
        library.printBooks();
    }

    private void printMagazines() {
        library.printMagazines();
    }

    private void printAll() {
        library.printAll();
    }

    private void exit() {
        System.out.println("Do widzenia!");
        dataReader.close();
    }


    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(ADD_PUBLICATION + " - dodanie nowej publikacji");
        System.out.println(PRINT_PUBLICATIONS + " - wyświetl dostępne publikacje");
        System.out.println(EXIT + " - wyjście z programu");
    }

    private void printMagazineOrBookMenu(){
        System.out.println("Wybierz opcję: ");
        System.out.println("1 - dodanie nowej książki");
        System.out.println("2 - dodanie nowego magazynu");
        System.out.println("0 - wróć do menu głównego");
    }

    private void printAddPublicationsMenu(){
        System.out.println("Wybierz opcję: ");
        System.out.println("1 - wyświetl książki");
        System.out.println("2 - wyświetl magazyny");
        System.out.println("3 - wyświetl wszystko");
        System.out.println("0 - wróć do menu głównego");
    }

}
