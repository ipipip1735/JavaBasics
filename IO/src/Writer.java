import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2018/9/24.
 */
public class Writer {
    public static void main(String[] args) {
        Writer Writer = new Writer();
        Writer.base();
    }

    private static void base() {

        try {
            FileWriter fw = new FileWriter("IO/res/a", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out =  new PrintWriter(bw);
            out.println("the text");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
