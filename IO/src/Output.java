import java.io.*;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Output {

    public static void main(String[] args) {

        Output out = new Output();

        out.outputStream();
    }

    public void outputStream() {

        //输出到文件
//        File file = new File("IO/res/a");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
//            byte[] bytes = new byte[1024 * 1024];
//            Arrays.fill(bytes, (byte) 96);
//            fileOutputStream.write(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //输出到标准输出流
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
//            byte[] bytes = new byte[1024];
//            Arrays.fill(bytes, (byte) 99);
//            fileOutputStream.write(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //输出到标准输出流
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.err);
            byte[] bytes = new byte[1024];
            Arrays.fill(bytes, (byte) 99);
            fileOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
