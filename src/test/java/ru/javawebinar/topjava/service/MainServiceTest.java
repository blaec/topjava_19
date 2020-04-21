package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MainServiceTest {
    private static final String OPEN_DIVIDER = "/---------------------------------------------------------------\\";
    private static final String SPLITTER = "=================================================================";
    private static final String CLOSE_DIVIDER = "\\---------------------------------------------------------------/";
    private static StringBuilder sb = new StringBuilder(
            String.format("%s\nMealServiceTest results:\n%s\n", OPEN_DIVIDER, SPLITTER)
    );
    private static long total;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @AfterClass
    public static void close() {
        sb.append(String.format("%s\n TOTAL%55d ms\n%s\n", SPLITTER, total, CLOSE_DIVIDER));
        System.out.println(sb.toString());
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        private Instant start;
        private String name;

        @Override
        public Statement apply(Statement base, Description description) {
            name = description.getMethodName();
            return super.apply(base, description);
        }

        @Override
        protected void starting(Description description) {
            start = Instant.now();
            System.out.printf("%s\n STARTING %30s\n%s\n",
                    OPEN_DIVIDER, name, CLOSE_DIVIDER);
        }

        @Override
        protected void finished(Description description) {
            long duration = Duration.between(start, Instant.now()).toMillis();
            total += duration;
            sb.append(String.format("%-25s%36d ms\n", name, duration));
            System.out.printf("%s\n FINISHED %30s %20d ms\n%s\n",
                    OPEN_DIVIDER, name, duration, CLOSE_DIVIDER);
        }
    };

}
