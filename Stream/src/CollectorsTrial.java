import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTrial {


    public List<Person> list = new ArrayList<Person>();

    public CollectorsTrial() {
        this.list.add(new Person(12, "aa"));
        this.list.add(new Person(13, "aa"));
        this.list.add(new Person(14, "cc"));
        this.list.add(new Person(12, "dd"));
    }

    public static void main(String[] args) {
        CollectorsTrial collectorTrial = new CollectorsTrial();
//        collectorTrial.join();
//        collectorTrial.maxBy();
        collectorTrial.minBy();
//        collectorTrial.reducing();
//        collectorTrial.summingInt();
//        collectorTrial.summarizingInt();
//        collectorTrial.averagingInt();
//        collectorTrial.toList();
//        collectorTrial.toMap();
//        collectorTrial.groupBy1();
//        collectorTrial.groupBy2();
//        collectorTrial.mapping();
//        collectorTrial.filtering();
//        collectorTrial.flatMapping();


    }

    private void filtering() {
        //简单例子，过滤小于12岁的人
//        List<Person> result = this.list.stream()
//                .collect(Collectors.filtering(p -> p.getAge()>12, Collectors.toList()));
//        System.out.println(result);


        //高级例子，配合groupingBy()一起使用
        Map<String, List<Person>> result = this.list.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.filtering(p -> p.getAge() > 12,
                                Collectors.toList())));
        System.out.println(result);

    }

    private void flatMapping() {

        //简单例子
//        List<List<Integer>> lists = new ArrayList<>();
//        lists.addr(Arrays.asList(Integer.valueOf(11), Integer.valueOf(34), Integer.valueOf(21)));
//        lists.addr(Arrays.asList(Integer.valueOf(3), Integer.valueOf(2)));
//        List<Integer> list = lists.stream()
//                .collect(Collectors.flatMapping(l -> l.stream().filter(o -> o > 15),
//                        Collectors.toList()));
//        System.out.println(list);


        //简单例子，使用size分类
//        Map<Integer, List<Integer>> map =
//                Stream.of(List.of(1, 2, 3, 4, 5, 6), List.of(7, 8, 9, 10))
//                        .collect(
//                                Collectors.groupingBy(
//                                        Collection::size,
//                                        Collectors.flatMapping(
//                                                l -> l.stream()
//                                                        .filter(i -> i % 2 == 0),
//                                                Collectors.toList())
//                                )
//                        );
//        System.out.println(map);


        //高级例子，介绍flatMapping()正真的力量
        Map<Integer, Set<String>> result =
                Stream.of(
                        new Family(1, Arrays.asList("aa", "tom", "bob")),
                        new Family(1, Arrays.asList("aa", "sam")),
                        new Family(2, Arrays.asList("aa", "chris", "jone", "anna")))
                        .collect(
                                Collectors.groupingBy(Family::getAddr,
                                        Collectors.flatMapping(f ->
                                                        f.getPerson().
                                                                stream().
                                                                filter(i -> i != "aa"),
                                                Collectors.toSet()
                                        )
                                )
                        );

        System.out.println(result);


    }


    private void mapping() {

        int result = this.list.stream()
                .collect(Collectors.mapping(Person::getAge,
                        Collectors.summingInt(age -> age)));
        System.out.println(result);

    }


    private void groupBy1() {
        Map<String, Integer> map = this.list.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.summingInt(Person::getAge)));

        System.out.println(map);
    }


    private void groupBy2() {

        Map<String, Map<Integer, List<Person>>> map = this.list.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.groupingBy(Person::getAge)));

        System.out.println(map);


    }


    private void toMap() {
        Map<String, Person> result = list.stream().collect(
                Collectors.toMap(
                        person -> person.name,
                        person -> person));

        for (String name : result.keySet()) {
            System.out.println("-------" + name + "------");
            System.out.println(result.get(name));
        }
    }


    private void toList() {
        List<Person> result = list.stream().collect(Collectors.toList());

        for (Person p : result) {
            System.out.println(p);
        }

    }


    private void averagingInt() {

        double result = list.stream().collect(Collectors.averagingInt(o -> o.age));

        System.out.println(result);


    }


    private void summarizingInt() {

        IntSummaryStatistics result = list.stream().collect(Collectors.summarizingInt(o -> o.age));

        System.out.println("Average is " + result.getAverage());
        System.out.println("Count is " + result.getCount());
        System.out.println("Max is " + result.getMax());
        System.out.println("Min is " + result.getMin());
        System.out.println("Sum is " + result.getSum());

    }


    private void summingInt() {
        int result = list.stream().collect(Collectors.summingInt(o -> o.age));
        System.out.println(result);
    }

    private void reducing() {

        //最简例子
//        Optional<Person> result = list.stream().collect(Collectors.reducing((p1, p2) -> {
//            System.out.println("p1 is " + p1);
//            System.out.println("p2 is " + p2);
//            return p1;
//        }));
//        System.out.println(">>" + result.get());

        //高级例子，先映射
        int result = list.stream().collect(
                Collectors.reducing(1, Person::getAge,
                        (p1, p2) -> {
                            System.out.println("p1 is " + p1);
                            System.out.println("p2 is " + p2);
                            return p1 + p2;
                        }));
        System.out.println(result);


    }


    private void minBy() {
        Integer[] integers = new Integer[]{8, 25, 7, 3};
        Stream<Integer> stream = Arrays.stream(integers);

        Optional<Integer> result = stream.collect(Collectors.minBy(Integer::compareTo));
        System.out.println(">>" + result.get());
    }


    private void maxBy() {

        Integer[] integers = new Integer[]{1, 25, 7, 3};
        Stream<Integer> stream = Arrays.stream(integers);

        Optional<Integer> result = stream.collect(Collectors.maxBy(((o1, o2) -> {
            System.out.println("o1 is " + o1);
            System.out.println("o2 is " + o2);
            return (o1.compareTo(o2) <= 0) ? -1 : 1;
        })));
        System.out.println(">>" + result.get());

    }


    public void join() {

        List<String> strings = new ArrayList<>();
        for (Person p : list) {
            strings.add(p.getName());
        }

        String result = strings.stream().collect(Collectors.joining());
        System.out.println(result);
    }


}
