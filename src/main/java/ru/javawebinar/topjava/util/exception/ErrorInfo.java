package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private final String url;
    private final String typeMessage;
    private final String details;

    public ErrorInfo(CharSequence url, String typeMessage, String details) {
        this.url = url.toString();
        this.typeMessage = typeMessage;
        this.details = details;
    }
}