import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by bruno on 09/03/16.
 */
public class StackSteps_cucumber_java8 {

    Stack<Integer> stack;
    Integer item;

    @Given("a Stack of Integer")
    public void aStackOfInteger() {
        stack = new Stack<Integer>();
    }

    @Then("it should be empty")
    public void itShouldBeEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @When("I enqueue (\\d+) to the stack")
    public void itShouldBeEmpty(int item) {
        stack.enqueue(item);
    }

    @Then("it should not be empty")
    public void itShouldNotBeEmpty() {
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @When("I dequeue")
    public void iDequeue() {
        item = stack.dequeue();
    }

    @Then("the result should be (\\d+)")
    public void theResultShouldBe(Integer expected) {
        assertEquals(expected, item);
    }
}