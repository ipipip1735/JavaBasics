public class StringTrial {
    public static void main(String[] args) {
        StringTrial stringTrial = new StringTrial();
        stringTrial.appendBool();
        stringTrial.appendChars();
        stringTrial.split();
        stringTrial.compare();
    }


    private static void compare() {
        String s1 = "a1";
        String s2 = "A2";
        System.out.println(s2.compareTo(s1));
    }

    private static void split() {
        String[] strings = null;
        String s = "a/b/c/d";
        strings = s.split("/");
        for (String v :
                strings) {
            System.out.println(v);
        }
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
