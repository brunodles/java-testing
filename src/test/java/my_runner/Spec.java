package my_runner;

import org.junit.runner.Describable;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

/**
 * Created by bruno on 13/03/16.
 */
public class Spec extends Runner {
    String name;
    private Runnable block;

    public Spec(String string, Runnable block) {
        name = string;
        this.block = block;
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription("", name);
    }

    @Override
    public void run(RunNotifier notifier) {
        Description description = getDescription();
        notifier.fireTestStarted(description);
        try {
            block.run();
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            notifier.fireTestFailure(new Failure(description, e));
        }
        notifier.fireTestFinished(description);
    }
}
