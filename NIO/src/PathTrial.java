import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2018/9/4 9:27.
 */

public class PathTrial {
    public static void main(String[] args) {
        PathTrial pathsTrial = new PathTrial();
        pathsTrial.base();
//        pathsTrial.compare();
//        pathsTrial.relativize();
    }

    private void relativize() {

        Path path = Paths.get("aa/bb/cc");
        System.out.println(path.relativize(Paths.get("aa/")));

    }

    private void base() {

        Path path = Paths.get("/aa/bb");

        System.out.println(path.toUri());



//        System.out.println(path.resolve("tt"));



//        System.out.println(path.resolve("/"));


//        System.out.println(path.toAbsolutePath());





//        System.out.println(path.subpath(0,1));
//        System.out.println(path.subpath(1,2));
//        System.out.println(path.subpath(2,3));
//        System.out.println(path.subpath(0,3));


//        System.out.println(path.startsWith("/aa"));
//        System.out.println(path.startsWith("/aa/bb"));
//        System.out.println(path.startsWith("/aa/b"));


//        System.out.println(path.getRoot());

//        System.out.println(path.getParent());

//        for (int i = 0; i < path.getNameCount(); i++) {
//            System.out.println(path.getName(i));
//
//        }

//        System.out.println(path.getFileName());

    }

    private void compare() {
        Path path1 = Paths.get("/aa/bb/cd");
        Path path2 = Paths.get("/aa/bb/cc");

        int r = path1.compareTo(path2);
        System.out.println(r);
    }


}
