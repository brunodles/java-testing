package my_runner;

import org.junit.runner.Describable;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

/**
 * Created by bruno on 13/03/16.
 */
public class SpecJ extends ParentRunner<Runner> {

    public SpecJ(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected List<Runner> getChildren() {
        Suite suite = new Suite("Root");
        StaticSupport.setBuilder(suite);
        try {
            Object obj = getTestClass().getJavaClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("list " + suite.list.size());
        return suite.list;
    }

    @Override
    protected Description describeChild(Runner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(Runner child, RunNotifier notifier) {
        try {
            child.run(notifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
