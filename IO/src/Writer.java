import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/9/24.
 */
public class Writer {
    public static void main(String[] args) {
        Writer Writer = new Writer();
        Writer.append();
//        Writer.fileWriter();
    }

    private void append() {
        try {



            OutputStream outputStream = new FileOutputStream("IO/res/a", false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.append("aaaaaa");
            bufferedWriter.append("bbbbb");
            bufferedWriter.close();



//            FileWriter fw = new FileWriter("IO/res/a", true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter out =  new PrintWriter(bw);
//            out.println("the text");
//            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileWriter() {

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
