import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/9/15.
 */
public class MapTrial {
    public static void main(String[] args) {
        MapTrial mapTrial = new MapTrial();
//        mapTrial.map();
//        mapTrial.mapCURD();

//        mapTrial.sortMap();
//        mapTrial.navigableMap();

        mapTrial.hashMap();
//        mapTrial.LinkedHashMap();
//        mapTrial.LinkedHashMapStale();

//        mapTrial.concurrentHashMap(); //线程安全，支持并行写入
    }

    private void hashMap() {

        Map<String, String> hashMap = new HashMap<String, String>();

        //不是字母排序
        hashMap.put("app_id", "001");
        hashMap.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"001\"}");
        hashMap.put("charset", "utf-8");
        hashMap.put("method", "alipay.trade.app.pay");
        hashMap.put("sign_type", "RSA2");
        hashMap.put("timestamp", "2016-07-29 16:55:53");
        hashMap.put("version", "1.0");


        //不能保存顺序存储
//                hashMap.put("one", "111");
//                hashMap.put("two", "222");
//                hashMap.put("three", "333");
//                hashMap.put("four", "444");


        for (String k : hashMap.keySet()) System.out.println("key is " + k);


    }

    private void concurrentHashMap() {
        Map<String, Integer> map = new HashMap<>(); //非线程安全，遍历时写操作将抛异常
//        Map<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 5; i++) {
            map.put("id"+i, Integer.valueOf(i));
        }

        for (String s : map.keySet()) {
            if(s.equals("id1"))map.put(s, Integer.valueOf(99));//可以put，但不能remove()
            if(s.equals("id1"))map.remove(s); //抛异常
        }

        System.out.println(map);

    }

    private void LinkedHashMapStale() {
        LinkedHashMap<String, Integer> linkedHashMap =
//                new LinkedHashMap<>(0, 0.75f, false){
                new LinkedHashMap<>(0, 0.75f, true) {
                    private static final int MAX_ENTRIES = 3;

                    @Override
                    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                        System.out.println("~~removeEldestEntry~~");
                        System.out.println(eldest);
                        return size() > MAX_ENTRIES;
//                        return super.removeEldestEntry(eldest);
                    }
                };

        linkedHashMap.put("zero", 0);
        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
//        linkedHashMap.put("three", 3);
//        linkedHashMap.put("four", 4);
//        linkedHashMap.put("one1", 11);
//        linkedHashMap.put("two1", 12);
//        linkedHashMap.put("three1", 13);
//        linkedHashMap.put("four1", 14);
        System.out.println(linkedHashMap);

//        linkedHashMap.put("zero", 0);
//        System.out.println(linkedHashMap);

//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("thirty", 30);
//        map.put("fourty", 40);
//        System.out.println(map);
//        linkedHashMap.merge("one", 90, (k,v)->{
//            System.out.println("k is " + k);
//            System.out.println("v is " + v);
//            return 99;
//        });
//        System.out.println(linkedHashMap);

//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("thirty", 30);
//        map.put("fourty", 40);
//        System.out.println(map);
//        linkedHashMap.putAll(map);
//        System.out.println(linkedHashMap);

//        linkedHashMap.putIfAbsent("one", 8);
//        System.out.println(linkedHashMap);


//        linkedHashMap.computeIfAbsent("five", (k) -> {
//            System.out.println("k is " + k);
//            return 99;
//        });

//        linkedHashMap.computeIfAbsent("five", (k) -> {
//            System.out.println("k is " + k);
//            return 99;
//        });

