package ru.khmel.mpi.address.jpa.entity;

/**
 * Тип населенного пункта
 *
 * @author Khmel Anton
 */
public enum TypeLocality {
    CITY("Город"),
    URBAN_VILLAGE("Поселок городского типа"),
    VILLAGE("Поселок"),
    SMALL_VILLAGE("Село");

    /**
     * Наименование статуса
     */
    private String name;

    TypeLocality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
