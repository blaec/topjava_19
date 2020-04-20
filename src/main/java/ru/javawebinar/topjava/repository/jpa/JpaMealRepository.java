package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        meal.setUser(em.getReference(User.class, userId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createQuery("" +
                    "UPDATE Meal m" +
                    "   SET m.description=:description, m.calories=:calories, m.dateTime=:dateTime " +
                    " WHERE m.id=:id AND m.user.id=:userId");
            return (query.setParameter("description", meal.getDescription())
                         .setParameter("calories", meal.getCalories())
                         .setParameter("dateTime", meal.getDateTime())
                         .setParameter("id", meal.getId())
                         .setParameter("userId", userId).executeUpdate() == 0)
                    ? null
                    : meal;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createQuery("DELETE FROM Meal m " +
                "WHERE m.id=:id AND m.user.id=:userId");
        return query
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        boolean isOwnMeal =  meal != null && meal.getUser().getId().equals(userId);
        return isOwnMeal ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        Query query = em.createQuery("SELECT m " +
                "FROM Meal m " +
                "WHERE m.user.id=:userId " +
                "ORDER BY m.dateTime DESC");
        return query
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createQuery("SELECT m " +
                "FROM Meal m " +
                "WHERE m.user.id=:userId AND m.dateTime>=:startDate AND m.dateTime<:endDate " +
                "ORDER BY m.dateTime DESC ");
        return query
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}