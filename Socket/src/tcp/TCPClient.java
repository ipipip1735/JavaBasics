package tcp;


import java.io.*;
import java.net.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TCPClient {

    private InetAddress ip, serverIp;


    public TCPClient() {

        try {
            this.ip = InetAddress.getByName("192.168.0.126");
            this.serverIp = InetAddress.getByName("192.168.0.126");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TCPClient tcpClient = new TCPClient();
        try {
            tcpClient.init();

        } catch (IOException e) {
            e.printStackTrace();
        }


//        tcpClient.reuse(); //测试失败了

    }

    private void reuse() {

        try {

            Socket socket = new Socket();
            InetSocketAddress serverISA = new InetSocketAddress(serverIp, 6666);
            InetSocketAddress clientISA = new InetSocketAddress(ip, 7778);
//            socket.setReuseAddress(true);
//            socket.bind(clientISA);
            int port = socket.getPort();
            System.out.println("port is " +  port);
            socket.connect(serverISA);
            System.out.println("localSocket is " + socket.getLocalSocketAddress());
            socket.close();




            socket = new Socket();
            socket.bind(new InetSocketAddress(ip, port));
//            socket.setReuseAddress(true);
            socket.connect(serverISA);
            System.out.println("localSocket is " + socket.getLocalSocketAddress());
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void init() throws IOException {

        Socket socket = new Socket();
        InetSocketAddress serverISA = new InetSocketAddress(serverIp, 6666);
        InetSocketAddress clientISA = new InetSocketAddress(ip, 7771);
        socket.bind(clientISA);
        socket.connect(serverISA);

        System.out.println("-ok-");

        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write("GET / HTTP/1.1");
        bufferedWriter.write("HOST:");
        bufferedWriter.write(""); // 根据 HTTP 协议, 空行将结束头信息
        bufferedWriter.flush();
        socket.shutdownOutput();//关闭输出流，发送信息到服务端


        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if (s.getBytes().length == 0) //如果是字节，那么就表示客户端请求信息结束了
                break; //socket是无限流，应该跳出循环，后面应该解析请求内容，然后回应信息
            System.out.println(s);
        }
        bufferedReader.close();
        bufferedWriter.close();
        System.out.println(socket.isClosed());

    }


}
