package pl.gooradev.library.control.publication.add_publication;

import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.util.IdGenerator;

import java.util.InputMismatchException;

public class AddPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;
    IdGenerator idGenerator = new IdGenerator();

    int optionInt;
    AddPublicationOption addPublicationOption;


    public AddPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void addPublicationLoop() {
        do {

            try{
                printAddMagazineOrBookMenu();
                addPublicationOption = getOption();
                addPublication(addPublicationOption);
            } catch (NullPointerException e){
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (addPublicationOption != AddPublicationOption.BACK);
    }

    private void addPublication(AddPublicationOption addPublicationOption) {
        switch (addPublicationOption) {
            case ADD_BOOK:
                addBook();
                break;
            case ADD_MAGAZINE:
                addMagazine();
                break;
            case BACK:
                break;
        }
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

    private AddPublicationOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return AddPublicationOption.createFromInt(optionInt);
    }
}
