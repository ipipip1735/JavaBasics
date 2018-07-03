import java.util.LinkedList;
import java.util.Queue;

public class QueueTrial {

    public static void main(String[] args) {
        base();


    }

    private static void base() {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        System.out.println(queue.peek());
    }
}
