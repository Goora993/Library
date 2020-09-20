package pl.gooradev.library.control.publication.info_publication;


public enum InfoPublicationOption {
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


    InfoPublicationOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    static InfoPublicationOption createFromInt(int opt) {
        InfoPublicationOption[] options = InfoPublicationOption.values();
        InfoPublicationOption optionToReturn = null;
        for (InfoPublicationOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
}
