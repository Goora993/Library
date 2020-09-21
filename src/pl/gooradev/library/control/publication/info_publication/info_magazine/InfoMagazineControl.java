package pl.gooradev.library.control.publication.info_publication.info_magazine;

import pl.gooradev.library.control.publication.info_publication.InfoPublicationOption;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class InfoMagazineControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    int optionInt;
    InfoMagazineOption infoMagazineOption;


    public InfoMagazineControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }


    public void printSortedMagazinesLoop() {
        do{
            try{
                printSortMagazinesMenu();
                infoMagazineOption = getOption();
                printSortedMagazines(infoMagazineOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }
        } while (infoMagazineOption != InfoMagazineOption.BACK);
    }

    private void printSortedMagazines(InfoMagazineOption infoMagazineOption){
        switch (infoMagazineOption) {
            case SORT_BY_NAME:
            case SORT_BY_PUBLISHER:
                printMagazines(infoMagazineOption);
                break;
            case BACK:
                break;
        }
    }

    private void printMagazines(InfoMagazineOption infoMagazineOption) {
        consolePrinter.printMagazines(library, infoMagazineOption);
    }

    private void printSortMagazinesMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoMagazineOption.SORT_BY_NAME);
        consolePrinter.printLine(InfoMagazineOption.SORT_BY_PUBLISHER);
        consolePrinter.printLine(InfoMagazineOption.BACK);
    }

    private InfoMagazineOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return InfoMagazineOption.createFromInt(optionInt);
    }
}
