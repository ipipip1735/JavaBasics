import java.io.*;
import java.nio.Buffer;
import java.sql.Array;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2021/1/20 17:05.
 */
public class ZipOutputStreamTrial {

    public static void main(String[] args) {

        ZipOutputStreamTrial zipOutputStreamTrial = new ZipOutputStreamTrial();

        zipOutputStreamTrial.zip();
    }

    private void zip() {


        File[] files = new File("Util/res/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("pathname = " + pathname);
                return pathname.getName().startsWith("zip_");
            }
        });
        System.out.println(Arrays.asList(files));


        try (FileOutputStream fileOutputStream = new FileOutputStream("Util/res/a.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        ) {


            FileInputStream fileInputStream = new FileInputStream(files[0]);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            Buffer buffer
            while (bufferedInputStream.read() != -1) {

            }

            zipOutputStream.putNextEntry(new ZipEntry(files[0].getName()));

            bufferedOutputStream.write();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
