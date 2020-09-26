package pl.gooradev.library.io.file;

import pl.gooradev.library.exception.NoSuchFileTypeException;
import pl.gooradev.library.io.print.ConsolePrinter;

public class FileManagerBuilder {

    private static CsvFileManager csvFileManager;
    private static SerializableFileManager serializableFileManager;

    public static FileManager getFileManager(FileType fileType, ConsolePrinter consolePrinter){
        switch (fileType){
            case CSV:
                return buildCsvFileManager(consolePrinter);
            case SERIAL:
                return buildSerializableFileManager(consolePrinter);
            default :
                throw  new NoSuchFileTypeException("Błędny typ danych");
        }
    }

    private static FileManager buildSerializableFileManager(ConsolePrinter consolePrinter) {
        if(serializableFileManager == null){
            serializableFileManager = new SerializableFileManager(consolePrinter);
        }

        return serializableFileManager;
    }

    private static CsvFileManager buildCsvFileManager(ConsolePrinter consolePrinter) {
        if(csvFileManager == null){
            csvFileManager = new CsvFileManager(consolePrinter);
        }

        return csvFileManager;
    }

}
