import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTrial {

    public static void main(String[] args) {
        QueueTrial queueTrial = new QueueTrial();
//        queueTrial.base();
        queueTrial.priority();


    }

    private void priority() {
        PriorityQueue<String> queue =
                new PriorityQueue<>(5, String::compareTo);
        queue.add("kb");
        queue.add("ab");
        queue.add("ab");
        queue.add("ab");
        queue.add("ab2");
        queue.add("ab1");


        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println(queue.size());

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        System.out.println(queue.size());







    }

    private void base() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        System.out.println("count is " + queue.size());

        System.out.println(queue.peek());
        System.out.println("count is " + queue.size());

        System.out.println(queue.poll());
        System.out.println("count is " + queue.size());

        System.out.println(queue.peek());
        System.out.println("count is " + queue.size());

    }
}
