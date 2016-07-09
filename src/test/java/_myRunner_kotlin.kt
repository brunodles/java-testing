import my_runner.SpecJ
import org.junit.runner.RunWith

import com.mscharhag.oleaster.matcher.Matchers.expect
import my_runner.StaticSupport.*

/**
 * Created by bruno on 08/03/16.
 */
@RunWith(SpecJ::class)
class _myRunner_kotlin {
    internal var stack: Stack<Integer> = Stack<Integer>()
    internal var item: Integer? = null

    init {
        given("a Stack") {

            beforeEach { stack = Stack<Integer>() }

            `when`("was created") { then("should be empty") { expect(stack.isEmpty).toBeTrue() } }

            `when`("a item is enqueued") {
                before { stack.enqueue(Integer(5)) }
                then("should not be empty") { expect(stack.isEmpty).toBeFalse() }
                then("size should be 1") { expect(stack.size()).toEqual(1) }

            }

            `when`("a item is dequeued") {
                `when`("the size is equals to 1") {
                    before {
                        stack.enqueue(Integer(3))
                        item = stack.dequeue()
                    }
                    then("should return the enqueued item") { expect(item).toEqual(3) }
                    then("should be empty again") {
                        expect(stack.isEmpty).toBeTrue()
                        expect(stack.size()).toEqual(0L)
                    }
                }
                `when`("the size is equals to 0") {
                    then("should return null") { expect(stack.dequeue()).toBeNull() }
                    then("should continue empty") {
                        expect(stack.isEmpty).toBeTrue()
                        expect(stack.size()).toEqual(0L)
                    }
                }
            }
        }
    }

}