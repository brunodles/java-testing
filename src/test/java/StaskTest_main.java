/**
 * Created by bruno on 09/03/16.
 */
public class StaskTest_main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        if (!stack.isEmpty()) {
            System.out.println("Erro!");
            return;
        }
        stack.enqueue(1);
        if (stack.isEmpty() || stack.size() != 1) {
            System.out.println("Erro!");
            return;
        }
        Integer number = stack.dequeue();
        if (!number.equals(1)) {
            System.out.println("Erro!");
            return;
        }
        System.out.println("Ok");
    }
}