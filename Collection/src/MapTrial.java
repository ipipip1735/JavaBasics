import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2018/9/15.
 */
public class MapTrial {
    public static void main(String[] args) {
        MapTrial mapTrial = new MapTrial();
        mapTrial.base();
//        mapTrial.sortMap();
//        mapTrial.navigableMap();
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

    private void base() {
        HashMap<Person, String> hashMap = new HashMap<>();
        Person p = new Person(121);
        hashMap.put(p, "121");
        System.out.println(hashMap.containsKey(p));
        p.setAge(12);
        System.out.println(hashMap.containsKey(p));
        hashMap.remove(p);
        System.out.println(hashMap);


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


//        HashMap<Person, String> hashMap = new HashMap<>();
//        Person person1 = new Person(121);
//        Person person2 = new Person(121);
//        System.out.println(person1.hashCode());
//        System.out.println(person2.hashCode());
//        hashMap.put(person1, "121");
//        System.out.println(hashMap.containsKey(person2));

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

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }
}