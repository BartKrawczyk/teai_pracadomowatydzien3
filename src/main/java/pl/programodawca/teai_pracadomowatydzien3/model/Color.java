package pl.programodawca.teai_pracadomowatydzien3.model;

public enum Color {
    ZIELONY("zielony"), CZERWONY("czerwony"), CZARNY("czarny"), NIEBIESKI("niebieski"), SREBRNY("srebrny");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
