import java.util.Arrays;

public class StringTrial {
    public static void main(String[] args) {
        StringTrial stringTrial = new StringTrial();
//        stringTrial.appendBool();
//        stringTrial.appendChars();
        stringTrial.join();
//        stringTrial.split();
//        stringTrial.compare();
//        stringTrial.equal();
//        stringTrial.base();



    }

    private void join() {
        String message = String.join("-", "Java", "is", "cool");
        System.out.println(message);
    }

    private void base() {
        System.out.println(" ss ".trim());

    }


    private void equal() {

        boolean b;
        b= ("sss" == "sss");
//        System.out.println("sss".hashCode());
//        System.out.println("stt".hashCode());

//        String s = "sss";
//        b = (s == "sss");
//
//        String sk = "oo";
//        String st = "oo";
//        System.out.println(sk.hashCode());
//        System.out.println(st.hashCode());
//        b = (sk == st);


//        String k = "sdf";
//        String t = "sdf";
//        System.out.println(t.hashCode());
//        System.out.println(k.hashCode());
//        b=k.equals(t);

        String k = new String("sdf");
        String t = new String("sdf");

        System.out.println(b);




    }


    private static void compare() {
        String s1 = "a1";
        String s2 = "A2";
        System.out.println(s2.compareTo(s1));
    }

    private static void split() {
//        String[] strings = null;
//        String s = "a/b/c/d";
//        strings = s.split("/");
//        for (String v :
//                strings) {
//            System.out.println(v);
//        }

        Arrays.stream("aa b1b       cc".split(" ")) //正则表达式没有+
                .forEach(System.out::println);

        Arrays.stream("aa bb       cc".split(" +"))
                .forEach(System.out::println);

        Arrays.stream("a1b2c3".split("[1-9]"))
                .forEach(System.out::println);

        Arrays.stream("a[3".split("\\["))  //匹配元字符时需要转义，使用\\
                .forEach(System.out::println);//输出 a 3






    }

    private static void appendChars() {
        StringBuilder stringBuilder = new StringBuilder("9876543210");
        char[] dst = new char[10];
        stringBuilder.getChars(1, 9, dst, 0);
        for (char c : dst) {
            System.out.println(c);
        }
    }

    private static void appendBool() {
        StringBuilder stringBuilder = new StringBuilder("9876543210");
        boolean b = true;
        stringBuilder.append(b);
        System.out.println(stringBuilder.toString());
    }
}
