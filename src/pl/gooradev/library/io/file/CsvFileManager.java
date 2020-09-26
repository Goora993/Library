package pl.gooradev.library.io.file;

import pl.gooradev.library.exception.*;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;


public class CsvFileManager implements FileManager {
    public static String PUBLICATION_FILE_NAME = "Publications.csv";
    public static String USER_FILE_NAME = "Users.csv";


    ConsolePrinter consolePrinter;


    protected CsvFileManager(ConsolePrinter consolePrinter){
        this.consolePrinter = consolePrinter;
    }


    @Override
    public Library importData() {
        Library library = new Library();

        importPublications(library);
        importUsers(library);
        setRandomPathAndNames();

        consolePrinter.printLine("Import danych z pliku zakończony powodzeniem");

        return library;
    }


    @Override
    public Library importData(ImportType importType) {
        Library library = new Library();

        switch (importType){
            case IMPORT_PUBLICATIONS:
                importPublications(library);
                library.setUsers(new HashMap<>());
                break;
            case IMPORT_USERS:
                importUsers(library);
                library.setPublications(new HashMap<>());
                break;
        }

        return library;
    }


    private void importPublications(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PUBLICATION_FILE_NAME))) {
            bufferedReader.lines()
                    .map(string->createPublicationFromString(string))
                    .forEach(publication->library.addPublication(publication));
        } catch (IOException e) {
            throw new PublicationImportException("Brak pliku " + PUBLICATION_FILE_NAME);
        }
    }


    private Publication createPublicationFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji: " + type);
    }


    private Book createBook(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        String author = data[4];
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        int id = Integer.valueOf(data[7]);
        boolean borrowed = Boolean.valueOf(data[8]);
        return new Book(title, publisher, year, author, pages, isbn, id, borrowed);
    }


    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        int id = Integer.valueOf(data[7]);
        boolean borrowed = Boolean.valueOf(data[8]);
        return new Magazine(title, publisher, language, year, month, day, id, borrowed);
    }


    private void importUsers(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_FILE_NAME))) {
            bufferedReader.lines()
                    .map(string->createUserFromString(string))
                    .forEach(user->library.addUser(user));
        } catch (IOException e) {
            throw new UserImportException("Brak pliku " + USER_FILE_NAME);
        }
    }


    public User createUserFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (LibraryUser.TYPE.equals(type)) {
            return createLibraryUser(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji: " + type);
    }


    private User createLibraryUser(String[] split) {
        String firstName = split[1];
        String lastName = split[2];
        String pesel = split[3];
        return new LibraryUser(firstName, lastName, pesel);
    }


    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
        setRandomPathAndNames();
        consolePrinter.printLine("Export danych do pliku zakończony powodzeniem");
    }


    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublicationsCollection();
        try (FileWriter fileWriter = new FileWriter(PUBLICATION_FILE_NAME);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Publication publication : publications) {
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + PUBLICATION_FILE_NAME);
        }
    }


    private void exportUsers(Library library) {
        Collection<User> users = library.getUsersCollection();
        try (FileWriter fileWriter = new FileWriter(USER_FILE_NAME);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (User user : users) {
                bufferedWriter.write(user.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + USER_FILE_NAME);
        }
    }


    public static String getPublicationFileName() {
        return PUBLICATION_FILE_NAME;
    }


    public static void setPublicationFileName(String publicationFileName) {
        PUBLICATION_FILE_NAME = publicationFileName;
    }


    public static String getUserFileName() {
        return USER_FILE_NAME;
    }


    public static void setUserFileName(String userFileName) {
        USER_FILE_NAME = userFileName;
    }


    private static void setRandomPathAndNames(){
        setPublicationFileName("Publications.csv");
        setUserFileName("Users.csv");
    }

}
