package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static final String MEALS_TABLE = "meals.jsp";
    public static final String MEAL_EDIT_FORM = "addMeal.jsp";
    public static final String ACTION = "action";
    public static final String MEAL = "meal";
    public static final String MEALS = "meals";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        String id = request.getParameter("id");
        String forwardTo = "";

        if ("edit".equals(action)) {
            log.debug(String.format("GET | edit meal with id: %s", id));
            forwardTo = MEAL_EDIT_FORM;
            request.setAttribute(ACTION, "Edit");
            request.setAttribute(MEAL, MealRepository.getMealById(Integer.parseInt(id)));
        } else if ("create".equals(action)) {
            log.debug("GET | add new meal");
            forwardTo = MEAL_EDIT_FORM;
            request.setAttribute(ACTION, "Create");
            request.setAttribute(MEAL, new MealTo(LocalDateTime.now(), "", 0));
        } else {
            if ("delete".equals(action)) {
                log.debug(String.format("GET | delete meal with id: %s", id));
                MealRepository.deleteMeal(Integer.parseInt(id));
                forwardTo = MEALS_TABLE;
            } else {
                log.debug("redirect to meals");
                forwardTo = MEALS_TABLE;
            }
            request.setAttribute(MEALS, getAllMeals());
        }

        request.getRequestDispatcher(forwardTo).forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter(ACTION);
        LocalDateTime date = LocalDateTime.parse(request.getParameter("fdate"));
        String description = request.getParameter("fdescription");
        int calories = Integer.parseInt(request.getParameter("fcalories"));

        if ("edit".equals(action)) {
            log.debug(String.format("POST | entry edited %tD %s %d", date, description, calories));
            int id = Integer.parseInt(request.getParameter("id"));
            Meal updated = new Meal(id, date, description, calories);
            MealRepository.updateMeal(updated);
        } else if ("create".equals(action)) {
            log.debug(String.format("POST | new entry created %tD %s %d", date, description, calories));
            Meal created = new Meal(date, description, calories);
            MealRepository.createMeal(created);
        }
        request.setAttribute(MEALS, getAllMeals());
        request.getRequestDispatcher(MEALS_TABLE).forward(request, response);
    }

    private List<MealTo> getAllMeals() {
        return MealsUtil.filteredByCycles(MealRepository.getMeals(), MealsUtil.MIN, MealsUtil.MAX, 2000);
    }
}
