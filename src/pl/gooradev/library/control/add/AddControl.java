package pl.gooradev.library.control.add;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.util.IdGenerator;

public class AddControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;
    IdGenerator idGenerator = new IdGenerator();

    public AddControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void addPublication() {
        AddOption option;
        do {
            printAddMagazineOrBookMenu();
            option = AddOption.getOption(dataReader.getInt());
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
        } while (option != AddOption.BACK);
    }

    private void addMagazine() {
        Publication publication = dataReader.readAndCreateMagazine();
        publication.setId(idGenerator.createId(library.getPublications()));
        library.addPublication(publication);
    }

    private void addBook() {
        Publication publication = dataReader.readAndCreateBook();
        publication.setId(idGenerator.createId(library.getPublications()));
        library.addPublication(publication);
    }

    private void printAddMagazineOrBookMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(AddOption.ADD_BOOK);
        consolePrinter.printLine(AddOption.ADD_MAGAZINE);
        consolePrinter.printLine(AddOption.BACK);
    }
}
