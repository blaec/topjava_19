package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo extends BaseEntity {
    private LocalDateTime dateTime;

    private String description;

    private int calories;

//    private final Supplier<Boolean> excess;
//    private final AtomicBoolean excess;
    private boolean excess;

    public MealTo() {}

    public MealTo(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories, true);
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess){
            super(id);
            this.dateTime = dateTime;
            this.description = description;
            this.calories = calories;
            this.excess = excess;
        }

//    public Boolean getExcess() {
//        return excess.get();
//    }


        public LocalDateTime getDateTime () {
            return dateTime;
        }

        public String getDescription () {
            return description;
        }

        public int getCalories () {
            return calories;
        }

        public boolean isExcess () {
            return excess;
        }

        @Override
        public String toString () {
            return "MealTo{" +
                    "dateTime=" + dateTime +
                    ", description='" + description + '\'' +
                    ", calories=" + calories +
                    ", excess=" + excess +
                    '}';
        }
    }
