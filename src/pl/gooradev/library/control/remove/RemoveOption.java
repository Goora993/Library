package pl.gooradev.library.control.remove;

import pl.gooradev.library.control.add.AddOption;

import java.util.InputMismatchException;

public enum RemoveOption {
    REMOVE_BY_ID(1, "Usuwanie za pomocą ID publikacji"),
    REMOVE_BY_PUBLICATION(2, "Usuwanie za pomocą informacji na temat publiacji"),
    REMOVE_BOOK(21, "Usuwanie ksiażki"),
    REMOVE_MAGAZINE(22, "Usuwanie magazynu/gazety"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    RemoveOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static RemoveOption createFromInt(int opt) {
        RemoveOption[] options = RemoveOption.values();
        RemoveOption optionToReturn = null;
        for (RemoveOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

    public static RemoveOption getOption(int inputInt) throws InputMismatchException {
        return RemoveOption.createFromInt(inputInt);
    }
}
