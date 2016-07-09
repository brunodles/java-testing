/*
* Copyright 2014 Michael Scharhag
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.mscharhag.oleaster.runner.Invokable;
import com.mscharhag.oleaster.runner.OleasterTest;
import com.mscharhag.oleaster.runner.StaticSupportingSuiteBuilder;
import com.mscharhag.oleaster.runner.suite.*;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class OleasterSuiteRunner extends ParentRunner<Suite> {

    public OleasterSuiteRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected List<Suite> getChildren() {
        SuiteBuilder suiteBuilder = this.createSuiteBuilder();
        SuiteDefinition baseSuiteDefinition = this.createBaseSuiteDefinition(suiteBuilder);
        SuiteDefinitionEvaluator evaluator = this.createSuiteDefinitionEvaluator();

        Suite suite = evaluator.evaluate(baseSuiteDefinition, suiteBuilder);
        if (suite.getSpecs().size()>0) return Arrays.asList(suite);
        return suite.getSuites();
    }

    protected SuiteBuilder createSuiteBuilder() {
        return new StaticSupportingSuiteBuilder();
    }

    protected SuiteDefinition createBaseSuiteDefinition(SuiteBuilder suiteBuilder) {
        return new SuiteDefinition(null, null, () -> {
            Object obj = getTestClass().getJavaClass().newInstance();
            if (obj instanceof OleasterTest) {
                ((OleasterTest) obj).buildTestSuite(suiteBuilder);
            }
        });
    }

    protected SuiteDefinitionEvaluator createSuiteDefinitionEvaluator() {
        return new SuiteDefinitionEvaluator();
    }

    @Override
    protected Description describeChild(Suite child) {
        String description = child.getDescription();
        if (description == null || description.isEmpty())
            description = getTestClass().getName();
        Description suiteDescription = Description.createSuiteDescription(description);
        child.getSuites().forEach(cSuite -> suiteDescription.addChild(describeChild(cSuite)));
        child.getSpecs().forEach(cSpec -> suiteDescription.addChild(describeSpec(cSpec)));
        return suiteDescription;
    }

    @Override
    protected void runChild(Suite suite, RunNotifier notifier) {
        Description description = describeChild(suite);
        suite.getSpecs().forEach(spec -> {
            description.addChild(describeSpec(spec));
            notifier.fireTestStarted(description);
            runSpec(spec, notifier);
            notifier.fireTestFinished(description);
        });
        suite.getSuites().forEach(childSuite -> {
            runChild(childSuite, notifier);
        });
    }

    protected void runSpec(Spec spec, RunNotifier notifier) {
        List<Spec> specs = spec.getSuite().getSpecs();
        boolean suiteHasNoSpecs = specs.isEmpty();
        boolean isFirstSpec = specs.indexOf(spec) == 0;
        boolean isLastSpec = specs.indexOf(spec) == specs.size() - 1;

        if (suiteHasNoSpecs || isFirstSpec) {
            runBeforeCallbacks(spec);
        }

        runBeforeEachCallbacks(spec);
        Description description = describeSpec(spec);
        runLeaf(spec, description, notifier);
        runAfterEachCallbacks(spec);

        if (suiteHasNoSpecs || isLastSpec) {
            runAfterCallbacks(spec);
        }
    }

    protected Description describeSpec(Spec child) {
        return Description.createTestDescription(
                merge(getTestClass().getName(),getFullDescription(child.getSuite())), "It " + child.getDescription());
    }

    private String merge(String first, String last) {
        if (first == null || first.isEmpty()) return last;
        if (last == null || last.isEmpty()) return first;
        return String.format("%s.%s", first, last);
    }

    private String getFullDescription(Suite suite) {
        if (suite.getParent() != null) {
            String parentDescription = getFullDescription(suite.getParent());
            if (parentDescription != null) {
                return String.format("%s.%s", parentDescription, suite.getDescription());
            }
        }
        String description = suite.getDescription();
        if (description == null)
            return getTestClass().getName();
        return description;
    }

    private void runBeforeEachCallbacks(Spec spec) {
        this.runInvokables(this.collectInvokables(spec.getSuite(), Suite::getBeforeEachHandlers, true));
    }

    private void runBeforeCallbacks(Spec spec) {
        this.runInvokables(this.collectInvokables(spec.getSuite(), Suite::getBeforeHandlers, true));
    }


    private void runAfterEachCallbacks(Spec spec) {
        this.runInvokables(this.collectInvokables(spec.getSuite(), Suite::getAfterEachHandlers, false));
    }

    private void runAfterCallbacks(Spec spec) {
        this.runInvokables(this.collectInvokables(spec.getSuite(), Suite::getAfterHandlers, false));
    }

    private List<Invokable> collectInvokables(Suite suite, Function<Suite, List<Invokable>> method, boolean reverseOrder) {
        List<List<Invokable>> lists = new ArrayList<>();
        Suite parent = suite;
        while (parent != null) {
            lists.add(new ArrayList<>(method.apply(parent)));
            parent = parent.getParent();
        }

        if (reverseOrder) {
            Collections.reverse(lists);
        }

        List<Invokable> flatList = new ArrayList<>();
        for (List<Invokable> list : lists) {
            flatList.addAll(list);
        }
        return flatList;
    }

    private void runInvokables(List<Invokable> invokables) {
        invokables.forEach(callback -> {
            try {
                callback.invoke();
            } catch (Exception e) {
                throw new RuntimeException("An exception occurred while running invokable: " + e.getMessage(), e);
            }
        });
    }
}
