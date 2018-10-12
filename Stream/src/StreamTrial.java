import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/9/20.
 */

public class StreamTrial {

    public static void main(String[] args) {
        StreamTrial streamDemo = new StreamTrial();


        //构造方式
//        streamDemo.builder();
//        streamDemo.of();
        streamDemo.iterate();


        //中间操作（Stateful）
//        streamDemo.distinct();
//        streamDemo.sorted();
//        streamDemo.limit();
//        streamDemo.skip();

        //中间操作（Stateless）
//        streamDemo.filter();
//        streamDemo.unordered();
//        streamDemo.map();
//        streamDemo.flatMap();
//        streamDemo.peek();


        //结果操作
//        streamDemo.reduce();
//        streamDemo.aggregation();
//        streamDemo.reduceParallel();
//        streamDemo.collect();
//        streamDemo.joining();
//        streamDemo.toArray();

//        streamDemo.groupBy();
//        streamDemo.partitioningBy();
//        streamDemo.collectingAndThen();
//        streamDemo.chain();


    }

    private void iterate() {

        //简单例子
//        Stream.iterate("Tom", s -> s.length() < 25, s -> s += new Random().nextInt(10))
//                .forEach(System.out::println);


        //等价操作，iterate()等价于for语句
//        for (String s = "-"; !s.equals("-sssss") ; s+="s") {
//            System.out.println(s);
//        }


        //生成流，映射为其他对象
//        Stream.iterate("Tom0", s -> !s.equals("Tom9"),
//                s -> "Tom" + new Random().nextInt(10))
//                .map(s -> new Person(new Random().nextInt(100), s))
//                .forEach(p -> System.out.println(p));


        //无限流
//        IntStream stream = IntStream.iterate(1, n -> n < 10, n-> n++);
//        stream.forEach(s -> System.out.println(s));


//        Stream<String> stream = Stream.of("xml", "mysql", "oracle", "db2");
//        stream.takeWhile(s -> s.length() < 3)
//                .forEach(System.out::println);


//        Stream<String> stream = Stream.iterate("", s -> s + "s")
//                .takeWhile(s -> s.length() < 10);
//        stream.forEach(System.out::println);


//        String[] s = {
//                "s",
//                "ss",
//                "sssssssss",
//                "sss",
//                "sss",
//                "ssss",
//                "sssss",
//                "ssssss",
//                "sssssss",
//                "www"
//        };

//        Stream<String> stringStream = Arrays.stream(s);
//        Set<String> strings = stringStream.collect(Collectors.toSet());


//        strings.stream().forEach(s1 -> System.out.println(s1));
//        strings.stream().filter(k -> k.contains("w")).forEach(s1 -> System.out.println(s1));
//        strings.stream().unordered().takeWhile(k -> k.contains("s")).forEach(s1 -> System.out.println(s1));
//        strings.stream().takeWhile(k -> k.contains("s")).forEach(s1 -> System.out.println(s1));

//        stringStream.unordered().takeWhile(k -> k.length() < 4)
//                .forEach(System.out::println);



        Set<Integer> numbers = Set.of(2, 4, 6, 3, 8);
        numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);

    }


    private void of() {
        Stream<String> stringStream = Stream.of("xml", "mysql", "oracle");
        stringStream.forEach(System.out::println);
    }

    private void builder() {
        Stream.Builder<String> b = Stream.builder();
        b.accept("a");
        b.accept("b");
        b.accept("c");
        b.accept("d");
        b.accept("e");

        Stream<String> s = b.build();
        s.forEach(System.out::println);
    }

    private void aggregation() {
        double totalIncome = Employee.persons()
                .stream()
                .mapToDouble(Employee::getIncome)
                .sum();
        System.out.println("Total Income:  " + totalIncome);
    }

    private void collectingAndThen() {
        List<String> names = Employee.persons()
                .stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        result -> {
                            System.out.println(result);
                            return Collections.unmodifiableList(result);
                        }));
        System.out.println(names);

    }

    private void partitioningBy() {
        Map<Boolean, List<Employee>> partionedByMaleGender =
                Employee.persons()
                        .stream()
                        .collect(Collectors.partitioningBy(Employee::isMale));
        System.out.println(partionedByMaleGender);

    }

    private void groupBy() {
        Map<Employee.Gender, List<Employee>> byDept
                = Employee.persons().stream()
                .collect(Collectors.groupingBy(Employee::getGender));

        for (Employee.Gender gender : byDept.keySet()) {
            System.out.println(">> " + gender + " <<");
            for (Employee employee : byDept.get(gender)) {
                System.out.println(employee.getName());
            }
        }


        Map<Employee.Gender, List<Employee>> namesByGender = Employee.persons()
                .stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.toList()));


        System.out.println(namesByGender);
    }

    private void joining() {
//        List<Employee> persons = Employee.persons();
//        String names = persons.stream()
//                .map(Employee::getName)
//                .collect(Collectors.joining(",", "p ", " s"));
//        System.out.println(names);


        String s = Employee.persons()
                .stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println(s);

    }

    private void toArray() {
        //最简方法（只能返回Object数组）
//        List<Employee> employeeList = Employee.persons();
//        Object[] Employees = employeeList.stream()
//                .filter(Employee::isMale)
//                .toArray();
//        for (Object o : Employees) {
//            System.out.println("o is " + o);
//        }


        //返回同类型数组
//        List<Employee> employeeList = Employee.persons();
//        Employee[] Employees = employeeList.stream()
//                .filter(Employee::isMale)
//                .toArray(n -> {
//                    System.out.println("length of List is " + n);
//                    Employee[] array = new Employee[n++]; //因为上面过滤删除了一些元素，这里的才可用自增
//                    return array;
//                });
//        for (Employee e : Employees) {
//            System.out.println("e is " + e);
//        }


        //返回任意类型数组
        List<Employee> employeeList = Employee.persons();
        Employee.Gender[] genders = employeeList.stream()
                .map(Employee::getGender)
                .<Employee.Gender>toArray(n -> {
                    System.out.println("length of List is " + n);
                    Employee.Gender[] array = new Employee.Gender[n];
                    return array;
                });
        for (Employee.Gender g : genders) {
            System.out.println(g);
        }

    }

