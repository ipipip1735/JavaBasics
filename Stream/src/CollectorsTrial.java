import java.util.*;
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
        collectorTrial.join();
//        collectorTrial.maxBy();
//        collectorTrial.minBy();
//        collectorTrial.reducing();
//        collectorTrial.summingInt();
//        collectorTrial.summarizingInt();
//        collectorTrial.averagingInt();
//        collectorTrial.toList();
//        collectorTrial.toMap();
//        collectorTrial.groupBy1();
//        collectorTrial.groupBy2();
//        collectorTrial.mapping();


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

        for (String name : map.keySet()) {
            System.out.println(">>" + name + "<<");
            System.out.println(map.get(name));
        }
    }





    private void groupBy2() {

        Map<String, Map<Integer, List<Person>>> map = this.list.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.groupingBy(Person::getAge)));

        for (String name : map.keySet()) {
            System.out.println("------" + name + "-------");

            for (Integer i : map.get(name).keySet()) {
                System.out.println("***" + i + "***");
                for (Person p : map.get(name).get(i)) {
                    System.out.println(p);
                }
            }
        }


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

        Optional<Person> result = list.stream().collect(Collectors.reducing((p1, p2) -> {
            System.out.println("p1 is " + p1);
            System.out.println("p2 is " + p2);
            return p1;
        }));

        System.out.println(">>" + result.get());
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
