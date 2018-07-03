package dir;

import java.io.File;
import java.nio.file.Files;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Dir {


    private File files = null;

    public static void main(String[] args) {

        Dir dir = new Dir("d:/kk/big");

//        dir.PrintFileName();
        dir.modifyFileName();
//        dir.changeFileName();





    }

    public Dir(String path) {
        this.files = new File(path);
    }

    public void modifyFileName() {

        int i = 17;
        for (File file : files.listFiles()) {

            String newPath = null;

            newPath = "d:/kk/big/" + (i++) + ".jpg";
            System.out.println(newPath);
            file.renameTo(new File(newPath));
        }

    }

    public void changeFileName() {

        int i = 1;

//        big1.jpg
        String[] newPath = null;
        newPath = files.list();

        String newFile = null;
//        for (String s : newPath) {
//            System.out.println(s);
//
//            s = s.replace("big", "");
//            System.out.println(s);
//            s = "d:/big/" + s;
//            System.out.println(s);


        for (File file : files.listFiles()) {


            newFile = file.getName();
            newFile = "d:/big/" + newFile.replace("big", "");
            System.out.println(newFile);

            file.renameTo(new File(newFile));

        }




    }

    public void PrintFileName() {

        String[] strings = null;
        strings = files.list();
        System.out.println(strings.length);

        for (String s : strings) {
            System.out.println(s);
        }

    }
}
