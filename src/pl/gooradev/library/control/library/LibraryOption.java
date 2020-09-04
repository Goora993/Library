package pl.gooradev.library.control.library;

public enum LibraryOption {
    PUBLICATION_MENU(1, "Menu zarządzania publikacjami"),
    USER_MENU(2, "Menu zarządzania użytkownikami"),
    SAVE_LOAD_MENU(3, "Menu zarządzania zapisem i odczytem danych"),
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
