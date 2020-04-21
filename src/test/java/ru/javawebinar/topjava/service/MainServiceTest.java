package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MainServiceTest {
    private static StringBuilder sb = new StringBuilder();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        private Instant start;
        private final String OPEN_DIVIDER = "/---------------------------------------------------------------\\";
        private final String CLOSE_DIVIDER = "\\---------------------------------------------------------------/";

        @Override
        protected void starting(Description description) {
            start = Instant.now();
            System.out.printf("%s\n STARTING %30s\n%s\n", OPEN_DIVIDER, description.getMethodName(), CLOSE_DIVIDER);
        }

        @Override
        protected void finished(Description description) {
            System.out.printf("%s\n FINISHED %30s %20d ms\n%s\n",
                    OPEN_DIVIDER, description.getMethodName(),
                    Duration.between(start, Instant.now()).toMillis(), CLOSE_DIVIDER);
        }
    };

}
