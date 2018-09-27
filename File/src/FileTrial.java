import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/9/4.
 */
public class FileTrial {
    public static void main(String[] args) {
        FileTrial fileTrial = new FileTrial();
        fileTrial.base();
//        fileTrial.dir();
//        fileTrial.scanSpace();


//        fileTrial.PrintFileName();
//        fileTrial.modifyFileName();
//        fileTrial.changeFileName();
    }

    private void base() {
        File file = new File("File/res/", "a.log");
        System.out.println(file);

        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write("slskdjf".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dir() {
//        File file = new File("D:/ki/gg/");
//        boolean b = file.mkdir();
//        System.out.println("b is " + b);


        File file = new File("D:/ki/gg/");
        boolean b = file.mkdirs();
        System.out.println("b is " + b);
    }

    private void scanSpace() {
        File file = new File("File/res/");
        long size;
        size = file.getFreeSpace();
        System.out.println("Free size is " + size/1024/1024 + "MB");
        size = file.getTotalSpace();
        System.out.println("Total size is " + size/1024/1024 + "MB");
    }



    public void modifyFileName() {
        File file = new File("d:/kk/big");

        int i = 17;
        for (File f : file.listFiles()) {

            String newPath = null;

            newPath = "d:/kk/big/" + (i++) + ".jpg";
            System.out.println(newPath);
            f.renameTo(new File(newPath));
        }

    }

    public void changeFileName() {
        File file = new File("d:/kk/big");

        int i = 1;


        String[] newPath = null;
        newPath = file.list();

        String newFile = null;
//        for (String s : newPath) {
//            System.out.println(s);
//
//            s = s.replace("big", "");
//            System.out.println(s);
//            s = "d:/big/" + s;
//            System.out.println(s);


        for (File f : file.listFiles()) {


            newFile = f.getName();
            newFile = "d:/big/" + newFile.replace("big", "");
            System.out.println(newFile);

            f.renameTo(new File(newFile));

        }




    }

    public void PrintFileName() {
        File file = new File("d:/kk/big");

        String[] strings = null;
        strings = file.list();
        System.out.println(strings.length);

        for (String s : strings) {
            System.out.println(s);
        }

    }
}
