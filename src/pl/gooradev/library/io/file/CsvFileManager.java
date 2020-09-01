package pl.gooradev.library.io.file;

import pl.gooradev.library.exception.*;
import pl.gooradev.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    public static final String PUBLICATION_FILE_NAME = "Publications.csv";
    public static final String USER_FILE_NAME = "Users.csv";

    @Override
    public Library importData() {
        Library library = new Library();

        importPublications(library);
        importUsers(library);

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
        try (Scanner fileReader = new Scanner(new File(PUBLICATION_FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Publication publication = createPublicationFromString(line);
                library.addPublication(publication);
            }
        } catch (FileNotFoundException e) {
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
        return new Book(title, publisher, year, author, pages, isbn, id);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        int id = Integer.valueOf(data[7]);
        return new Magazine(title, publisher, language, year, month, day, id);
    }

    private void importUsers(Library library) {
        try (Scanner fileReader = new Scanner(new File(USER_FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                User user = createUserFromString(line);
                library.addUser(user);
            }
        } catch (FileNotFoundException e) {
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
}
