package ru.javawebinar.topjava.web;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, ADMIN, USER);
                            }
                        }
                ));
    }

    @Test
    void getMeals() throws Exception {
        perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals",
                        new AssertionMatcher<List<MealTo>>() {
                            @Override
                            public void assertion(List<MealTo> actual) throws AssertionError {
                                List<Meal> actualMeals = actual.stream()
                                        .map(m -> new Meal(m.getId(), m.getDateTime(), m.getDescription(), m.getCalories()))
                                        .collect(Collectors.toList());
                                MEAL_MATCHER.assertMatch(actualMeals, MEALS);
                            }
                        }
                ));
    }

    @Test
    void getRoot() throws Exception {
        perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }

    // TODO add post test
    // .andExpect(redirectedUrl("/messages/123"));
//    @Test
//    void postUsers() throws Exception {
//        perform(post("/users"))
//                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(view().name("users"))
////                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
////                .andExpect(model().attribute("users",
////                        new AssertionMatcher<List<User>>() {
////                            @Override
////                            public void assertion(List<User> actual) throws AssertionError {
////                                USER_MATCHER.assertMatch(actual, ADMIN, USER);
////                            }
////                        }
////                ))
//        ;
//    }
}