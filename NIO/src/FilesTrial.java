import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * Created by Administrator on 2018/9/4 9:43.
 */

public class FilesTrial {
    public static void main(String[] args) {
        FilesTrial filesTrial = new FilesTrial();

//        filesTrial.copy();
//        filesTrial.create();
        filesTrial.move();
//        filesTrial.move();
    }

    private void create() {
        try {
            Files.createFile(Paths.get("NIO/res/tt"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy() {
        try {

            InputStream in = new FileInputStream("NIO/res/flipData");
            Files.copy(in, Paths.get("NIO/res/cc"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void move() {

        try {
            Files.move(Paths.get("NIO/res/cc"), Paths.get("NIO/res/gg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
