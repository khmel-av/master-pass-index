package ru.khmel.mpi.card.jpa.entity;

/**
 * Статус пропуска
 *
 * @author Khmel Anton
 */
public enum StatusCard {
    ACTIVE("Действует"),
    EXPIRY("Истек срок"),
    DELETE("Удален");

    /**
     * Наименование статуса
     */
    private String name;

    StatusCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
