import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(T item) {
        list.add(item);
    }

    public T dequeue() {
        int index = list.size() - 1;
        if (index < 0 || index >= list.size())
            return null;
        T result = list.get(index);
        list.remove(index);
        return result;
    }

    public long size() {
        return list.size();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.enqueue(1);
        Integer item = stack.dequeue();
        if (item == 1){
            System.out.println("Ok");
        } else {
            System.out.println("Error");
        }
    }
}
