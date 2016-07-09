import my_runner.SpecJ;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static my_runner.StaticSupport.*;

/**
 * Created by bruno on 08/03/16.
 */
@RunWith(SpecJ.class)
public class _myRunner {
    Stack<Integer> stack;
    Integer item;

    {
        describe(Stack.class, () -> {

            beforeEach(() -> {
                stack = new Stack<>();
            });

            when("is created", () -> {
                then("should be empty", () -> {
                    expect(stack.isEmpty()).toBeTrue();
                });
            });

            when("a item is enqueued", () -> {
                before(() -> {
                    stack.enqueue(5);
                });
                then("should not be empty", () -> {
                    expect(stack.isEmpty()).toBeFalse();
                });
                then("size should be 1", () -> {
                    expect(stack.size()).toEqual(1);
                });

            });

            when("a item is dequeued", () -> {
                when("the size is equals to 1", () -> {
                    before(() -> {
                        stack.enqueue(3);
                        item = stack.dequeue();
                    });
                    then("should return the enqueued item", () -> {
                        expect(item).toEqual(3);
                    });
                    then("should be empty again", () -> {
                        expect(stack.isEmpty()).toBeTrue();
                        expect(stack.size()).toEqual(0L);
                    });
                });
                when("the size is equals to 0", () -> {
                    then("should return null", () -> {
                        expect(stack.dequeue()).toBeNull();
                    });
                    then("should continue empty", () -> {
                        expect(stack.isEmpty()).toBeTrue();
                        expect(stack.size()).toEqual(0L);
                    });
                });
            });
        });
    }
}