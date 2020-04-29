package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceAbstractTest;

@ActiveProfiles(Profiles.JPA)
public class UserServiceTestJPA extends UserServiceAbstractTest {
}
