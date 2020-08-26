package pl.gooradev.library.control.add;

import java.util.InputMismatchException;

public enum AddOption {
    ADD_BOOK(1, "Dodawanie książki"),
    ADD_MAGAZINE(2, "Dodawanie magazynu/gazety"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    AddOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static AddOption createFromInt(int opt) {
        AddOption[] options = AddOption.values();
        AddOption optionToReturn = null;
        for (AddOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

    public static AddOption getOption(int inputInt) throws InputMismatchException {
        return AddOption.createFromInt(inputInt);
    }
}
