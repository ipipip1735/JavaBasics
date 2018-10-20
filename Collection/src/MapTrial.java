import java.util.*;

/**
 * Created by Administrator on 2018/9/15.
 */
public class MapTrial {
    public static void main(String[] args) {
        MapTrial mapTrial = new MapTrial();
//        mapTrial.base();
//        mapTrial.sortMap();
        mapTrial.navigableMap();
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
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("one", "11111");
        hashMap.put("one", "22222");
        System.out.println(hashMap);


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
