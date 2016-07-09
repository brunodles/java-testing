import junit.framework.TestCase;

/**
 * Created by bruno on 09/03/16.
 */
public class StackTest_junit3 extends TestCase {

    Stack<Integer> stack;

    public void setUp(){
        stack = new Stack<>();
    }

    public void testPreconditions(){
        assertTrue("it should be empty", stack.isEmpty());
        assertEquals("size should be 0", 0, stack.size());
    }

    public void testEnqueue(){
        stack.enqueue(1);
        assertFalse("it should not be empty", stack.isEmpty());
        assertEquals("size should be 1", 1, stack.size());
    }

    public void testWillFail(){
        assertFalse("it should fail", true);
    }

}