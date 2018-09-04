import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;

/**
 * Created by Administrator on 2018/9/4 9:43.
 */

public class FileSystemTrial {
    public static void main(String[] args) {
        FileSystemTrial fileSystemTrial = new FileSystemTrial();
        FileSystemTrial.base();
    }

    private static void base() {
        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println(fileSystem);

//        System.out.println("Separator is " + fileSystem.getSeparator());



//         Path path = fileSystem.getPath("aa", "bb", "cc");
//        System.out.println(path);


//        Iterable<Path> iterable = fileSystem.getRootDirectories();
//        iterable.forEach(path -> System.out.println(path));


//        Set<String> set =  fileSystem.supportedFileAttributeViews();
//        for (String s : set) {
//            System.out.println(s);
//        }


//        getFileStores()

//        fileSystem.

    }
}
