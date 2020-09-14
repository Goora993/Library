package pl.gooradev.library.control.publication;

public enum PublicationOption {
    ADD_PUBLICATION(1, "Dodawanie publikacji"),
    REMOVE_PUBLICATION(2, "Usuwanie publikacji"),
    PRINT_PUBLICATIONS(3, "Wyświetlanie informacji o publikacjach"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    PublicationOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static PublicationOption createFromInt(int opt) {
        PublicationOption[] options = PublicationOption.values();
        PublicationOption optionToReturn = null;
        for (PublicationOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

}
