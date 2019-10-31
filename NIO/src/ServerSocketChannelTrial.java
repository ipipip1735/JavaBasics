import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.List;

public class ServerSocketChannelTrial {
    String request = null;


    public static void main(String[] args) {
        ServerSocketChannelTrial tcpChannelTrial = new ServerSocketChannelTrial();


//        tcpChannelTrial.block();//阻塞I/O
        tcpChannelTrial.noBlock();//非阻塞I/O


    }

    private void block() {

        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//打开通道
            serverSocketChannel.bind(new InetSocketAddress(inetAddress, 5454));
            System.out.println(serverSocketChannel);
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel);


            //方式一：基于Buffer的读写
//            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//            while (socketChannel.read(buffer) != -1) {
//                buffer.flip();
//                socketChannel.write(buffer);
//                if (buffer.hasRemaining()) {
//                    buffer.compact();
//                } else {
//                    buffer.clear();
//                }
//            }
//            socketChannel.close();


            //方式二：基于Stream的读写
            try (InputStream in = socketChannel.socket().getInputStream();
                 OutputStream out = socketChannel.socket().getOutputStream()) {
                byte[] bytes = new byte[1024];
                while (in.read(bytes) != -1) {
                    System.out.println(new String(bytes));
                    out.write("ok".getBytes());
                }
                socketChannel.close();
            }


            serverSocketChannel.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void noBlock() {
//        selectorRead();
        selectorRW();
    }

    private void selectorRW() {


        try {
            Selector selector = Selector.open();//获取选择器
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//打开服务端通道


            serverSocketChannel.bind(new InetSocketAddress(inetAddress, 5454));//绑定Socket
            serverSocketChannel.configureBlocking(false);//设置服务端通道模式
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册服务端通道，并设置感兴趣的事件


            while (true) {
                System.out.println("-----start------");

                selector.select();//调用selecot方法，查询底层操作系统，看内核buffer是否有数据

                for (SelectionKey key : selector.selectedKeys()) {

                    System.out.println("threadID is " + Thread.currentThread());


                    //处理accept事件
                    if (key.isAcceptable()) {
                        System.out.println("*******isAcceptable()********");

                        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocket.accept();//获取客户端通道
                        System.out.println(socketChannel);
                        System.out.println(socketChannel.getRemoteAddress());
                        socketChannel.configureBlocking(false);//设置客户端通道模式
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);//设置感兴趣的事件

                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());

                        if (request != null) {
                            System.out.println("writing!");
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            request = "[servser]" + request;
                            ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());

                            socketChannel.write(byteBuffer);//写数据到客户端
                            socketChannel.shutdownOutput();
                            request = null;

                        }

                    }

                    //处理read事件
                    if (key.isReadable()) {
                        System.out.println("*******isReadable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());


                        int n = 1;
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * n);
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.read(byteBuffer);

                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            System.out.println(byteBuffer.get()); //读取客户端数据
                        }

                        byteBuffer.rewind();
                        System.out.println(byteBuffer.limit());
                        byte[] bytes = new byte[byteBuffer.limit()];
                        byteBuffer.get(bytes);
                        request = new String(bytes);
                    }

                    selector.selectedKeys().remove(key);//删除key集
                }

                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void selectorRead() {

        try {

            Selector selector = Selector.open();

//            InetAddress inetAddress = InetAddress.getLoopbackAddress();//回环地址
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");
            ServerSocketChannel serverSocket = ServerSocketChannel.open();//打开服务端通道


            serverSocket.bind(new InetSocketAddress(inetAddress, 5454));
            serverSocket.configureBlocking(false);//使用非阻塞模式

            //方式一：注册时设置感兴趣的事件
            SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            List<String> list = Arrays.asList("aa", "aa", "aa");//附加对象
            selectionKey.attach(list);

            //方式一：注册时设置啥都不感兴趣，然后再指定感兴趣的事件
//            SelectionKey selectionKey = serverSocket.register(selector, 0);
//            selectionKey.interestOps(SelectionKey.OP_ACCEPT);//设置感兴趣的事件
//            List<String> list = Arrays.asList("aa", "aa", "aa");//附加对象
//            selectionKey.attach(list);


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


                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();//获取服务端通道
                        SocketChannel socketChannel = serverSocketChannel.accept();//获取客户端通道
                        System.out.println(socketChannel);
                        socketChannel.configureBlocking(false);//修改通道属性，客户端通道使用非阻塞模式
                        socketChannel.register(selector, SelectionKey.OP_READ);//修改通道属性，把感兴趣的事件设置为READ


                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());


                    }

                    //处理read事件
                    if (key.isReadable()) {
                        System.out.println("*******isReadable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());


                        int n = 16;
                        byte[] bytes = new byte[1024 * n];
                        Arrays.fill(bytes, (byte) 97);


                        for (int i = 0; i < n; i++) {

                            bytes[1024 * i] = (byte) (100 + i);

                        }

                        bytes[bytes.length - 1] = 98;

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
                        socketChannel.read(byteBuffer);

                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            System.out.println(byteBuffer.get()); //读取客户端数据
                        }
                        byteBuffer.rewind();


                        //回复1K
//                        byteBuffer.clear();
//                        byteBuffer.put(bytes);
//                        byteBuffer.rewind();


                        //回复ok
//                        byteBuffer.clear();
//                        byteBuffer.put("ok".getBytes());
//                        byteBuffer.rewind();
//                        byteBuffer.limit("ok".getBytes().length);


                        socketChannel.write(byteBuffer); //发送数据到客户端

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
