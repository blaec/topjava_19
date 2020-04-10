package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static final String MEALS_TABLE = "meals.jsp";
    public static final String MEAL_EDIT_FORM = "addMeal.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String forwardTo = "";

        if ("edit".equals(action)) {
            if (id != null) {
                log.debug("edit meal with id: " + id);
                forwardTo = MEAL_EDIT_FORM;
                request.setAttribute("action", "Edit");
                request.setAttribute("meal", Repository.getMeal(Integer.parseInt(id)));
            }
        } else if ("create".equals(action)) {
            log.debug("add new meal");
            forwardTo = MEAL_EDIT_FORM;
            request.setAttribute("action", "Create");
            request.setAttribute("meal", new MealTo());
        } else {
            if ("delete".equals(action)) {
                if (id != null) {
                    log.debug("delete user with id: " + id);
                    Repository.deleteMeal(Integer.parseInt(id));
                } else {
                    log.error("id is missing");
                }
                forwardTo = MEALS_TABLE;
            } else {
                log.debug("redirect to meals");
                forwardTo = MEALS_TABLE;
            }
            List<MealTo> meals = MealsUtil.filteredByCycles(Repository.getMeals(), MealsUtil.MIN, MealsUtil.MAX, 2000);
            request.setAttribute("meals", meals);
        }

        request.getRequestDispatcher(forwardTo).forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
