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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if ("delete".equals(action)) {
            if (id != null) {
                log.debug("delete user with id: " + id);
                Repository.deleteMeal(Integer.parseInt(id));
            } else {
                log.error("id is missing");
            }
        } else if ("edit".equals(action)) {
            log.debug("edit user with id: " + id);

        } else {
            log.debug("redirect to meals");

        }
        List<MealTo> meals = MealsUtil.filteredByCycles(Repository.getMeals(), MealsUtil.MIN, MealsUtil.MAX, 2000);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
