package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.time.LocalDateTime;

public class MealTo extends AbstractBaseEntity {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

//    private final Supplier<Boolean> excess;
//    private final AtomicBoolean excess;
    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories, false);
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

//    public Boolean getExcess() {
//        return excess.get();
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
