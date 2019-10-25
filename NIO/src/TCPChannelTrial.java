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

public class TCPChannelTrial {
    public static void main(String[] args) {
        TCPChannelTrial tcpChannelTrial = new TCPChannelTrial();


        tcpChannelTrial.selector();


    }

    private void selector() {

        try {

            Selector selector = Selector.open();

//            InetAddress inetAddress = InetAddress.getLoopbackAddress();//回环地址
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");
            ServerSocketChannel serverSocket = ServerSocketChannel.open();


            serverSocket.bind(new InetSocketAddress(inetAddress, 5454));
            serverSocket.configureBlocking(false);
            SelectionKey selectionKey = serverSocket.register(selector, 0);
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);


            List<String> list = Arrays.asList("aa", "aa", "aa");
            selectionKey.attach(list);



            while (true) {
                System.out.println("-----start------");

                selector.select();//调用selecot方法，查询底层操作系统，看内核buffer是否有数据

                for (SelectionKey key : selector.selectedKeys()) {

                    System.out.println("threadID is " + Thread.currentThread());


                    //处理accept事件
                    if (key.isAcceptable()) {
                        System.out.println("*******isAcceptable()********");
                        list = (List<String>) key.attachment();
                        System.out.println(list);


                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();//获取客户端socket
                        System.out.println(socketChannel);
                        socketChannel.configureBlocking(false);
                        System.out.println(socketChannel.getRemoteAddress());
                        socketChannel.register(selector, SelectionKey.OP_READ);



                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");

                    }

                    //处理read事件
                    if (key.isReadable()) {
                        System.out.println("*******isReadable()********");
                        System.out.println("Readable'interest is " + key.interestOps());
                        System.out.println("Readable'ready is " + key.readyOps());


                        int n = 16;
                        byte[] bytes = new byte[1024 * n];
                        Arrays.fill(bytes, (byte) 97);


                        for (int i = 0; i < n; i++) {

                            bytes[1024 * i] = (byte) (100 + i);

                        }

                        bytes[bytes.length - 1] = 98;

                        SocketChannel socket = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
                        socket.read(byteBuffer);

                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            System.out.println(byteBuffer.get()); //读取客户端数据
                        }


                        //回复1K
//                        byteBuffer.clear();
//                        byteBuffer.put(bytes);
//                        byteBuffer.rewind();


                        //回复ok
//                        byteBuffer.clear();
//                        byteBuffer.put("ok".getBytes());
//                        byteBuffer.rewind();
//                        byteBuffer.limit("ok".getBytes().length);


                        socket.write(byteBuffer); //发送数据到客户端

                    }

                    selector.selectedKeys().remove(key);


                }


                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
