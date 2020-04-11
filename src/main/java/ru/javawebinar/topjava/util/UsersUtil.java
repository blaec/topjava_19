package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("admin", "admin@mail.ma", "password", Role.ROLE_ADMIN),
            new User("user", "user@mail.ma", "password", Role.ROLE_USER)
    );
}