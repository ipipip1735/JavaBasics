import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

/**
 * Created by Administrator on 2018/9/24.
 */
public class FilesTrial {
    public static void main(String[] args) {
        FilesTrial filesTrial = new FilesTrial();


//        filesTrial.createFile();

//        filesTrial.writeBytes();
//        filesTrial.writeLines();

//        filesTrial.readBytes();
//        filesTrial.readLines();
//        filesTrial.readStream();


//        filesTrial.createDir();
//        filesTrial.walk();
        filesTrial.directoryStream();


//        filesTrial.find();
//        filesTrial.list();
//        filesTrial.move(); //empty
//        filesTrial.copy();
//        filesTrial.copyDir();


//        filesTrial.states();
//        filesTrial.attrs();
//        filesTrial.attrsPosix();

        //不同平台下，获取属性视图和属性集
//        filesTrial.basicOS();  //通用文件系统
//        filesTrial.posixOS(); // posix文件系统



    }

    private void directoryStream() {

        try {
//            Path dir = Paths.get("D:\\oo");
//            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, p -> {
//                if (p.getFileName().endsWith("kd")) return false;
//                return true;
//            });
//
//
//            directoryStream.forEach(path -> {
//                System.out.println(path);
//            });


            Path dir = Paths.get("D:\\oo");
            DirectoryStream<Path> directoryStream =
                    Files.newDirectoryStream(dir, "{aa,bb}.txt");


            directoryStream.forEach(path -> {
                System.out.println(path);
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void walk() {
        Path start = Paths.get("d:/oo");

        FileVisitor fileVisitor = new FileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("~~preVisitDirectory~~");
                System.out.println("dir is " + dir);
                if (dir.compareTo(Paths.get("d:\\oo\\kc")) == 0) {
                    return FileVisitResult.SKIP_SIBLINGS;
                } else
                    return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("~~visitFile~~");
                System.out.println("file is " + file);
//                    if (file.compareTo(Paths.get("d:\\oo\\kc")) == 0) {
//                    System.out.println("*************");
//                    return FileVisitResult.SKIP_SIBLINGS;
//                } else
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("~~visitFileFailed~~");
                System.out.println("file is " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("~~postVisitDirectory~~");
                System.out.println("dir is " + dir);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(start, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createDir() {
        try {


//            Path path = Paths.get("d:/oo");
//            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
//            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
//            Files.createDirectory(path, attr);


            Path path = Paths.get("d:/oo/tt");
            Files.createDirectories(path);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void states() {

        Path path = Paths.get("d://tt.lnk");
        boolean b = Files.exists(path);
//            Path path = Files.copy(srcPath, tgtPath, NOFOLLOW_LINKS);
        System.out.println(b);

    }


    private void attrs() {
        Path path = Paths.get("File/res/", "sql.log");
        Charset charset = Charset.forName("UTF-8");

        try {
            long size = Files.size(path);
            System.out.println("size is " + size + " bytes");


            //获取UID
            UserPrincipal userPrincipal = Files.getOwner(path);
            System.out.println("user is " + userPrincipal.getName());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void attrsPosix() {
        try {

            Path path = Paths.get("d:/aa.txt");
            UserPrincipalLookupService lookupService =
                    FileSystems.getDefault().getUserPrincipalLookupService();
            UserPrincipal userPrincipal = lookupService.lookupPrincipalByName("root");
            Files.setOwner(path, userPrincipal);


            //获取GID
//            PosixFileAttributes posixFileAttributes =
//                    Files.readAttributes(path, PosixFileAttributes.class);
//            GroupPrincipal groupPrincipal = posixFileAttributes.group();
//            System.out.println(groupPrincipal.getName());

            //设置GID
//            GroupPrincipal groupPrincipal = lookupService.lookupPrincipalByGroupName("root");
//            PosixFileAttributeView posixFileAttributeView =
//                    Files.getFileAttributeView(path, PosixFileAttributeView.class);
//            posixFileAttributeView.setGroup(groupPrincipal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void posixOS() {

//        try {
//        //先获取View，通过View获取Attribute
//        Path path = Paths.get("d:/aa.txt");
//        PosixFileAttributeView posixFileAttributeView =
//                Files.getFileAttributeView(path, PosixFileAttributeView.class);
//        System.out.println("name is " + posixFileAttributeView.name());
//            PosixFileAttributes posixFileAttributes = posixFileAttributeView.readAttributes();
//
//            System.out.println("creationTime is " + posixFileAttributes.creationTime());
//            System.out.println("fileKey is " + posixFileAttributes.fileKey());
//            System.out.println("isDirectory is " + posixFileAttributes.isDirectory());
//            System.out.println("isOther is " + posixFileAttributes.isOther());
//            System.out.println("isRegularFile is " + posixFileAttributes.isRegularFile());
//            System.out.println("isSymbolicLink is " + posixFileAttributes.isSymbolicLink());
//            System.out.println("lastAccessTime is " + posixFileAttributes.lastAccessTime());
//            System.out.println("lastModifiedTime is " + posixFileAttributes.lastModifiedTime());
//            System.out.println("size is " + posixFileAttributes.size());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


        try {
            Path path = Paths.get("d:/aa.txt");
            PosixFileAttributes posixFileAttributes =
                    Files.readAttributes(path, PosixFileAttributes.class);

            //从父类BasicFileAttributes接口继承
            System.out.println("creationTime is " + posixFileAttributes.creationTime());
            System.out.println("fileKey is " + posixFileAttributes.fileKey());
            System.out.println("isDirectory is " + posixFileAttributes.isDirectory());
            System.out.println("isOther is " + posixFileAttributes.isOther());
            System.out.println("isRegularFile is " + posixFileAttributes.isRegularFile());
            System.out.println("isSymbolicLink is " + posixFileAttributes.isSymbolicLink());
            System.out.println("lastAccessTime is " + posixFileAttributes.lastAccessTime());
            System.out.println("lastModifiedTime is " + posixFileAttributes.lastModifiedTime());
            System.out.println("size is " + posixFileAttributes.size());

            //PosixFileAttribute接口独有
            System.out.println("group is " + posixFileAttributes.group());
            System.out.println("owner is " + posixFileAttributes.owner());
            System.out.println("permissions is " + posixFileAttributes.permissions());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void basicOS() {

        //先获取View，通过View获取Attribute
//        Path path = Paths.get("d:/aa.txt");
//        BasicFileAttributeView basicFileAttributeView =
//                Files.getFileAttributeView(path, BasicFileAttributeView.class);
//        System.out.println("name is " + basicFileAttributeView.name());
//        try {
//            BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
//            System.out.println("creationTime is " + basicFileAttributes.creationTime());
//            System.out.println("fileKey is " + basicFileAttributes.fileKey());
//            System.out.println("isDirectory is " + basicFileAttributes.isDirectory());
//            System.out.println("isOther is " + basicFileAttributes.isOther());
//            System.out.println("isRegularFile is " + basicFileAttributes.isRegularFile());
//            System.out.println("isSymbolicLink is " + basicFileAttributes.isSymbolicLink());
//            System.out.println("lastAccessTime is " + basicFileAttributes.lastAccessTime());
//            System.out.println("lastModifiedTime is " + basicFileAttributes.lastModifiedTime());
//            System.out.println("size is " + basicFileAttributes.size());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //直接获取Attribute
        try {
            Path path = Paths.get("d:/aa.txt");
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("creationTime is " + basicFileAttributes.creationTime());
            System.out.println("fileKey is " + basicFileAttributes.fileKey());
            System.out.println("isDirectory is " + basicFileAttributes.isDirectory());
            System.out.println("isOther is " + basicFileAttributes.isOther());
            System.out.println("isRegularFile is " + basicFileAttributes.isRegularFile());
            System.out.println("isSymbolicLink is " + basicFileAttributes.isSymbolicLink());
            System.out.println("lastAccessTime is " + basicFileAttributes.lastAccessTime());
            System.out.println("lastModifiedTime is " + basicFileAttributes.lastModifiedTime());
            System.out.println("size is " + basicFileAttributes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void move() {

    }


    private void list() {
        try {

            Path path = Paths.get("AppTemp/res");
            Stream<Path> stringStream = Files.list(path);
            stringStream.forEach(file -> {
                System.out.println(file);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void copyDir() {

    }

    private void copy() {
        try {

            Path srcPath = Paths.get("D:/tt.lnk");
            Path tgtPath = Paths.get("D:/oo/", srcPath.getFileName().toString());
            Path path = Files.copy(srcPath, tgtPath, NOFOLLOW_LINKS);
            System.out.println(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void find() {

        try {

            Path startPath = Paths.get("File/res/");
            Charset charset = Charset.forName("UTF-8");
            Stream<Path> pathStream = Files.find(startPath, 2,
                    (path, attrs) -> {
                        System.out.println("~~find~~");
                        System.out.println(path);
                        System.out.println(attrs.isDirectory());
                        return false;
                    });
            pathStream.forEach(resultPath -> {
                System.out.println("~result~");
                System.out.println(resultPath);
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void createFile() {
        try {
            Path path = Paths.get("d:/aa");

            //创建权限
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");

            //封装权限
            FileAttribute<Set<PosixFilePermission>> attr =
                    PosixFilePermissions.asFileAttribute(perms);

            Files.createFile(path, attr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readStream() {

        Path path = Paths.get("File/res/", "stream");
        Charset charset = Charset.forName("UTF-8");


        try {
            Stream<String> stringStream = Files.lines(path, charset);
            stringStream.forEach(s -> System.out.println(s));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void readLines() {
        Path path = Paths.get("File/res/", "sql.log");
        Charset charset = Charset.forName("UTF-8");

        try {
            List<String> list = Files.readAllLines(path, charset);
            for (String s : list) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void readBytes() {
        Path path = Paths.get("File/res/", "sql.log");

        try {
            byte[] bytes = Files.readAllBytes(path);
            System.out.println("size is " + bytes.length);
            String s = new String(bytes, "utf-8");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLines() {
        Path path = Paths.get("File/res/", "sql.log");

        ArrayList<String> lines = new ArrayList<>();
        String sql = "SELECT * FROM Car;";
        lines.add(sql);
        lines.add(sql);
        Charset charset = Charset.forName("UTF-8");

        try {
            Files.write(path, lines, charset,
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeBytes() {
        Path path = Paths.get("File/res/", "sql.log");
        String sql = "SELECT * FROM Car;\n";
        try {
            Files.write(path, sql.getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
