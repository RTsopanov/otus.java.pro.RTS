package ru.otus.pro.streamapi;

public enum Condition {
    TO_DO("To Do"),
    DONE("Done"),
    IN_REVIEW("In Review");

    private final String description;

    Condition(String description) {
        this.description = description;
    }





    public String getDescription() {
        return description;}
}