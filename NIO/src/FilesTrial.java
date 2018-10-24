import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

/**
 * Created by Administrator on 2018/9/24.
 */
public class FilesTrial {
    public static void main(String[] args) {
        FilesTrial filesTrial = new FilesTrial();


//        filesTrial.createFile();


//        filesTrial.SeekableByteChannel();

//        filesTrial.writeBytes();
//        filesTrial.writeLines();
//        filesTrial.writeBuffer();

//        filesTrial.readBytes();
//        filesTrial.readLines();
//        filesTrial.readBuffer();
//        filesTrial.readStream();


//        filesTrial.createDir();
//        filesTrial.walk();
//        filesTrial.tree();
//        filesTrial.directoryStream();


//        filesTrial.find();
//        filesTrial.list();
//        filesTrial.move(); //empty
//        filesTrial.copy();
//        filesTrial.copyDir();


        filesTrial.states();
//        filesTrial.attrs();
//        filesTrial.attrsPosix();

        //不同平台下，获取属性视图和属性集
//        filesTrial.basicOS();  //通用文件系统
//        filesTrial.posixOS(); // posix文件系统


    }


    private void SeekableByteChannel() {

        try {

            //读一个字节
//            Path path = Paths.get("NIO/res/tt.txt");
//            SeekableByteChannel seekableByteChannel =
//                    Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.READ);
//            seekableByteChannel.position(0);
//            ByteBuffer buffer = ByteBuffer.allocate(3);
//            seekableByteChannel.read(buffer);
//            buffer.flip();
//            System.out.println(buffer.get());


            //读所有字节
//            Path path = Paths.get("NIO/res/tt.txt");
//            SeekableByteChannel seekableByteChannel =
//                    Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.READ);
//            ByteBuffer buffer = ByteBuffer.allocate(3);
//            while (seekableByteChannel.read(buffer) != -1) {
//                System.out.println("file'posistion is " + seekableByteChannel.position());
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    System.out.println(buffer.get());
//                }
//                buffer.clear();
//            }


            //截断文件内容
//            Path path = Paths.get("NIO/res/tt.txt");
//            SeekableByteChannel seekableByteChannel =
//                    Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
//            System.out.println(seekableByteChannel.size());
//            seekableByteChannel.position(2).truncate(3);
//
//            System.out.println(seekableByteChannel.position());
//            System.out.println(seekableByteChannel.size());


            //写文件
            Path path = Paths.get("NIO/res/tt.txt");
            SeekableByteChannel seekableByteChannel =
                    Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(10);

            buffer.putChar('a');
            buffer.putChar('b');
            buffer.putChar('c');
            buffer.rewind();
            seekableByteChannel.write(buffer);


        } catch (IOException e) {
            e.printStackTrace();
        }
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


    private void tree() {
        Path start = Paths.get("d:/oo");
        try {

            Stream<Path> pathStream = Files.walk(start, 1);
            pathStream.forEach(path -> System.out.println(path));

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
            //方法一
//            Files.walkFileTree(start, fileVisitor);

            //方法二
            Files.walkFileTree(start,
                    EnumSet.noneOf(FileVisitOption.class), 3,
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            System.out.println("dir is " + dir);
                            System.out.println("dir'size is " + attrs.size());
                            return super.preVisitDirectory(dir, attrs);
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            System.out.println("file is " + file);
                            System.out.println("size is " + attrs.size());


                            return FileVisitResult.CONTINUE;
                        }
                    });

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


            Path path = Paths.get("d:/oo/tt.txt");
            Files.createDirectories(path);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void states() {

//        Path path = Paths.get("d://tt.txt.lnk"); //软连接


//        boolean b = Files.exists(path);
//        System.out.println("is exist: " +b);



        try {

            Path path = Paths.get("NIO/res/tt.txt");
            String type = Files.probeContentType(path);
            System.out.println(type);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void attrs() {
        Path path = Paths.get("NIO/res/", "sql.log");
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

            Path srcPath = Paths.get("D:/tt.txt.lnk");
            Path tgtPath = Paths.get("D:/oo/", srcPath.getFileName().toString());
            Path path = Files.copy(srcPath, tgtPath, NOFOLLOW_LINKS);
            System.out.println(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void find() {

        try {

            Path startPath = Paths.get("NIO/res/");
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


    private void readBuffer() {


        try {
            Path path = Paths.get("NIO/res/", "sql.log");
            System.out.println("path is " + path);

            BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);


            StringBuilder stringBuilder = new StringBuilder(128);
            CharBuffer buffer = CharBuffer.allocate(1024);
            while (bufferedReader.read(buffer) != -1) {

                buffer.flip();
                while (buffer.hasRemaining()) {
                    stringBuilder.append(buffer.get());
                }
                buffer.clear();
            }

            bufferedReader.close();

            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//
//            Path path = Paths.get("NIO/res/", "sql.log");
//            Charset charset = Charset.forName("UTF-8");
//            BufferedReader bufferedReader = Files.newBufferedReader(path, charset);
//            StringBuilder stringBuilder = new StringBuilder(1024);
//            String r;
//            while ((r = bufferedReader.readLine()) != null){
//                stringBuilder.append(r + "\n");
//            }
//            bufferedReader.close();
//            System.out.println(stringBuilder.toString());
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    private void readStream() {

        Path path = Paths.get("NIO/res/", "stream");
        Charset charset = Charset.forName("UTF-8");


        try {
            Stream<String> stringStream = Files.lines(path, charset);
            stringStream.forEach(s -> System.out.println(s));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void readLines() {
        Path path = Paths.get("NIO/res/", "sql.log");
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
        Path path = Paths.get("NIO/res/", "sql.log");

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

        try {

            Path path = Paths.get("NIO/res/", "sql.log");

            ArrayList<String> lines = new ArrayList<>();
            String sql = "SELECT * FROM Car;";
            lines.add(sql);
            lines.add(sql);
            Charset charset = Charset.forName("UTF-8");
            Files.write(path, lines, charset,
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeBytes() {
        Path path = Paths.get("NIO/res/", "sql.log");
        String sql = "UPDATE Car SET name = 'chris' WHERE id = 1;\n";
        try {
            path = Files.write(path, sql.getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBuffer() {
        try {

            Path path = Paths.get("d:\\oo\\ak");
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);


            bufferedWriter.write("aaaaaaaa");
            bufferedWriter.newLine();
            bufferedWriter.append("bbbbbb")
                    .append("cccccc")
                    .append("ddddd");

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
