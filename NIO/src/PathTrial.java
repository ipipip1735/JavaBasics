import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by Administrator on 2018/9/4 9:27.
 */

public class PathTrial {
    public static void main(String[] args) {
        PathTrial pathsTrial = new PathTrial();
//        pathsTrial.base();
//        pathsTrial.compare();
//        pathsTrial.relativize();

//        pathsTrial.registerMultiple();
        pathsTrial.registerSingle();

    }

    private void registerSingle() {

        try {
            Path path = Paths.get("d:\\oo");
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

            Thread.sleep(5000);

            for (; ; ) {
                WatchKey key = watchService.take();

                List<WatchEvent<?>> list = key.pollEvents();
                System.out.println("event'count is " + list.size());

                for (WatchEvent<?> event : list) {
                    System.out.println("event is " + event.kind().name());
                    System.out.println("count is " + event.count());
                    System.out.println("file is " +
                            path.resolve((Path) event.context()));
                }

                boolean valid = key.reset();
                System.out.println("key is " + valid);
                if (!valid) {
                    System.out.println("key is " + valid);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void registerMultiple() {



        try {

            WatchService watchService = FileSystems.getDefault().newWatchService();


            Map<WatchKey, Path> keyMap = new HashMap<WatchKey, Path>();
            Path path;
            WatchKey key;

            path = Paths.get("d:\\oo");
            key = path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);
            keyMap.put(key, path);

            path = Paths.get("d:\\tt.txt");
            key = path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);
            keyMap.put(key, path);


            while (true) {
                key = watchService.take();
                path = keyMap.get(key);


                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("event is " + event.kind().name());
                    System.out.println("file is " + path.resolve((Path) event.context()));
                }

                boolean valid = key.reset();
                if(!valid) {
                    keyMap.remove(key);
//                    if(keyMap.isEmpty()) break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void relativize() {

        Path path = Paths.get("aa/bb/cc");
        System.out.println(path.relativize(Paths.get("aa/")));

    }

    private void base() {

        Path path = Paths.get("/aa/bb");

        System.out.println(path.toUri());


//        System.out.println(path.resolve("tt.txt"));


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
