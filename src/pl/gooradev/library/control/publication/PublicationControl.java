package pl.gooradev.library.control.publication;

import pl.gooradev.library.control.publication.add_publication.AddPublicationControl;
import pl.gooradev.library.control.publication.info_publication.InfoPublicationControl;
import pl.gooradev.library.control.publication.remove_publication.RemovePublicationControl;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class PublicationControl {

    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;
    AddPublicationControl addPublicationControl;
    RemovePublicationControl removePublicationControl;
    InfoPublicationControl infoPublicationControl;

    int optionInt;
    PublicationOption publicationOption;

    public PublicationControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader) {
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        addPublicationControls();
    }

    private void addPublicationControls() {
        addPublicationControl = new AddPublicationControl(library, dataReader, consolePrinter);
        removePublicationControl = new RemovePublicationControl(library, dataReader, consolePrinter);
        infoPublicationControl = new InfoPublicationControl(library, dataReader, consolePrinter);
    }

    public void managePublicationLoop() {
        do {

            try {
                printOptions();
                publicationOption = getOption();
                managePublication(publicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (publicationOption != PublicationOption.BACK);
    }

    private void managePublication(PublicationOption publicationOption) {
        switch (publicationOption) {
            case ADD_PUBLICATION:
                addPublicationControl.addPublicationLoop();
                break;
            case REMOVE_PUBLICATION:
                removePublicationControl.removePublicationLoop();
                break;
            case PRINT_PUBLICATIONS:
                infoPublicationControl.printPublicationsLoop();
                break;
            case BACK:
                break;
        }

    }

    private void printOptions() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(PublicationOption.ADD_PUBLICATION);
        consolePrinter.printLine(PublicationOption.REMOVE_PUBLICATION);
        consolePrinter.printLine(PublicationOption.PRINT_PUBLICATIONS);
        consolePrinter.printLine(PublicationOption.BACK);
    }

    private PublicationOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return PublicationOption.createFromInt(optionInt);
    }

}
