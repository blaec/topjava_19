package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;


@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(MealTo mealTo) {
        log.info("create {}", mealTo);
        int userId = SecurityUtil.authUserId();
        Meal meal = MealsUtil.convertTo(mealTo, userId);
        checkNew(meal);
        return service.create(meal);
    };

    public void delete(int id) {
        log.info("delete {}", id);
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
    };

    public MealTo get(int id) {
        log.info("get {}", id);
        int userId = SecurityUtil.authUserId();
        Meal meal = service.get(id, userId);
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories());
    };

    public Collection<MealTo> getAll() {
        log.info("getAll");
        int userId = SecurityUtil.authUserId();
        Collection<Meal> meals = service.getAll(userId);
        return MealsUtil.getTos(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY, userId);
    };

    public void update(MealTo mealTo, int id) {
        log.info("update {} with id={}", mealTo, id);
        assureIdConsistent(mealTo, id);
        int userId = SecurityUtil.authUserId();
        Meal meal = MealsUtil.convertTo(mealTo, userId);
        service.update(meal);
    };
}