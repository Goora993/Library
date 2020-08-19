package pl.gooradev.library.app;

import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;

public class LibraryControl {

    Library library = new Library();
    DataReader dataReader = new DataReader();

    public void mainLoop(){
        Option option;
        do{
            printOptions();
            option = Option.createFromInt(dataReader.getInt());
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
                    
            }
        } while(option!= Option.EXIT);
    }

    private void addPublication(){
        Option option;
        do{
            printMagazineOrBookMenu();
            option = Option.createFromInt(dataReader.getInt());
            switch(option){
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case BACK:
                    break;
            }
        } while(option!= Option.BACK);
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
        Option option;
        do{
            printAddPublicationsMenu();
            option = Option.createFromInt(dataReader.getInt());
            switch(option){
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case BACK:
                    break;
            }
        } while(option!= Option.BACK);
    }



    private void printBooks() {
        System.out.println(library.printBooks());
    }

    private void printMagazines() {
        System.out.println(library.printMagazines());
    }

    private void printAll() {
        System.out.println(library.printAll());
    }

    private void exit() {
        System.out.println("Do widzenia!");
        dataReader.close();
    }


    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(Option.ADD_PUBLICATION);
        System.out.println(Option.PRINT_PUBLICATIONS);
        System.out.println(Option.EXIT);;
    }

    private void printMagazineOrBookMenu(){
        System.out.println("Wybierz opcję: ");
        System.out.println(Option.ADD_BOOK);
        System.out.println(Option.ADD_MAGAZINE);
        System.out.println(Option.BACK);
    }

    private void printAddPublicationsMenu(){
        System.out.println("Wybierz opcję: ");
        System.out.println(Option.PRINT_BOOKS);
        System.out.println(Option.PRINT_MAGAZINES);
        System.out.println(Option.PRINT_ALL);
        System.out.println(Option.BACK);
    }

}
