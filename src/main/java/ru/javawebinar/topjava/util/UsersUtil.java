package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final Integer ADMIN_ID = 1;
    public static final Integer USER_ID = 2;
    public static final List<User> USERS = Arrays.asList(
            new User(ADMIN_ID, "admin", "admin@mail.ma", "password", Role.ROLE_ADMIN),
            new User(USER_ID, "user", "user@mail.ma", "password", Role.ROLE_USER)
    );
}