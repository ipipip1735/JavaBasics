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
            this.ip = InetAddress.getByName("192.168.0.127");
            this.serverIp = InetAddress.getByName("192.168.0.127");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TCPClient tcpClient = new TCPClient();
        try {
//            tcpClient.httpGET();
            tcpClient.communication();

        } catch (IOException e) {
            e.printStackTrace();
        }


//        tcpClient.reuse(); //测试失败了

    }

    private void communication() throws IOException {

        Socket socket = new Socket();
        InetSocketAddress serverISA = new InetSocketAddress(serverIp, 6666);
        socket.connect(serverISA);

//        System.out.println("getLocalAddress is " + socket.getLocalAddress());
//        System.out.println("getLocalPort()() is " + socket.getLocalPort());
//        System.out.println("getLocalSocketAddress() is " + socket.getLocalSocketAddress());
//        System.out.println("getPort() is " + socket.getPort());
//        System.out.println("getInetAddress() is " + socket.getInetAddress());
//        System.out.println("getRemoteSocketAddress() is " + socket.getRemoteSocketAddress());




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    int r;
                    while ((r = inputStream.read()) != -1) {
                        System.out.println("r is " + r);
                    }
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 97; i < 100; i++) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(i);
            outputStream.flush();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        socket.shutdownOutput();

    }

    private void reuse() {

        try {

            Socket socket = new Socket();
            InetSocketAddress serverISA = new InetSocketAddress(serverIp, 6666);
            InetSocketAddress clientISA = new InetSocketAddress(ip, 7778);
//            socket.setReuseAddress(true);
//            socket.bind(clientISA);
            int port = socket.getPort();
            System.out.println("port is " + port);
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

    private void httpGET() throws IOException {

        Socket socket = new Socket();
        InetSocketAddress serverISA = new InetSocketAddress(serverIp, 6666);
//        InetSocketAddress clientISA = new InetSocketAddress(ip, 7771);
//        socket.bind(clientISA); //不指定socket，然系统自动分配空闲socket
        socket.connect(serverISA);

        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write("GET / HTTP/1.1\n");
        bufferedWriter.write("HOST: 192.168.0.126:80\n");
        bufferedWriter.write("\n"); // 根据 HTTP 协议, 空行将结束头信息
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
        System.out.println(socket.isClosed());

    }


}
