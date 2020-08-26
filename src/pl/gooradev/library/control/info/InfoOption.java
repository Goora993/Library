package pl.gooradev.library.control.info;

import java.util.InputMismatchException;

public enum InfoOption {
    PRINT_BOOKS(1, "Wyświetlanie informacji o książkach"),
    SORT_BY_TITLE(11, "Wyświetl posortowane według tytułu"),
    SORT_BY_AUTHOR(12, "Wyświetl posortowane według nazwiska autora"),
    PRINT_MAGAZINES(2, "Wyświetlanie informacji o magazynach/gazetach"),
    SORT_BY_NAME(21, "Wyświetl posortowane według nazwy"),
    SORT_BY_PUBLISHER(22, "Wyświetl posortowane według wydawnictwa"),
    PRINT_ALL(3, "Wyświetlanie informacji o wszystkich publikacjach"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    InfoOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static InfoOption createFromInt(int opt) {
        InfoOption[] options = InfoOption.values();
        InfoOption optionToReturn = null;
        for (InfoOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

    public static InfoOption getOption(int inputInt) throws InputMismatchException {
        return InfoOption.createFromInt(inputInt);
    }
}
