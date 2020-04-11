package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.UsersUtil.ADMIN_ID;

public class SecurityUtil {
    private static int authUser = ADMIN_ID;

    public static int authUserId() {
        return authUser;
    }

    public static void setAuthUser(int authUser) {
        SecurityUtil.authUser = authUser;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}