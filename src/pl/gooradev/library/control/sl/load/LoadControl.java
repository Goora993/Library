package pl.gooradev.library.control.sl.load;

import pl.gooradev.library.control.sl.save.SaveOption;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.CsvFileManager;
import pl.gooradev.library.io.file.SerializableFileManager;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class LoadControl {
        Library library;
        ConsolePrinter consolePrinter;
        DataReader dataReader;

        SerializableFileManager sfm = new SerializableFileManager();
        CsvFileManager cfm = new CsvFileManager();

        int optionInt;
        LoadOption loadOption;


        public LoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
            this.library = library;
            this.consolePrinter = consolePrinter;
            this.dataReader = dataReader;
        }

        public void manageLoadLoop() {
            do {
                printOptions();
                loadOption = getOption();

                switch (loadOption){
                    case SERIAL_LOAD:
                        sfm.exportData(library);
                        break;
                    case CSV_LOAD:
                        dataReader.getAndSetFilePath();
                        cfm.exportData(library);
                        break;
                    case BACK:
                        break;
                }
            } while (loadOption!=LoadOption.BACK);
        }

        private void printOptions(){
            consolePrinter.printLine("Wybierz opcję: ");
            consolePrinter.printLine(LoadOption.SERIAL_LOAD);
            consolePrinter.printLine(LoadOption.CSV_LOAD);
            consolePrinter.printLine(LoadOption.BACK);
        }

        private LoadOption getOption() throws InputMismatchException {
            optionInt = dataReader.getInt();
            return LoadOption.createFromInt(optionInt);
        }
}
