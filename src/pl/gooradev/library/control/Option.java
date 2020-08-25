package pl.gooradev.library.control;

import java.util.InputMismatchException;

public enum Option {
    ADD_PUBLICATION(1, "Dodawanie publikacji"),
    ADD_BOOK(11, "Dodawanie książki"),
    ADD_MAGAZINE(12, "Dodawanie magazynu/gazety"),
    REMOVE_PUBLICATION(2, "Usuwanie publikacji"),
    REMOVE_BOOK(21, "Usuwanie ksiażki"),
    REMOVE_MAGAZINE(22, "Usuwanie magazynu/gazety"),
    PRINT_PUBLICATIONS(3, "Wyświetlanie informacji o publikacjach"),
    PRINT_BOOKS(31, "Wyświetlanie informacji o książkach"),
    PRINT_MAGAZINES(32, "Wyświetlanie informacji o magazynach/gazetach"),
    PRINT_ALL(33, "Wyświetlanie informacji o wszystkich publikacjach"),
    BACK(9, "Wróć do poprzedniego menu"),
    EXIT(0, "Wyjście z programu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
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

    static Option getOption(int inputInt) throws InputMismatchException {
        return Option.createFromInt(inputInt);
    }
}
