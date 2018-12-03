package config;

import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.*;

/**
 * Custom class taken from:
 * <p/>
 * http://www.javacodegeeks.com/2015/03/spring-boot-integration-testing-with-selenium.html
 * <p/>
 * 
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestExecutionListeners(listeners = SeleniumTestExecutionListener.class, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface SeleniumTest {

    /**
     * The base URL to use for this test
     *
     * @return the base URL.
     */
    String baseUrl() default "http://localhost:8080";
}
