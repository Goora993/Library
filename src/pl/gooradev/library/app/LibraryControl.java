package pl.gooradev.library.app;

import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Book;
import pl.gooradev.library.model.Library;

public class LibraryControl {
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;
    private static final int EXIT = 3;


    Library library = new Library();
    DataReader dataReader = new DataReader();

    public void mainLoop(){
        int option = 0;
        do{
            printOptions();
            option = dataReader.getInt();
            switch (option){
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie: ");
                    
            }
        } while(option!=0);
    }

    private void exit() {
        System.out.println("Do widzenia!");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        System.out.println(ADD_BOOK + " - dodanie nowej książki");
        System.out.println(PRINT_BOOKS + " - wyświetl dostępne książki");
        System.out.println(EXIT + " - wyjście z programu");
    }

}
