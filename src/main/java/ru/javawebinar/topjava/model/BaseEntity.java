package ru.javawebinar.topjava.model;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseEntity {
    private int id;

    public BaseEntity() {}

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