//        linkedHashMap.computeIfPresent("one", (k, v) -> {
//            System.out.println("k is " + k);
//            System.out.println("v is " + v);
//            return 99;
//        });
//        System.out.println(linkedHashMap);


    }


    private void LinkedHashMap() {

        LinkedHashMap<String, Integer> linkedHashMap =
//                new LinkedHashMap<>(0, 0.75f, false);
                new LinkedHashMap<>(0, 0.75f, true);
        linkedHashMap.put("zero", 0);
        System.out.println(linkedHashMap);
        linkedHashMap.put("one", 1);
        System.out.println(linkedHashMap);
        linkedHashMap.put("two", 2);
        System.out.println(linkedHashMap);
        linkedHashMap.put("three", null);
        System.out.println(linkedHashMap);
        linkedHashMap.put("four", 4);
        System.out.println(linkedHashMap);

//        linkedHashMap.get("two");
//        System.out.println(linkedHashMap);

//        linkedHashMap.computeIfAbsent("one", (k)->{
//            System.out.println("k is " + k);
//            return 99;
//        });
//        System.out.println(linkedHashMap);


//        linkedHashMap.replace("five", 65);
//        System.out.println(linkedHashMap);
//        HashMap<String, Integer> hashMap = new HashMap<>();


        HashMap<String, Integer> map = new HashMap<>();
        map.put("thirty", 30);
        map.put("fourty", 40);
        map.put("sfourty1", 410);
        map.put("tfourty2", 420);
        map.put("fourty3", 430);
        System.out.println(map);

        linkedHashMap.putAll(map);
        System.out.println(linkedHashMap);


    }

    private void navigableMap() {
        NavigableMap<String, String> navigableMap = new TreeMap<>();
        navigableMap.put("python", ".py");
        navigableMap.put("c++", ".cpp");
        navigableMap.put("kotlin", ".kt");
        navigableMap.put("golang", ".go");
        navigableMap.put("java", ".java");
        System.out.println(navigableMap);

        //返回单个entry
//        Map.Entry<String, String> lowerEntry = navigableMap.lowerEntry("jave");
//        System.out.println(lowerEntry);
//        Map.Entry<String, String> floorEntry = navigableMap.floorEntry("jave");
//        System.out.println(floorEntry);
//        Map.Entry<String, String> ceilingEntry = navigableMap.ceilingEntry("jave");
//        System.out.println(ceilingEntry);
//        Map.Entry<String, String> higherEntry = navigableMap.higherEntry("jave");
//        System.out.println(higherEntry);

        //返回单个key
//        String lower = navigableMap.lowerKey("jave");
//        System.out.println(lower);
//        String floor = navigableMap.floorKey("jave");
//        System.out.println(floor);
//        String ceiling = navigableMap.ceilingKey("jave");
//        System.out.println(ceiling);
//        String higher = navigableMap.higherKey("jave");
//        System.out.println(higher);

        //返回反序子集
        System.out.println(navigableMap.descendingKeySet());
        System.out.println(navigableMap.descendingMap());

    }

    private void map() {

        //输出顺序和插入顺序不一致
//        Map<String, Integer> map = new HashMap<>();
//        map.put("three", 3);
//        map.put("one", 1);
//        map.put("two", 2);
//
//        for (String s : map.keySet()) {
//            System.out.println(s);
//        }
//        for (Integer i : map.values()) {
//            System.out.println(i);
//        }
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry);
//        }


        //由于重写hashCode导致内存泄漏
//        HashMap<Person, String> hashMap = new HashMap<>();
//        Person p = new Person(121, "bob");
//        hashMap.put(p, "bob");
//        System.out.println(hashMap.containsKey(p));
////        p.setAge(12);
//        p.setName("sam");
//        System.out.println(hashMap.containsKey(p));
//        hashMap.remove(p);
//        System.out.println(hashMap);


        //Hashcode相同的情况，haseCode()方法被重写了
//        try {
//            HashMap<URI, String> hashMap = new HashMap<>();
//            URI uri1 = new URI("content://abc/");
//            URI uri2 = new URI("content://abc/");
//            System.out.println(uri1.hashCode());
//            System.out.println(uri2.hashCode());
//
//            hashMap.put(uri1, "abc");
//            System.out.println("has is " + hashMap.containsKey(uri2));
//            System.out.println(hashMap);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }


