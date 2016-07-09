import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Created by bruno on 10/03/16.
 */

class StackSpeks : Spek() { init {
    given("a Stack of Integers") {
        on("the begining") {
            val stack = Stack<Int>()
            it("should be empty") {
                assertTrue(stack.isEmpty)
            }
            it("size should be 0") {
                assertEquals(0, stack.size())
            }
        }
        on("enqueue a new Item") {
            val stack = Stack<Int>()
            stack.enqueue(5)
            it("should not be empty") {
                assertFalse(stack.isEmpty)
            }
            it("size should be equals to 1") {
                assertEquals(1, stack.size())
            }
        }
        on("dequeue a Item and Stack size is equals to 1") {
            val stack = Stack<Int>();
            stack.enqueue(3)
            val item = stack.dequeue()
            it("should return the enqueued item") {
                assertEquals(3, item)
            }
            it("should be empty again") {
                assertTrue(stack.isEmpty)
            }
            it("should have size equals to 0") {
                assertEquals(0, stack.size())
            }
        }
        on("dequeue a Item and Stack size is equals to 0") {
            val stack = Stack<Int>();
            val item = stack.dequeue()
            it("should return null") {
                assertNull(item)
            }
            it("should continue empty") {
                assertTrue(stack.isEmpty)
            }
            it("should have size equals to 0") {
                assertEquals(0, stack.size())
            }
        }
    }
}
}