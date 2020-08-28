package pl.gooradev.library.control.publication.add_publication;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.util.IdGenerator;

public class AddPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;
    IdGenerator idGenerator = new IdGenerator();

    public AddPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void addPublication() {
        AddPublicationOption option;
        do {
            printAddMagazineOrBookMenu();
            option = AddPublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case BACK:
                    break;
            }
        } while (option != AddPublicationOption.BACK);
    }

    private void addMagazine() {
        Publication publication = dataReader.readAndCreateMagazine();
        publication.setId(idGenerator.createId(library.getPublicationsCollection()));
        library.addPublication(publication);
    }

    private void addBook() {
        Publication publication = dataReader.readAndCreateBook();
        publication.setId(idGenerator.createId(library.getPublicationsCollection()));
        library.addPublication(publication);
    }

    private void printAddMagazineOrBookMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(AddPublicationOption.ADD_BOOK);
        consolePrinter.printLine(AddPublicationOption.ADD_MAGAZINE);
        consolePrinter.printLine(AddPublicationOption.BACK);
    }
}
