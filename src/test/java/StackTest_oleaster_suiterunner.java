import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

/**
 * Created by bruno on 08/03/16.
 */
@RunWith(OleasterSuiteRunner.class)
public class StackTest_oleaster_suiterunner {

    Stack<Integer> stack;
    Integer item;

    {
        beforeEach(() -> {
            stack = new Stack<>();
        });

        describe("When it was created", () -> {
            it("should be empty", () -> {
                expect(stack.isEmpty()).toBeTrue();
            });
            it("size should be zero", () -> {
                expect(stack.size()).toEqual(0L);
            });
        });
        describe("When a item is enqueued", () -> {
            beforeEach(() -> {
                stack.enqueue(5);
            });
            it("should not be empty", () -> {
                expect(stack.isEmpty()).toBeFalse();
            });
            it("size should be 1", () -> {
                expect(stack.size()).toEqual(1);
            });

        });

        describe("When a item is dequeued", () -> {
            describe("and the size is equals to 1", () -> {
                beforeEach(() -> {
                    stack.enqueue(3);
                    item = stack.dequeue();
                });
                it("should return the enqueued item", () ->{
                    expect(item).toEqual(3);
                });
                it("should be empty again", () -> {
                    expect(stack.isEmpty()).toBeTrue();
                    expect(stack.size()).toEqual(0L);
                });
            });
            describe("and the size is equals to 0", () -> {
                it("should return null", () -> {
                    expect(stack.dequeue()).toBeNull();
                });
                it("should continue empty", () -> {
                    expect(stack.isEmpty()).toBeTrue();
                    expect(stack.size()).toEqual(0L);
                });
            });
        });
    }

}