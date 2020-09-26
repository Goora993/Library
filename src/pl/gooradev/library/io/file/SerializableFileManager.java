package pl.gooradev.library.io.file;

import pl.gooradev.library.exception.DataExportException;
import pl.gooradev.library.exception.DataImportException;
import pl.gooradev.library.exception.PublicationImportException;
import pl.gooradev.library.exception.UserImportException;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerializableFileManager implements FileManager {

    public static final String PUBLICATION_FILE_NAME = "Publications.o";
    public static final String USER_FILE_NAME = "Users.o";

    ConsolePrinter consolePrinter;


    protected SerializableFileManager(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }


    @Override
    public Library importData() {
        Library library = new Library();

        library.setPublications(importPublicationsMap());
        library.setUsers(importUsersMap());

        consolePrinter.printLine("Import danych z pliku zakończony powodzeniem");

        return library;
    }


    @Override
    public Library importData(ImportType importType) {
        Library library = new Library();

        switch (importType){
            case IMPORT_PUBLICATIONS:
                library.setPublications(importPublicationsMap());
                library.setUsers(new HashMap<>());
                break;
            case IMPORT_USERS:
                library.setUsers(importUsersMap());
                library.setPublications(new HashMap<>());
                break;
        }

        return library;
    }


    private Map<Integer, Publication> importPublicationsMap() {
        try (FileInputStream fis = new FileInputStream(PUBLICATION_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Map<Integer, Publication>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new PublicationImportException("Brak pliku " + PUBLICATION_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + PUBLICATION_FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + PUBLICATION_FILE_NAME);
        }
    }


    private Map<String, User> importUsersMap() {
        try (FileInputStream fis = new FileInputStream(USER_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new UserImportException("Brak pliku " + USER_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + USER_FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + USER_FILE_NAME);
        }
    }


    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
        consolePrinter.printLine("Export danych do pliku zakończony powodzeniem");
    }


    private void exportPublications(Library library) {
        try (FileOutputStream fos = new FileOutputStream(PUBLICATION_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(library.getPublications());
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + PUBLICATION_FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + PUBLICATION_FILE_NAME);
        }
    }


    private void exportUsers(Library library) {
        try (FileOutputStream fos = new FileOutputStream(USER_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(library.getUsers());
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + USER_FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + USER_FILE_NAME);
        }
    }

}
