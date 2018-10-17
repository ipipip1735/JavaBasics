import java.io.*;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Output {

    public static void main(String[] args) {

        Output out = new Output();

        out.outputStream();
//        out.outputStreamWriter();
//        out.fileWriter();
//        out.BufferedWriter();
    }

    private void BufferedWriter() {
        File file = new File("IO/res/a");

        //JAVA 7 新特性
        try (OutputStream outputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

            String s = "cc";
            bufferedWriter.write(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void fileWriter() {
        File file = new File("IO/res/a");
        try {
            FileWriter fileWriter = new FileWriter(file);
            String s = "cc";
            fileWriter.write(s);
//            fileWriter.flush();  //close()会调用flush()
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void outputStreamWriter() {
        File file = new File("IO/res/a");
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            String s = "cc";
            outputStreamWriter.write(s);
//            outputStreamWriter.flush();  //close()会调用flush()

            outputStreamWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void outputStream() {

        File file = new File("IO/res/a");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            byte[] bytes = new byte[1024 * 1024];
            Arrays.fill(bytes, (byte) 96);
            fileOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
