import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by bruno on 09/03/16.
 */
@RunWith(JUnit4.class)
public class StackTest_junit4_hamcrest {

    Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<>();
    }

    @Test
    public void preconditions() {
        assertThat("it should be empty", stack.isEmpty(), is(equalTo(true)));
        assertThat("size should be 0", stack.size(), is(equalTo(0L)));
    }

    @Test
    public void when_enqueue() {
        stack.enqueue(1);
        assertThat("it should not be empty", stack.isEmpty(), is(equalTo(false)));
        assertThat("size should be 1", stack.size(), is(equalTo(1L)));
    }
}