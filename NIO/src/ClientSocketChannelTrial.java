import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2019/10/31 13:25.
 */
public class ClientSocketChannelTrial {
    String ip = "192.168.0.126";
    int port = 5454;

    public static void main(String[] args) {
        ClientSocketChannelTrial client = new ClientSocketChannelTrial();

//        client.tcpBlock();
        client.tcpNoBlock();

//        client.updBlock();

    }

    private void updBlock() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1);

        try (DatagramChannel datagramChannel = DatagramChannel.open()) { //创建UDP通道

            datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);//配置接收Buffer
            datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);//配置发送Buffer


            int n = datagramChannel.send( //发送请求
                    ByteBuffer.wrap("request".getBytes()), //请求数据
                    new InetSocketAddress(ip, port)); //目标Socket
            System.out.println("byte sent is " + n);

            datagramChannel.receive(byteBuffer);//阻塞，等待接收回应的数据

            System.out.println(UTF_8.decode(byteBuffer.flip()).toString());


        } catch (IOException ex) {
            ex.printStackTrace();
        }





    }

    private void tcpNoBlock() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1);

        try (Selector selector = Selector.open();
             SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            socketChannel.connect(new java.net.InetSocketAddress(ip, port));

            while (selector.select(1000) > 0) {
                System.out.println("-----start------");

                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);

                    SocketChannel sc = (SocketChannel) key.channel();

                    if (key.isConnectable()) {
                        System.out.println("*******isConnectable()********");


                        if (sc.isConnectionPending()) {
                            sc.finishConnect();
                        }


                        sc.write(UTF_8.encode("Request"));
                        sc.shutdownOutput();

                        while (sc.read(byteBuffer) != -1) {
                            System.out.println(UTF_8.decode(byteBuffer.flip()).toString());
                            byteBuffer.clear();

                        }

                    }

                }

                System.out.println("-----end------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (key.isConnectable()) {
//            System.out.println("*******isConnectable()********");
//
//            if (sc.isConnectionPending()) {
//                sc.finishConnect();
//            }
//
//
//            sc.write(UTF_8.encode("Request"));
//            sc.shutdownOutput();
//
//            System.out.println(byteBuffer);
//            while (sc.read(byteBuffer) != -1) {
//
//                byteBuffer.flip();
//                System.out.println(byteBuffer);
////                                System.out.println(UTF_8.decode(byteBuffer.flip()).toString());
//                byteBuffer.clear();
//            }
//        }

    }

    private void tcpBlock() {


        try {
            InetAddress inetAddress = InetAddress.getByName(ip);

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);


            socketChannel.connect(new InetSocketAddress(inetAddress, port));


            socketChannel.write(ByteBuffer.wrap("request".getBytes()));
            socketChannel.shutdownOutput();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer) != -1) {
                System.out.println("reading");
                String response = Charset.defaultCharset()
                        .newDecoder()
                        .decode(buffer.flip())
                        .toString();
                System.out.println(response);

                buffer.clear();
            }

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}