//        try {
//            HashMap<URL, String> hashMap = new HashMap<>();
//            URL url1 = new URL("http://abc/");
//            URL url2 = new URL("http://abc/");
//            System.out.println(url1.hashCode());
//            System.out.println(url2.hashCode());
//
//            hashMap.put(url1, "abc");
//            System.out.println("has is " + hashMap.containsKey(url2));
//            System.out.println(hashMap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }


        //hashcode test
//        HashMap<Person, String> hashMap = new HashMap<>();
//        Person person1 = new Person(121);
//        Person person2 = new Person(121);
//        System.out.println(person1.hashCode());
//        System.out.println(person2.hashCode());
//        hashMap.put(person1, "121");
//        System.out.println(hashMap.containsKey(person2));

    }


    private void mapCURD() {

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("zero", 0);
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        System.out.println(hashMap);

        //put()操作
//        hashMap.put("two", 100);
//        System.out.println(hashMap);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("thirty", 30);
        map.put("fourty", 40);
        System.out.println(map);

        hashMap.putAll(map);
        System.out.println(hashMap);


//        Integer integer = hashMap.putIfAbsent("four", 65);
//        System.out.println("integer is " + integer);
//        System.out.println("hashMap is " + hashMap);
//
//        integer = hashMap.putIfAbsent("five", 65);
//        System.out.println("integer is " + integer);
//        System.out.println("hashMap is " + hashMap);


        //replace()操作
//        hashMap.replace("zero", 100); //等价于put()某个已存在的KEY
//        System.out.println(hashMap);


//        hashMap.replaceAll((k, v)->{
//            System.out.println("k is " + k);
//            System.out.println("v is " + v);
//            return v;
//        });
//        System.out.println(hashMap);


        //mege()操作
////        hashMap.merge("one", 11, (origin, newV)->{
//        hashMap.merge("five", 11, (origin, newV)->{
//            System.out.println("origin is " + origin);
//            System.out.println("new is " + newV);
//            return newV;
//        });
//        System.out.println(hashMap);


        //compute()操作
//        hashMap.compute("one", (k, v)->{
//        hashMap.compute("five", (k, v)->{
//            System.out.println("k is " + k);
//            System.out.println("v is " + v);
//            return 99;
//        });
//        System.out.println(hashMap);

//        hashMap.computeIfAbsent("one", (k)->{
//            System.out.println("k is " + k);
//            return 99;
//        });
//        System.out.println(hashMap);


//        hashMap.computeIfPresent("five", (k, v)->{
//            System.out.println("k is " + k);
//            System.out.println("v is " + v);
//            return 99;
//        });
//        System.out.println(hashMap);


    }

    private void sortMap() {
        //默认排序
//        SortedMap<String, String> sortedMap = new TreeMap<>();
//        sortedMap.put("python", ".py");
//        sortedMap.put("c++", ".cpp");
//        sortedMap.put("kotlin", ".kt");
//        sortedMap.put("golang", ".go");
//        sortedMap.put("java", ".java");
//        System.out.println(sortedMap);


        //反序
//        SortedMap<String, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
//        sortedMap.put("python", ".py");
//        sortedMap.put("c++", ".cpp");
//        sortedMap.put("kotlin", ".kt");
//        sortedMap.put("golang", ".go");
//        sortedMap.put("java", ".java");
//        System.out.println(sortedMap);

        //SortedMap<String, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.put("python", ".py");
        sortedMap.put("c++", ".cpp");
        sortedMap.put("kotlin", ".kt");
        sortedMap.put("golang", ".go");
        sortedMap.put("java", ".java");
        System.out.println(sortedMap);

        SortedMap<String, String> headMap = sortedMap.headMap("jave");
        System.out.println(headMap);
        SortedMap<String, String> tailMap = sortedMap.tailMap("jave");
        System.out.println(tailMap);
        SortedMap<String, String> subMap = sortedMap.subMap("jave", "kotlin");
        System.out.println(subMap);

//        Set<Map.Entry<String, String>> set = sortedMap.entrySet();
//        for (Map.Entry<String, String> m : set) {
//            System.out.println(m.getKey());
//            System.out.println(m.getValue());
//        }

    }
}


class Person {
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}