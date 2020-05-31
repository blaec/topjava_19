package ru.javawebinar.topjava.util.exception;

public enum ErrorType {
    APP_ERROR("App error"),
    DATA_NOT_FOUND("Data not found"),
    DATA_ERROR("Data error"),
    VALIDATION_ERROR("Validation error");

    private String errorType;

    ErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
