import java.io.*;
import java.nio.Buffer;
import java.sql.Array;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.zip.ZipOutputStream.DEFLATED;

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
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            zipOutputStream.setMethod(DEFLATED);
            for (File file : Arrays.asList(files)) {
                System.out.println("file = " + file);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                byte[] bytes = new byte[1024];
                while (bufferedInputStream.read(bytes) != -1) {
                    zipOutputStream.write(bytes);
                }
                zipOutputStream.closeEntry();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        }

    }
