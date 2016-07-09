package my_runner;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 13/03/16.
 */
public class Suite extends Runner {
    String name;
    List<Runnable> befores = new ArrayList<>();
    List<Runnable> beforeEachs = new ArrayList<>();
    List<Runner> list = new ArrayList<>();
    List<Runnable> afterEachs = new ArrayList<>();
    List<Runnable> afters = new ArrayList<>();

    public Suite(String name) {
        this.name = name;
    }

    public void addBefore(Runnable beforeEach) {
        befores.add(beforeEach);
    }

    public void addBeforeEach(Runnable beforeEach) {
        beforeEachs.add(beforeEach);
    }

    public void add(Suite suite) {
        list.add(suite);
    }

    public void add(Spec spec) {
        list.add(spec);
    }

    public void addAfterEach(Runnable afterEach) {
        afterEachs.add(afterEach);
    }

    public void addAfter(Runnable afterEach) {
        afters.add(afterEach);
    }

    @Override
    public Description getDescription() {
        Description suiteDescription = Description.createSuiteDescription(name);
        list.forEach(item -> suiteDescription.addChild(item.getDescription()));
        return suiteDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        run(befores);
        for (Runner block : list) {
            run(beforeEachs);
            block.run(notifier);
            run(afterEachs);
        }
        run(afters);
    }

    private void run(List<Runnable> list) throws RuntimeException {
        for (Runnable block : list) block.run();
    }
}
