package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.assureMealBelongsToUser;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    @Autowired
    private MealRepository repository;

    public Meal create(Meal meal) {
        return repository.save(meal);
    };

    public void delete(int id, int userId) {
        assureMealBelongsToUser(getMealWithId(id), userId);
        checkNotFoundWithId(repository.delete(id), id);
    };

    public Meal get(int id, int userId) {
        Meal meal = getMealWithId(id);
        assureMealBelongsToUser(meal, userId);
        return checkNotFoundWithId(meal, id);
    };

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    };

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    };

    private Meal getMealWithId(int id) {
        return repository.get(id);
    }
}