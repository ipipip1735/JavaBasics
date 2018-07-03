import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public class AsynchronousFileChannelTrial {

    public static void main(String[] args) {

        AsynchronousFileChannelTrial asynchronousFileChannelTrial = new AsynchronousFileChannelTrial();
        asynchronousFileChannelTrial.read();
    }

    private void read() {


        //使用Future
        try {
            Path path = Paths.get("res/a");
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(255);
            Future<Integer> operation = fileChannel.read(buffer, 0);
            while (!operation.isDone()) {
                System.out.println("not ok!");
            }

            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            System.out.println(buffer.limit());
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }




        //使用CompletionHandler
//        try {
//            Path path = Paths.get("res/a");
//            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
//            ByteBuffer buffer = ByteBuffer.allocate(5);
//
//            fileChannel.read(buffer, 0, buffer, new FileHandler());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}



class FileHandler implements CompletionHandler<Integer, ByteBuffer>{

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println("***** completed() *****");
        System.out.println(result.intValue());

        attachment.flip();
        byte[] data = new byte[attachment.limit()];
        attachment.get(data);
        System.out.println(new String(data));
        attachment.clear();
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("***** failed() *****");
        System.out.println(exc.getMessage());
    }
}