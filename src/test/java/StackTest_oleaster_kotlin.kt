import com.mscharhag.oleaster.matcher.Matchers.expect
import com.mscharhag.oleaster.runner.OleasterRunner
import com.mscharhag.oleaster.runner.StaticRunnerSupport.*
import org.junit.runner.RunWith

/**
 * Created by bruno on 08/03/16.
 */
@RunWith(OleasterRunner::class)
class StackTest_oleaster_kotlin {
    internal var stack: Stack<Integer> = Stack()
    internal var item: Integer? = null

    init {
        beforeEach { stack = Stack<Integer>() }

        describe("When it was created") {
            it("should be empty") {
                expect(stack.isEmpty).toBeTrue()
            }
        }

        describe("When a item is enqueued") {
            beforeEach { stack.enqueue(Integer(5)) }
            it("should not be empty") { expect(stack.isEmpty).toBeFalse() }
            it("size should be 1") { expect(stack.size()).toEqual(1) }
        }

        describe("When a item is dequeued") {
            describe("and the size is equals to 1") {
                beforeEach {
                    stack.enqueue(Integer(3))
                    item = stack.dequeue()
                }
                it("should return the enqueued item") { expect(item).toEqual(3) }
                it("should be empty again") {
                    expect(stack.isEmpty).toBeTrue()
                    expect(stack.size()).toEqual(0L)
                }
            }
            describe("and the size is equals to 0") {
                it("should return null") { expect(stack.dequeue()).toBeNull() }
                it("should continue empty") {
                    expect(stack.isEmpty).toBeTrue()
                    expect(stack.size()).toEqual(0L)
                }
            }
        }
    }
}