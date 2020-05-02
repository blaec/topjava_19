package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
public class JspMealController {

    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String getMeals(Model model) {
        List<MealTo> meals = MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
        model.addAttribute("meals", meals);
        return "meals";
    }

    @GetMapping("delete/{id}")
    public String deleteMeal(@PathVariable("id") int id) {
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @GetMapping("update/{id}")
    public String updateMeal(Model model, @PathVariable("id") int id) {
        final Meal meal = service.get(id, SecurityUtil.authUserId());
        model.addAttribute("meal", meal);
        model.addAttribute("action", "Edit meal");
        return "mealForm";
    }

    @GetMapping("create")
    public String createMeal(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        model.addAttribute("action", "Create meal");
        return "mealForm";
    }

    @PostMapping("meals")
    public String setUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            service.create(meal, SecurityUtil.authUserId());
        } else {
            meal.setId(getId(request));
            service.update(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
