package pl.gooradev.library.control.publication;

import pl.gooradev.library.control.publication.add_publication.AddPublicationControl;
import pl.gooradev.library.control.publication.info_publication.InfoPublicationControl;
import pl.gooradev.library.control.publication.remove_publication.RemovePublicationControl;
import pl.gooradev.library.exception.NoSuchIdException;
import pl.gooradev.library.exception.NoSuchOptionException;
import pl.gooradev.library.io.ConsolePrinter;
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

    public PublicationControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        addPublicationControls();
    }

    private void addPublicationControls() {
        addPublicationControl = new AddPublicationControl(library,dataReader,consolePrinter);
        removePublicationControl = new RemovePublicationControl(library, dataReader, consolePrinter);
        infoPublicationControl = new InfoPublicationControl(library, dataReader, consolePrinter);
    }

    public void managePublicationLoop()
            throws NoSuchOptionException, NoSuchIdException {
        do{
            printOptions();
            publicationOption = getOption();
            managePublication(publicationOption);
        }while(publicationOption!=PublicationOption.BACK);
    }

    private void managePublication(PublicationOption publicationOption) throws NoSuchOptionException, InputMismatchException,
            NoSuchIdException {
        try{
            switch (publicationOption) {
                case ADD_PUBLICATION:
                    addPublicationControl.addPublication();
                    break;
                case REMOVE_PUBLICATION:
                    removePublicationControl.removePublication();
                    break;
                case PRINT_PUBLICATIONS:
                    infoPublicationControl.printPublications();
                    break;
                case BACK:
                    break;
            }
        } catch (NullPointerException e){
            throw new NoSuchOptionException("Brak opcji o id " + optionInt);
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
