package pl.gooradev.library.app;

import pl.gooradev.library.exception.NoSuchOptionException;

public enum Option {
    ADD_PUBLICATION(1, "Dodawanie publikacji"),
    ADD_BOOK(11, "Dodawanie książki"),
    ADD_MAGAZINE(12, "Dodawanie magazynu/gazety"),
    PRINT_PUBLICATIONS(2, "Wyświetlanie informacji o publikacjach"),
    PRINT_BOOKS(21, "Wyświetlanie informacji o książkach"),
    PRINT_MAGAZINES(22, "Wyświetlanie informacji o magazynach/gazetach"),
    PRINT_ALL(23, "Wyświetlanie informacji o wszystkich publikacjach"),
    BACK(3, "Wróć do poprzedniego menu"),
    EXIT(0, "Wyjście z programu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    Option(int value){
        this.value = value;
    }

    Option(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static Option createFromInt(int opt) {

        Option[] options = Option.values();
        Option optionToReturn = null;
        for (Option option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }
}
