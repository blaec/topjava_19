package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceAbstractTest;

@ActiveProfiles(Profiles.JPA)
public class MealServiceTestJPA extends MealServiceAbstractTest {
}
