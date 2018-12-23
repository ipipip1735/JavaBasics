import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 2018/11/13.
 */
public class MemoryLeak {
    public static void main(String[] args) {
        MemoryLeak memoryLeak = new MemoryLeak();

        memoryLeak.collectionLeak();
    }


    private void collectionLeak() {

        //正常情况
//        Person person = new Person(12, "bob");
//        List<Person> list = new LinkedList<>();
//        list.add(person);
//        person.setAge(15);
//        boolean b = list.remove(person);
//        System.out.println(b + "-" +list.size());


        //内存泄漏（hashCode()方法被重写导致的）
        List<String> list = new LinkedList<>();
        String s = new String("aaa");
        list.add(s);
        s = "iii";
        boolean b = list.remove(s);
        System.out.println(b + "-" + list.size());
    }

}
