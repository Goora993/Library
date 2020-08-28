package pl.gooradev.library.control.library;

public enum LibraryOption {
    ADD_PUBLICATION(1, "Dodawanie publikacji"),
    REMOVE_PUBLICATION(2, "Usuwanie publikacji"),
    PRINT_PUBLICATIONS(3, "Wyświetlanie informacji o publikacjach"),
    ADD_USER(4, "Dodawanie użytkowników"),
    REMOVE_USER(5, "Usuwanie użytkowników"),
    PRINT_USER(6, "Wyświetlanie informacji o użytkownikach"),
    EXIT(0, "Wyjście z programu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    LibraryOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static LibraryOption createFromInt(int opt) {
        LibraryOption[] libraryOptions = LibraryOption.values();
        LibraryOption libraryOptionToReturn = null;
        for (LibraryOption libraryOption : libraryOptions) {
            if (libraryOption.getValue() == opt)
                libraryOptionToReturn = libraryOption;
        }
        return libraryOptionToReturn;
    }

}
