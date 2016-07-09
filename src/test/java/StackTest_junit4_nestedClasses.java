import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by bruno on 09/03/16.
 */
@RunWith(Enclosed.class)
public class StackTest_junit4_nestedClasses {

    static Stack<Integer> stack;

    public static class WhenItWasCreated {
        @Before
        public void setup() {
            stack = new Stack<>();
        }

        @Test
        public void shouldBeEmpty() {
            assertTrue("it should be empty", stack.isEmpty());
        }

        @Test
        public void shouldHaveSizeEqualsToZero() {
            assertEquals("size should be 0", 0, stack.size());
        }
    }

    public static class WhenEnqueueOneObject {
        @Before
        public void setup() {
            stack = new Stack<>();
            stack.enqueue(1);
        }

        @Test
        public void shouldNotBeEmpty() {
            assertFalse("it should not be empty", stack.isEmpty());
        }

        @Test
        public void shouldHaveSizeEqualsToOne() {
            assertEquals("size should be 1", 1, stack.size());
        }
    }

    @RunWith(Enclosed.class)
    public static class WhenDequeue {

        static Integer item;

        public static class AndSizeIsEqualsToOne {

            @Before
            public void setup() {
                stack = new Stack<>();
                stack.enqueue(2);
                item = stack.dequeue();
            }

            @Test
            public void shouldReturnTheEnqueuedItem() {
                assertEquals("should return the enqueued item", new Integer(2), item);
            }

            @Test
            public void shouldBeEmpty() {
                assertTrue("it .hould be empty", stack.isEmpty());
            }

            @Test
            public void shouldHaveSizeEqualsToZero() {
                assertEquals("size should be 0", 0, stack.size());
            }
        }

        public static class AndSizeIsEqualsToZero {

            @Before
            public void setup() {
                stack = new Stack<>();
                item = stack.dequeue();
            }

            @Test
            public void shouldReturnNull() {
                assertNull("shouuld return null", item);
            }

            @Test
            public void shouldContinueEmpty() {
                assertTrue("should continue empty", stack.isEmpty());
            }

            @Test
            public void shouldHaveSizeEqualsToZero() {
                assertEquals("should have size equals to zero", 0, stack.size());
            }
        }
    }
}