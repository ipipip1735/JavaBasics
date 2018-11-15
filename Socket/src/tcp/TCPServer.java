package tcp;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Spliterators;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TCPServer {

    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer();
        tcpServer.create();
//        tcpServer.isBind();
//        tcpServer.listen();
    }

    private void isBind() {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println(serverSocket.isBound());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void create() {

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("192.168.0.126", 6666));
            System.out.println("getLocalSocketAddress is " + serverSocket.getLocalSocketAddress());
//            serverSocket.accept();
            serverSocket.setReuseAddress(true);

            while (true) {

                serverSocket.accept();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {

        try {
            init();
//            initWhile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(InetAddress.getByName("192.168.0.126"), 6666));
        Socket socket = serverSocket.accept();


        //读字节数据
//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[10];
//        int n;
//        while ((n = inputStream.read(bytes)) != -1) {
//            for (int i = 0; i < n; i++) {
//                System.out.println(bytes[i]);
//            }
//        }
//        inputStream.close(); //先不关闭，后面还要给客户端回应信息
//        socket.close(); // 不需要调用，因为bufferedReader.close()将自动关闭socket


        //读字符串数据
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if (s.getBytes().length == 0) //如果是字节，那么就表示客户端请求信息结束了
                break; //socket是无限流，应该跳出循环，后面应该解析请求内容，然后回应信息
            System.out.println(s);
            break;
        }
//        bufferedReader.close(); //先不关闭，后面还要给客户端回应信息
//        socket.close(); // 不需要调用，因为bufferedReader.close()将自动关闭socket


        //给客户端回应请求
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write("HTTP/1.0 200 OK"); // 返回应答消息,并结束应答
        bufferedWriter.write("Date: Fri, 31 Dec 1999 23:59:59 GMT"); // 返回应答消息,并结束应答
        bufferedWriter.write("Server: Apache/0.8.4"); // 返回应答消息,并结束应答
        bufferedWriter.write("Content-Type: text/html"); // 返回应答消息,并结束应答
        bufferedWriter.write("Content-Length: 29"); // 返回应答消息,并结束应答
        bufferedWriter.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT"); // 返回应答消息,并结束应答
        bufferedWriter.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT"); //
        bufferedWriter.write(""); // 根据 HTTP 协议, 空行将结束头信息

        bufferedWriter.write("<html><body>");
        bufferedWriter.write("gogogo");
        bufferedWriter.write("</body></html>");

        bufferedWriter.close();
        socket.close(); // 不需要调用，因为bufferedReader.close()将自动关闭socket


        serverSocket.close(); //关闭服务端
    }


    public void initWhile() throws IOException {
        InetAddress ip = InetAddress.getByName("192.168.0.126");
        InetSocketAddress isa = new InetSocketAddress(ip, 6666);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(isa);


        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
                        BufferedReader bufferedReader = new BufferedReader(reader);

                        OutputStream outputStream = socket.getOutputStream();
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF_8);
                        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


                        String s;
                        while ((s = bufferedReader.readLine()) != null) {
                            if (s.getBytes().length == 0) //如果是字节，那么就表示客户端请求信息结束了
                                break; //socket是无限流，应该跳出循环，后面应该解析请求内容，然后回应信息
                            System.out.println(s);

                            bufferedWriter.write("<html><body>");
                            bufferedWriter.write("gogogo");
                            bufferedWriter.write("</body></html>");
                            bufferedWriter.write("\r\n");
                            bufferedWriter.flush();
                        }

                        bufferedWriter.close();
                        bufferedReader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
//        serverSocket.close();
    }


}
