package pl.gooradev.library.control.publication.info_publication.info_book;

public enum InfoBookOption {
    SORT_BY_TITLE(1, "Wyświetl posortowane według tytułu"),
    SORT_BY_AUTHOR(2, "Wyświetl posortowane według nazwiska autora"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;


    InfoBookOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    static InfoBookOption createFromInt(int opt) {
        InfoBookOption[] options = InfoBookOption.values();
        InfoBookOption optionToReturn = null;
        for (InfoBookOption option : options) {
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
