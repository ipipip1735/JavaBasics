import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.zip.ZipOutputStream.DEFLATED;

public class GZipTrail {

    public static void main(String[] args) {
        GZipTrail gZipTrail = new GZipTrail();

//        gZipTrail.gzip();//压缩
        gZipTrail.ungzip();//解压

    }

    private void ungzip() {

        File[] files = new File("Util/res/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("pathname = " + pathname);
                return pathname.getName().endsWith(".gz"); //筛选文件
            }
        });
        System.out.println(Arrays.asList(files));

        int length;
        byte[] bytes = new byte[1024];

        for (File file : files) {
            System.out.println("file = " + file);
            String outFile = file.getPath().substring(0, file.getPath().length()-3);
            System.out.println("outFile = " + outFile);

            try (FileInputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                 GZIPInputStream gzipInputStream = new GZIPInputStream(bufferedInputStream);
                 FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

                while ((length = gzipInputStream.read(bytes)) != -1) {//读取压缩数据
                    bufferedOutputStream.write(bytes, 0, length);//解压
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void gzip() {

        File[] files = new File("Util/res/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("pathname = " + pathname);
                return pathname.getName().startsWith("zip_"); //筛选文件
            }
        });
        System.out.println(Arrays.asList(files));

        int length;
        byte[] bytes = new byte[1024];

        for (File file : files) {
            System.out.println("file = " + file);
            String outFile = file.getPath() + ".gz";
            System.out.println("outFile = " + outFile);
            try (FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                 GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bufferedOutputStream);
                 FileInputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

                while ((length = bufferedInputStream.read(bytes)) != -1) {//读取原生数据
                    gzipOutputStream.write(bytes, 0, length);//压缩
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
