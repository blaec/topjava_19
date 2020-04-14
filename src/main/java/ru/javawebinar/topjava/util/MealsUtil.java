package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.UsersUtil.ADMIN_ID;
import static ru.javawebinar.topjava.util.UsersUtil.USER_ID;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(ADMIN_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 500),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 1, 13, 0), "Обед", 1000),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 1, 20, 0), "Ужин", 500),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 2, 0, 0), "Еда на граничное значение", 100),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 2, 10, 0), "Завтрак", 1000),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 2, 13, 0), "Обед", 500),
            new Meal(USER_ID, LocalDateTime.of(2020, Month.FEBRUARY, 2, 20, 0), "Ужин", 410)
    );

    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay, int userId) {
        return filteredByStreams(meals, caloriesPerDay, userId, meal -> true);
    }

    public static List<MealTo> getFilteredTos(Collection<Meal> meals, int caloriesPerDay, LocalDateTime from, LocalDateTime to, int userId) {
        return filteredByStreams(meals, caloriesPerDay, userId, meal ->
                DateTimeUtil.isBetweenDateInclusive(meal.getDate(), from.toLocalDate(), to.toLocalDate()) &&
                DateTimeUtil.isBetweenTimeInclusive(meal.getTime(), from.toLocalTime(), to.toLocalTime()));
    }

    public static List<MealTo> filteredByStreams(Collection<Meal> meals, int caloriesPerDay, int userId, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(filter)
                .filter(m -> m.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static Meal convertTo(MealTo mealTo, int userId) {
        return mealTo.isNew()
                ? new Meal(userId, mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories())
                : new Meal(mealTo.getId(), userId, mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories());
    }
}