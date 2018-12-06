/**
 * Created by Administrator on 2018/12/6 15:08.
 */

public class KeywordTrial {
    public static void main(String[] args) {
        KeywordTrial keywordTrial = new KeywordTrial();
        KeywordTrial.finalTest();
    }

    private static void finalTest() {

        final String k = "asdf";
        System.out.println(k);
//        k = "asdf";
        System.out.println(k);

    }
}
