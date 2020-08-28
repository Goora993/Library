package pl.gooradev.library.control.publication.remove_publication;

import java.util.InputMismatchException;

public enum RemovePublicationOption {
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

    RemovePublicationOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static RemovePublicationOption createFromInt(int opt) {
        RemovePublicationOption[] options = RemovePublicationOption.values();
        RemovePublicationOption optionToReturn = null;
        for (RemovePublicationOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

    public static RemovePublicationOption getOption(int inputInt) throws InputMismatchException {
        return RemovePublicationOption.createFromInt(inputInt);
    }
}
