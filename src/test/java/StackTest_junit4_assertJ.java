import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by bruno on 09/03/16.
 */
@RunWith(JUnit4.class)
public class StackTest_junit4_assertJ {

    Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<>();
    }

    @Test
    public void preconditions() {
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
    }

    @Test
    public void when_enqueue() {
        stack.enqueue(1);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.size()).isEqualTo(1);
    }
}