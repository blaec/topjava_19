package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceAbstractTest;

@ActiveProfiles(Profiles.JDBC)
public class UserServiceTestJDBC extends UserServiceAbstractTest {
}
