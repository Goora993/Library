package pl.gooradev.library.control.sl;

import pl.gooradev.library.control.sl.load.LoadControl;
import pl.gooradev.library.control.sl.save.SaveControl;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class SaveLoadControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    int optionInt;
    SaveLoadOption saveLoadOption;

    SaveControl saveControl;
    LoadControl loadControl;

    public SaveLoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        saveControl = new SaveControl(library, consolePrinter, dataReader);
        loadControl = new LoadControl(library, consolePrinter, dataReader);
    }

    public void manageSaveLoadLoop() {
        do {

            try{
                printOptions();
                saveLoadOption = getOption();
                manageSaveLoad(saveLoadOption);
            } catch (NullPointerException e){
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (saveLoadOption!=SaveLoadOption.BACK);
    }


    private void manageSaveLoad(SaveLoadOption saveLoadOption)  {

        switch (saveLoadOption) {
            case SAVE_MENU:
                saveControl.manageSaveLoop();
                break;
            case LOAD_MENU:
                loadControl.manageLoadLoop();
                break;
            case BACK:
                break;
        }
    }

    private void printOptions(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(SaveLoadOption.SAVE_MENU);
        consolePrinter.printLine(SaveLoadOption.LOAD_MENU);
        consolePrinter.printLine(SaveLoadOption.BACK);
    }

    private SaveLoadOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return SaveLoadOption.createFromInt(optionInt);
    }
}