//    private void collect() {
//        List<String> names = Employee.persons()
//                .stream()
//                .map(Employee::getName)
//                .collect(ArrayList::new,  ArrayList::addr, ArrayList::addAll);
//        System.out.println(names);
//    }

    private void collect() {

        List<String> names = Employee.persons()
                .stream()
                .map(Employee::getName)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(names);

    }

    private void reduceParallel() {

//        double sum = Employee
//                .persons()
//                .stream()
//                .reduce(
//                        0.0,
//                        (Double partialSum, Employee p) -> {
//                            double accumulated = partialSum + p.getIncome();
//                            System.out.println(Thread.currentThread().getName()
//                                    + "  - Accumulator: partialSum  = " + partialSum
//                                    + ",  person = " + p + ", accumulated = " + accumulated);
//                            return accumulated;
//                        },
//                        (a, b) -> {
//                            double combined = a + b;
//                            System.out.println(Thread.currentThread().getName()
//                                    + "  - Combiner:  a  = " + a + ", b  = " + b
//                                    + ", combined  = " + combined);
//                            return combined;
//                        });
//        System.out.println("--------------------------------------");
//        System.out.println(sum);
//
        double sum1 = Employee
                .persons()
                .parallelStream()
                .reduce(
                        0.0,
                        (Double partialSum, Employee p) -> {
                            double accumulated = partialSum + p.getIncome();
                            System.out.println(Thread.currentThread().getName()
                                    + "  - Accumulator: partialSum  = " + partialSum
                                    + ",  person = " + p + ", accumulated = " + accumulated);
                            return accumulated;
                        },
                        (a, b) -> {
//                            System.out.println();
                            double combined = a + b;
                            System.out.println(Thread.currentThread().getName()
                                    + "  - Combiner:  a  = " + a + ", b  = " + b
                                    + ", combined  = " + combined);
                            return combined;
                        });
        System.out.println(sum1);

    }


    private void reduce() {
        List<Integer> numbers = Arrays.asList(13, 2, 3, 4, 5);
        int sum = numbers.stream()
                .reduce(0, (u, g) -> {
                    System.out.println("u is " + u);
                    System.out.println("g is " + g);
                    return u + g;
                }, (u, g) -> {
                    System.out.println("first is " + u);
                    System.out.println("second is " + g);
                    return 0;
                });
        System.out.println(sum);


    }


    private void limit() {
        List<String> list = Arrays.asList("a4", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.limit(2l).forEach(System.out::println);
    }


    private void skip() {
        List<String> list = Arrays.asList("a4", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.skip(1l).forEach(System.out::println);
    }


    public void distinct() {

        List<String> list = Arrays.asList("a4", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.distinct().forEach(System.out::println);

    }


    public void filter() {

        List<String> list = Arrays.asList("a4", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.filter(s -> {
            System.out.println("filter: " + s);
            if (s == "a2") {
                return false;
            }
            return true;
        }).forEach(System.out::println);

    }


    public void sorted() {

        List<String> list = Arrays.asList("a4", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.sorted().forEach(System.out::println);

    }


    public void unordered() {

        List<String> list = Arrays.asList("a1", "a2", "a3", "a3");
        Stream stream = list.stream();
        stream.unordered().spliterator().forEachRemaining(System.out::println);

    }


    public void map() {

        List<String> list = Arrays.asList("a1", "a2", "a3", "a3");

        Stream stream = list.stream();
        stream.map(n -> "String is " + n)
                .forEach(System.out::println);

//        Stream stream = list.stream();
//        stream.map( n -> new Person((String)n))
//                .forEach(System.out::println);


    }


    public void flatMap() {

        List<String> line = Arrays.asList(
                "I am a boy -",
                "I love you -",
                "I want to be a scientist -"
        );

        line.stream()
                .flatMap(string -> Stream.of(string.split(" +"))
                        .filter(s -> !s.equals("-")))
                .forEach(r -> System.out.println(r));

    }


    public void peek() {

        Stream s = Stream.of("one", "two", "three", "four");
        s.peek(e -> System.out.println("Filtered value: " + e))
                .forEach(System.out::println);

    }


    private void chain() {

        Map<Integer, List<String>> names = Employee.persons()
                .stream()
                .collect(Collectors.mapping(Employee::getName,
                        Collectors.groupingBy(Object::hashCode,
                                Collectors.toList())));
        System.out.println(names.keySet());


    }


}