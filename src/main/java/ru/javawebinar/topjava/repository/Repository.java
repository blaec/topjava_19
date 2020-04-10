package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository {
    private static Map<Integer, Meal> meals;
    private static AtomicInteger id;

    static {
        id = new AtomicInteger(0);
        meals = new ConcurrentHashMap<>();
        List<Meal> initMeals = Arrays.asList(
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(id.addAndGet(1), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        for (Meal meal : initMeals) {
            meals.put(meal.getId(), meal);
        }
    }

    public static void createMeal(Meal meal) {
        int id = Repository.id.addAndGet(1);
        meal.setId(id);
        meals.put(id, meal);
    }

    public static List<Meal> getMeals() {
        return new ArrayList<>(Repository.meals.values());
    }

    public static MealTo getMeal(int id) {
        return MealsUtil.createTo(meals.get(id), true);
    }

    public static void updateMeal(Meal meal) {
        int id = meal.getId();
        meals.replace(id, meals.get(id), meal);
    }

    public static void deleteMeal(int id) {
        meals.remove(id);
    }
}
