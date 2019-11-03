import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ServerSocketChannelTrial {
    String ip = "192.168.0.126";
    int port = 5454;

    public static void main(String[] args) {
        ServerSocketChannelTrial serverSocketChannelTrial = new ServerSocketChannelTrial();


//        serverSocketChannelTrial.tcpBlock();//阻塞I/O
        serverSocketChannelTrial.tcpNoBlock();//非阻塞I/O


//        serverSocketChannelTrial.udpBlock();

    }

    private void udpBlock() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1);

        try (DatagramChannel datagramChannel = DatagramChannel.open()) {

            if (!datagramChannel.isOpen()) {
                System.out.println("not opened!");
                return;
            }

            datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
            datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
            datagramChannel.bind(new InetSocketAddress(ip, port));


            while (true) {
                System.out.println("-----start------");

                SocketAddress clientAddress = //返回客户端Socket
                        datagramChannel.receive(byteBuffer);////阻塞，等待接收请求的数据
                System.out.println("received!");

                String request = UTF_8.decode(byteBuffer.flip()).toString();//解码请求的字节
                byteBuffer.clear();
                System.out.println(request);


                request = "[UDP server]" + request;
                datagramChannel.send(UTF_8.encode(request), clientAddress);//发送请求


                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void tcpBlock() {

        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//打开通道
            serverSocketChannel.bind(new InetSocketAddress(inetAddress, port));
            System.out.println(serverSocketChannel);
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel);

            //方式一：基于Buffer的读写
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (socketChannel.read(buffer) != -1) {
                System.out.println("reading");
                String request = UTF_8.decode(buffer.flip()).toString();
                System.out.println(request);
                buffer.clear();
            }
            socketChannel.write(UTF_8.encode("OK"));


            //方式二：基于Stream的读写
//            try (InputStream in = socketChannel.socket().getInputStream();
//                 OutputStream out = socketChannel.socket().getOutputStream()) {
//                byte[] bytes = new byte[1024];
//                while (in.read(bytes) != -1) System.out.println(new String(bytes));
//                out.write("ok".getBytes());
//                out.flush();
//            }


            socketChannel.shutdownOutput();
            socketChannel.close();
            serverSocketChannel.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void tcpNoBlock() {
        selectorSimpleOne(); //简单使用例一
//        selectorSimpleTwo(); //简单使用例二
//        selectorSingleEvent();
//        selectorMultypleEvent();
    }

    private void selectorSingleEvent() {
        Map<SocketChannel, String> map = new HashMap<>();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1);

        try {
            Selector selector = Selector.open();//获取选择器
            InetAddress inetAddress = InetAddress.getByName(ip);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//打开服务端通道


            serverSocketChannel.bind(new InetSocketAddress(inetAddress, port));//绑定Socket
            serverSocketChannel.configureBlocking(false);//设置服务端通道模式
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册服务端通道，并设置感兴趣的事件


            while (true) {
                System.out.println("-----start------");

                selector.select();//调用selecot方法，查询底层操作系统，看内核buffer是否有数据

                for (SelectionKey key : selector.selectedKeys()) {

                    System.out.println("threadID is " + Thread.currentThread());

                    selector.selectedKeys().remove(key);//删除key，以免下次selector.select()生成的key中又包含此key

                    if (!key.isValid()) continue;

                    //处理accept事件
                    if (key.isAcceptable()) {
                        System.out.println("*******isAcceptable()********");

                        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocket.accept();//获取客户端通道
                        System.out.println(socketChannel);
                        System.out.println(socketChannel.getRemoteAddress());
                        socketChannel.configureBlocking(false);//设置客户端通道模式
                        socketChannel.register(selector, SelectionKey.OP_READ);//设置感兴趣的事件



                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());


                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(UTF_8.encode("[server]" + map.get(socketChannel)));
                        map.remove(socketChannel);

                        key.interestOps(SelectionKey.OP_READ);
                    }

                    //处理read事件
                    if (key.isReadable()) {
                        System.out.println("*******isReadable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());


                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        if (socketChannel.read(byteBuffer) == -1) {
                            key.cancel();
                            socketChannel.shutdownOutput();
                            socketChannel.shutdownInput();
                            socketChannel.close();
                            continue;
                        }


                        System.out.println(UTF_8.decode(byteBuffer.flip()).toString());
                        map.put(socketChannel, UTF_8.decode(byteBuffer.flip()).toString());//将读取的内容保存到容器
                        byteBuffer.clear();
                        key.interestOps(SelectionKey.OP_WRITE);

                    }
                }

                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void selectorMultypleEvent() {

    }


    private void selectorSimpleTwo() {

        String request = null;


        try {
            Selector selector = Selector.open();//获取选择器
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");
            ServerSocketChannel serverSocket = ServerSocketChannel.open();//打开通道


            serverSocket.bind(new InetSocketAddress(inetAddress, 5454));//绑定Socket
            serverSocket.configureBlocking(false);//设置通道模式
            SelectionKey selectionKey = serverSocket.register(selector, 0);//注册选择器
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);//设置感兴趣的事件


            while (true) {
                System.out.println("-----start------");

                selector.select();//调用selecot方法，查询底层操作系统，看内核buffer是否有数据

                for (SelectionKey key : selector.selectedKeys()) {

                    System.out.println("threadID is " + Thread.currentThread());


                    if (!key.isValid()) continue;

                    //处理accept事件
                    if (key.isAcceptable()) {
                        System.out.println("*******isAcceptable()********");

                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();//获取通道对象
                        System.out.println(socketChannel);
                        System.out.println(socketChannel.getRemoteAddress());
                        socketChannel.configureBlocking(false);//设置通道模式
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);//设置感兴趣的事件

                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());

                        if (request != null) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());

                            socketChannel.write(byteBuffer);//写数据到客户端
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

                    selector.selectedKeys().remove(key);//删除key，以免下次selector.select()生成的key中又包含此key
                }

                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void selectorSimpleOne() {

        try {

            Selector selector = Selector.open();//创建选择器

//            InetAddress inetAddress = InetAddress.getLoopbackAddress();//回环地址
            ServerSocketChannel serverSocket = ServerSocketChannel.open();//打开服务端通道
            serverSocket.configureBlocking(false);//使用非阻塞模式
            InetAddress inetAddress = InetAddress.getByName(ip);
            serverSocket.bind(new InetSocketAddress(inetAddress, port));


            //方式一：注册时设置感兴趣的事件
            SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            List<String> list = Arrays.asList("aa", "aa", "aa");//附加对象
            selectionKey.attach(list);

            //方式一：注册时设置啥都不感兴趣，然后再指定感兴趣的事件
//            SelectionKey selectionKey = serverSocket.register(selector, 0);
//            selectionKey.interestOps(SelectionKey.OP_ACCEPT);//设置感兴趣的事件
//            List<String> acceptList = Arrays.asList("aa", "aa", "aa");//附加对象
//            selectionKey.attach(acceptList);


            while (true) {
                System.out.println("-----start------");

                selector.select();//调用selecot方法，查询底层操作系统，看内核buffer是否有数据

                for (SelectionKey key : selector.selectedKeys()) {

                    System.out.println("threadID is " + Thread.currentThread());

                    if (!key.isValid()) continue;

                    //处理accept事件
                    if (key.isAcceptable()) {
                        System.out.println("*******isAcceptable()********");
                        System.out.println("key is " + key);
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());
                        list = (List<String>) key.attachment();
                        System.out.println(list);


                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();//获取服务端通道
                        SocketChannel socketChannel = serverSocketChannel.accept();//获取客户端通道
                        System.out.println(socketChannel);
                        socketChannel.configureBlocking(false);//修改通道属性，客户端通道使用非阻塞模式
                        socketChannel.register(selector, SelectionKey.OP_READ)//修改通道属性，把感兴趣的事件设置为READ
                        .attach(Arrays.asList("oneRead", "twoRead", "threeRead"));


                    }

                    //处理write事件
                    if (key.isWritable()) {
                        System.out.println("*******isWritable()********");
                        System.out.println("key is " + key);
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());
                        System.out.println(key.attachment());

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer data = (ByteBuffer) key.attachment();
                        socketChannel.write(data); //发送数据到客户端
                        data.clear();

                        socketChannel.register(selector, SelectionKey.OP_READ);//修改通道属性，把感兴趣的事件设置为READ
                    }

                    //处理read事件
                    if (key.isReadable()) {
                        System.out.println("*******isReadable()********");
                        System.out.println("key is " + key);
                        System.out.println("interestOps is " + key.interestOps());
                        System.out.println("readyOps is " + key.readyOps());
                        System.out.println(key.attachment());


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


                        socketChannel.register(selector, SelectionKey.OP_WRITE)//修改通道属性，把感兴趣的事件设置为WRITE
                                .attach(byteBuffer);

                    }

                    selector.selectedKeys().remove(key);//删除key，以免下次selector.select()生成的key中又包含此key

                }


                System.out.println("-----end------");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}