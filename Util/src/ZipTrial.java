import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static java.util.zip.ZipOutputStream.DEFLATED;

/**
 * Created by Administrator on 2021/1/21 17:05.
 */
public class ZipTrial {

    public static void main(String[] args) {

        ZipTrial zipTrial = new ZipTrial();

//        zipOutputStreamTrial.zip();//压缩
//        zipOutputStreamTrial.unzip();//解压
        zipTrial.unzipWithZipFile();//使用ZipFile解压


    }

    private void unzipWithZipFile() {


        try {
            ZipFile zipFile = new ZipFile("Util/res/a.zip");//创建ZIP文件
            System.out.println("getName() is " + zipFile.getName());//获取ZIP文件名
            System.out.println("size() is " + zipFile.size());//获取ZIP文件中包含的目标文件个数

            zipFile.stream().forEach(zipEntry -> {
                System.out.println(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                try (InputStream inputStream = zipFile.getInputStream(zipEntry);//导出目标文件的输出流
                     FileOutputStream fileOutputStream = new FileOutputStream("Util/res/" + zipEntry);
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

                    while ((length = inputStream.read(bytes)) != -1) {//读取解压数据
                        bufferedOutputStream.write(bytes, 0, length);//保存解压数据
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void unzip() {

        try (FileInputStream fileInputStream = new FileInputStream("Util/res/a.zip");
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {

            ZipEntry zipEntry = null;

            //遍历压缩
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {//获取目标

                System.out.println("zipEntry = " + zipEntry);

                try (FileOutputStream fileOutputStream = new FileOutputStream("Util/res/" + zipEntry);//导出目标输出流
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(bytes)) != -1) {//读取解压数据
                        bufferedOutputStream.write(bytes, 0, length);//保存解压数据
                    }
                }
                zipInputStream.closeEntry();//关闭当前目标，但不关闭底层ZipInputStream
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void zip() {

        File[] files = new File("Util/res/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("pathname = " + pathname);
                return pathname.getName().startsWith("zip_"); //筛选文件
            }
        });
        System.out.println(Arrays.asList(files));


        try (FileOutputStream fileOutputStream = new FileOutputStream("Util/res/a.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            zipOutputStream.setMethod(DEFLATED);//操作类型为压缩
            zipOutputStream.setLevel(9);//设置压缩级别，值越大压缩率越高，耗时越长

            //遍历压缩
            for (File file : Arrays.asList(files)) {
                System.out.println("file = " + file);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName() + "xxx"));//创建目标
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                int length;
                byte[] bytes = new byte[1024];
                while ((length = bufferedInputStream.read(bytes)) != -1) {//读取原生数据
                    zipOutputStream.write(bytes, 0, length);//压缩
                }
                zipOutputStream.closeEntry();//关闭目标但不关闭底层ZipOutputStream
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
