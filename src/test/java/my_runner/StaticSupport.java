package my_runner;

/**
 * Created by bruno on 13/03/16.
 */
public class StaticSupport {
    private static Suite suite;

    private StaticSupport() {
    }

    public static void before(Runnable block) {
        checkSuite("before");
        suite.addBefore(block);
    }

    public static void beforeEach(Runnable block) {
        checkSuite("beforeEach");
        suite.addBeforeEach(block);
    }

    public static void afterEach(Runnable block) {
        checkSuite("afterEach");
        suite.addAfterEach(block);
    }

    public static void after(Runnable block) {
        checkSuite("after");
        suite.addAfter(block);
    }

    public static void given(String string, Runnable block) {
        checkSuite("given");
        buildInnerSuite("Given " + string, block);
    }

    public static void when(String string, Runnable block) {
        checkSuite("when");
        buildInnerSuite("When " + string, block);
    }
    public static void on(String string, Runnable block) {
        checkSuite("on");
        buildInnerSuite("On " + string, block);
    }

    public static void describe(String string, Runnable block) {
        checkSuite("describe");
        buildInnerSuite(string, block);
    }

    public static void describe(Class<?> classUnderTest, String string, Runnable block) {
        checkSuite("describe");
        buildInnerSuite(classUnderTest.getSimpleName() + string, block);
    }

    public static void describe(Class<?> classUnderTest, Runnable block) {
        checkSuite("describe");
        buildInnerSuite(classUnderTest.getSimpleName(), block);
    }

    public static void then(String string, Runnable block) {
        checkSuite("then");
        suite.add(new Spec("Then " + string, block));
    }

    private static void checkSuite(Object methodName) {
        if (suite == null)
            throw new RuntimeException(
                    String.format("Suite is null, may you've call '%s' on wrong place.", methodName));
    }

    private static void buildInnerSuite(String string, Runnable block) {
        Suite old = suite;
        suite = new Suite(string);
        run(block);
        old.add(suite);
        suite = old;
    }

    public static void setBuilder(Suite suite) {
        StaticSupport.suite = suite;
    }

    private static void run(Runnable block) {
        try {
            block.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
