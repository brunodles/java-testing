import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bruno on 09/03/16.
 */
@RunWith(JUnit4.class)
public class StackTest_junit4 {

    Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<>();
    }

    @Test
    public void preconditions() {
        assertTrue("it should be empty", stack.isEmpty());
        assertEquals("size should be 0", 0, stack.size());
    }

    @Test
    public void when_enqueue() {
        stack.enqueue(1);
        assertFalse("it should not be empty", stack.isEmpty());
        assertEquals("size should be 1", 1, stack.size());
    }
}